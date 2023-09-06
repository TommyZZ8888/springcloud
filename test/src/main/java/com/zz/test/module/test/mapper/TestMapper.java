package com.zz.test.module.test.mapper;

import com.zz.test.module.test.domain.dto.TestDTO;
import com.zz.test.module.test.domain.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {

    int insert(TestEntity testEntity);

    List<TestEntity> find(String id);

    List<TestEntity> select(@Param("dto") TestDTO dto);
}
