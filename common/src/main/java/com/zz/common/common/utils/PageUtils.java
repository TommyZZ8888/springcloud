package com.zz.common.common.utils;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zz.common.common.core.domain.OrderItem;
import com.zz.common.common.core.domain.PageParam;
import com.zz.common.common.filter.RequestCacheWrapper;
import com.zz.common.common.utils.sql.SqlUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页工具类
 *
 * @author ruoyi
 */
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        RequestCacheWrapper request = (RequestCacheWrapper) ServletUtils.getRequest();
        PageParam pageParam = JSONObject.parseObject(request.getBodyString(), PageParam.class);
        List<OrderItem> ordersList = pageParam.getOrders();
        String orderBy = null;
        if (StringUtils.isNotEmpty(ordersList)) {
            List<String> orders = ordersList.stream().map(item -> StringUtils.format("{} {}", StringUtils.toUnderScoreCase(item.getColumn()), item.getIsAsc())).collect(Collectors.toList());
            orderBy = StringUtils.join(orders, ",");
            orderBy = SqlUtil.escapeOrderBySql(orderBy);
        }
        PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize(), orderBy);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }
}
