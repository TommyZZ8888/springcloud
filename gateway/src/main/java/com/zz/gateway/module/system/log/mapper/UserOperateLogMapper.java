package com.zz.gateway.module.system.log.mapper;

import com.zz.gateway.module.system.log.domain.entity.UserOperateLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOperateLogMapper {

    int insert(UserOperateLogEntity entity);

    void truncate();

}