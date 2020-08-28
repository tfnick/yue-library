package com.sample;

import com.alibaba.fastjson.JSON;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MQController {

    @Resource
    private RedissonClient redissonClient;


    /**
     * 发送方式一: 使用代码发送消息
     */
    @RequestMapping("testMq")
    @ResponseBody
    public void testMq(){
        RStream testMq = redissonClient.getStream("testMq");
        User message = new User();
        message.setAge(22);
        message.setName("王阳明");
        testMq.add("12", JSON.toJSONString(message));
    }

}