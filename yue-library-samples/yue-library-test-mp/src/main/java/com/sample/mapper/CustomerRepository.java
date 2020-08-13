package com.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerRepository extends BaseMapper<Customer> {

}