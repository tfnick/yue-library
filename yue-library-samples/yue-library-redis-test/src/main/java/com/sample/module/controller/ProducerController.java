package com.sample.module.controller;

import ai.yue.library.base.view.Result;
import cn.hutool.core.util.RandomUtil;
import com.sample.module.common.Const;
import com.sample.module.service.Producer;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ProducerController {
    private final String prefix = "lwx_";

    @Autowired
    Producer producer;

    @Autowired
    RedissonClient redisson;

    @GetMapping("/msg/send")
    public Result<Long> send() {
        String key = RandomUtil.randomString(4);
        Integer value = RandomUtil.randomNumber();
        StreamMessageId id = producer.send(key, value.toString());

        return Result.<Long>builder().data(id.getId0()).build();
    }

    @GetMapping("/msg/receive")
    public Result<Long> receive() {
        final RStream<String, String> stream = redisson.getStream(Const.REDIS_MESSAGE.TOPIC);
        Map<StreamMessageId, Map<String, String>> msgHolder = stream.readGroup(Const.REDIS_MESSAGE.CONSUMER_GROUP, "consumer0", 1);

        return Result.<Long>builder().data(Long.valueOf(msgHolder.size())).build();
    }

    @GetMapping("/cache")
    public Result<Long> cache() {
        RBucket<String> stringKey = redisson.getBucket(prefix + "stringKey");
        stringKey.set("A String Value");

        RBucket<Integer> intKey = redisson.getBucket(prefix + "integerKey");
        intKey.set(1000);


        RMap<String, String> map = redisson.getMap(prefix + "mapKey");
        map.put("a", "100");

        RList<String> list = redisson.getList(prefix + "listKey");
        list.add("100");
        list.expire(5, TimeUnit.SECONDS);

        RAtomicLong atomicInteger = redisson.getAtomicLong(prefix + "counter");
        long result = atomicInteger.addAndGet(100);

        return Result.<Long>builder().data(result).build();
    }
}
