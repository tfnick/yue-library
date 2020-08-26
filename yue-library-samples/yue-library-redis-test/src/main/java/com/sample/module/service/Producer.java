package com.sample.module.service;

import com.sample.module.common.Const;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    protected static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    RedissonClient redisson;

    public StreamMessageId send(String key,String value) {
        RStream<String, String> stream = redisson.getStream(Const.REDIS_MESSAGE.TOPIC);
        StreamMessageId streamMessageId = stream.add(key, value);
        log.info("消息ID{}",streamMessageId.getId0());
        return streamMessageId;
    }
}
