package com.vren.common.module.system.log.mapper;

import com.vren.common.module.system.log.domain.entity.UserOperateLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOperateLogMapper {

    int insert(UserOperateLogEntity entity);

    void truncate();

}