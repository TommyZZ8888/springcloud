package com.zz.common.common.core.controller;

import com.github.pagehelper.PageInfo;
import com.zz.common.common.core.domain.PageResult;
import com.zz.common.common.core.domain.ResponseResult;
import com.zz.common.common.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName:BaseController
 * @Description:
 * @Author: vren
 * @Date: 2022/4/15 14:05
 */
@Slf4j
public class BaseController {

    protected final void startPage() {
        PageUtils.startPage();
    }

    protected final <T> ResponseResult<PageResult<T>> getDataTable(List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(list);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageResult.setPage((long) pageInfo.getPageNum());
        pageResult.setPageSize((long) pageInfo.getPageSize());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPages((long) pageInfo.getPages());
        return ResponseResult.success("获取成功", pageResult);
    }
}
