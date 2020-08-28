package com.sample;

import ai.yue.library.data.redis.annotation.EnableMQ;
import ai.yue.library.data.redis.annotation.MQListener;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

//注入到spring容器中
@Component
@EnableMQ
public class Consumer {

    /**
     * 参数固定为key jsonValue两个参数，且顺序固定
     * @param key
     * @param jsonValue
     */
    @MQListener(name = "testMq",group = "test_group")
    public void test1(String key,String jsonValue){
        System.out.println("msg_key=>" + key);
        System.out.println("msg_value=>" + jsonValue);
        User user = JSON.parseObject(jsonValue, User.class);
        System.out.println("用户姓名:" + user.getName());
    }
}
