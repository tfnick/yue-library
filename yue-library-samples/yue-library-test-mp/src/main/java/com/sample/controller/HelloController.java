package com.sample.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sample.entity.Customer;
import com.sample.mapper.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/create")
    public Map hello() {

        Customer customer = new Customer();
        customer.setAccount(RandomUtil.randomString(20));
        customer.setConfidential("pwd");
        customerRepository.insert(customer);

        Map s = new HashMap<>();

        s.put("now", System.currentTimeMillis());
        return s;
    }


    @GetMapping("/update")
    public Map update(@RequestParam Long id) {
        Map s = new HashMap<>();
        Customer customer = customerRepository.selectById(id);
        if (customer != null) {
            customerRepository.updateById(customer);

            s.put("success", customer.getId());
        }else{
            s.put("fail", "id不存在");
        }

        return s;
    }
}
