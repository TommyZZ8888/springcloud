package com.vren.common.module.system.log;

import com.vren.common.common.utils.MyThreadFactory;
import com.vren.common.module.system.log.domain.entity.UserOperateLogEntity;
import com.vren.common.module.system.log.mapper.UserOperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@EnableScheduling
public class UserOperateLogService {

    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private UserOperateLogMapper userOperateLogMapper;

    //每月1号的0:10:00执行
    @Scheduled(cron = "0 10 0 1 * ?")
    void truncate() {
        threadPoolExecutor.execute(() -> userOperateLogMapper.truncate());
    }

    @PostConstruct
    void init() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS
                    , new LinkedBlockingDeque<>(2000), MyThreadFactory.create("LogAspect"));
        }
    }

    @PreDestroy
    void destroy() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
        }
    }


    public void addLog(UserOperateLogEntity userOperateLogEntity) {
        try {
            threadPoolExecutor.execute(() -> userOperateLogMapper.insert(userOperateLogEntity));
        } catch (Throwable e) {
            log.error("userLogAfterAdvice:", e);
        }
    }

}
