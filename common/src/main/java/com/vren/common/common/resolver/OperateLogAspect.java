package com.vren.common.common.resolver;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.vren.common.common.annotation.OperateLog;
import com.vren.common.common.filter.RequestCacheWrapper;
import com.vren.common.common.utils.ServletUtils;
import com.vren.common.common.utils.StringUtils;
import com.vren.common.common.utils.ThreadLocalUser;
import com.vren.common.module.identity.user.domain.entity.UserInfoEntity;
import com.vren.common.module.system.log.UserOperateLogService;
import com.vren.common.module.system.log.domain.entity.UserOperateLogEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 操作日志记录处理,对所有OperateLog注解的Controller进行操作日志监控
 */
@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    @Autowired
    private UserOperateLogService userOperateLogService;

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    @Pointcut("execution(* com.vren.*.module..*Controller.*(..))")
    public void logPointCut() {

    }

    @Before(value = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }


    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Throwable e, Object jsonResult) {
        try {
            RequestCacheWrapper request = (RequestCacheWrapper) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            OperateLog operateLog = this.getAnnotationLog(joinPoint);
            if (operateLog == null) return;
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String operateMethod = className + "." + methodName;
            String params = null;
            if (operateLog.isSaveRequestData()) {
                params = JSON.toJSONString(request.getBodyString(), excludePropertyPreFilter(operateLog.excludeParamNames()));
            }
            String failReason = null;
            boolean result = true;
            if (e != null) {
                result = false;
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                failReason = sw.toString();
                pw.flush();
                pw.close();
                sw.flush();
                sw.close();
            }
            UserInfoEntity userInfoEntity = ThreadLocalUser.get();
            //防止不需要登录的接口日志报错
            if (userInfoEntity == null) {
                userInfoEntity = new UserInfoEntity();
            }
            UserOperateLogEntity operateLogEntity =
                    UserOperateLogEntity.builder()
                            .userId(userInfoEntity.getUserId())
                            .username(userInfoEntity.getUserName())
                            .url(request.getRequestURI())
                            .method(operateMethod)
                            .param(params)
                            .failReason(failReason)
                            .result(result)
                            .cost(System.currentTimeMillis() - TIME_THREADLOCAL.get())
                            .build();
            if (operateLog.isSaveResponseData() && StringUtils.isNotNull(jsonResult)) {
                operateLogEntity.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
            }
            ApiOperation apiOperation = this.getApiOperation(joinPoint);
            if (apiOperation != null) {
                operateLogEntity.setContent(apiOperation.value());
            }
            Api api = this.getApi(joinPoint);
            if (api != null) {
                String[] tags = api.tags();
                operateLogEntity.setModule(StringUtils.join(tags, ","));
            }
            userOperateLogService.addLog(operateLogEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    private OperateLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return AnnotationUtils.findAnnotation(method.getDeclaringClass(), OperateLog.class);
        }
        return null;
    }

    /**
     * swagger API
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Api getApi(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return AnnotationUtils.findAnnotation(method.getDeclaringClass(), Api.class);
        }
        return null;
    }

    private SimplePropertyPreFilter excludePropertyPreFilter(String[] excludeParamNames) {
        SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter();
        Set<String> excludes = simplePropertyPreFilter.getExcludes();
        excludes.addAll(Arrays.asList(excludeParamNames));
        return simplePropertyPreFilter;
    }

    /**
     * swagger ApiOperation
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private ApiOperation getApiOperation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(ApiOperation.class);
        }
        return null;
    }
}
