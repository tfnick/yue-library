package com.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoRepository extends BaseMapper<Demo> {

}