package com.sample;

import ai.yue.library.data.redis.annotation.MQPublish;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Slf4j
@Controller
public class MQController {

    @Resource
    private RedissonClient redissonClient;


    /**
     * 发送方式一: 使用代码发送消息
     */
    @RequestMapping("testMq1")
    @ResponseBody
    public void testMq(){
        RStream testMq1 = redissonClient.getStream("testMq1");
        User user = new User();
        user.setAge(22);
        user.setName("王阳明");
        testMq1.add("12", JSON.toJSONString(user));
    }

    @RequestMapping("testMq1/anno")
    @ResponseBody
    @MQPublish(name="testMq1")
    User mock(){
        log.info("注解发送消息，消息ID随机生产");
        User user =new User();
        user.setName(RandomUtil.randomString(6));
        user.setAge(RandomUtil.randomInt(20, 40));
        return user;
    }

    /**
     * 发送方式一: 使用代码发送消息
     */
    @RequestMapping("testMq2")
    @ResponseBody
    public void testMq2(){
        RStream testMq1 = redissonClient.getStream("testMq1");
        User user = new User();
        user.setAge(36);
        user.setName("李白");
        testMq1.add("198", JSON.toJSONString(user));
    }

}