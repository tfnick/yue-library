package com.sample.module.service;

import cn.hutool.core.collection.ListUtil;
import com.lzh.easythread.EasyThread;
import com.sample.module.common.Const;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamGroup;
import org.redisson.api.StreamMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 程序完全启动后进行初始化消费者动作
 */
@Service
public class Consumer implements CommandLineRunner, DisposableBean {
    protected static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    RedissonClient redisson;

    @Autowired
    ResourceManager resourceManager;

    public void startConsumers() {
        final int MAX_CONSUMER_THREADS = resourceManager.getMAX_CONSUMER_THREADS();
        final RStream<String, String> stream = redisson.getStream(Const.REDIS_MESSAGE.TOPIC);

        for (int threadId = 0; threadId < MAX_CONSUMER_THREADS; threadId++) {
            final String consumerId = "consumer" + threadId;
            log.info("启动消费者线程 {}", consumerId);
            resourceManager.getConsumerPool().execute(new Runnable() {
                @Override
                public void run() {
                    //消费消息线程
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            log.error("e", e);
                        }
                        Map<StreamMessageId, Map<String, String>> msgHolder = stream.readGroup(Const.REDIS_MESSAGE.CONSUMER_GROUP, consumerId, 1);
                        if (msgHolder != null && msgHolder.size() > 0) {
                            for (Map.Entry<StreamMessageId, Map<String, String>> entry : msgHolder.entrySet()) {
                                Map<String, String> msg = entry.getValue();
                                log.info("线程ID {},线程任务名 {},消费者ID {},消息ID {},消息体 {}", Thread.currentThread().getId(), Thread.currentThread().getName(), consumerId, entry.getKey(), msg);
                                //消费了消息，要应答一下
                                stream.ack(Const.REDIS_MESSAGE.CONSUMER_GROUP, entry.getKey());
                                //如果消费了消息想删除，可以删除掉
                                //stream.remove(entry.getKey());
                            }
                        } else {
                            //log.info("无消息可消费");
                        }
                    }
                }
            });
        }
    }

    @Override
    public void destroy() throws Exception {
        resourceManager.getConsumerPool().getExecutor().shutdown();
    }

    @Override
    public void run(String... args) throws Exception {
        //创建topic与consumer group
        RStream<String, String> stream = redisson.getStream(Const.REDIS_MESSAGE.TOPIC);
        stream.add("0","0");
        List<String> groupNames = ListUtil.toList(Const.REDIS_MESSAGE.CONSUMER_GROUP);
        List<StreamGroup> groups = stream.listGroups();
        groupNames.forEach(group -> {
            boolean exists = false;
            if (groups != null) {
                for (StreamGroup sg : groups) {
                    if (sg.getName().equals(group)) {
                        exists = true;
                        break;
                    }
                }
            }
            if (!exists) {
                stream.createGroup(group);
            }
        });
        //启动消费者线程
        this.startConsumers();
    }
}
