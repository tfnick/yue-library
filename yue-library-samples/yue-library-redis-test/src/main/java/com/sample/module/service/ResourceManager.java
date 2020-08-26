package com.sample.module.service;

import cn.hutool.core.collection.ListUtil;
import com.lzh.easythread.EasyThread;
import com.sample.module.common.Const;
import org.redisson.api.RStream;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * 负责资源的初始化与销毁
 */
@Component
public class ResourceManager {
    protected static final Logger log = LoggerFactory.getLogger(ResourceManager.class);
    int MAX_CONSUMER_THREADS = 2;
    int MAX_TASK_THREADS = 5;

    EasyThread taskPool;
    EasyThread consumerPool;

    public int getMAX_CONSUMER_THREADS() {
        return MAX_CONSUMER_THREADS;
    }

    public int getMAX_TASK_THREADS() {
        return MAX_TASK_THREADS;
    }

    public EasyThread getTaskPool() {
        return taskPool;
    }

    public EasyThread getConsumerPool() {
        return consumerPool;
    }

    @PostConstruct
    public void init(){
        taskPool = EasyThread.Builder.createFixed(MAX_TASK_THREADS).build();
        consumerPool = EasyThread.Builder.createFixed(MAX_CONSUMER_THREADS).build();
    }

    @PreDestroy
    public void destroy(){
        if (!taskPool.getExecutor().isShutdown()) {
            taskPool.getExecutor().shutdown();
        }
        if (!consumerPool.getExecutor().isShutdown()) {
            consumerPool.getExecutor().shutdown();
        }

        while(true){
            if(taskPool.getExecutor().isTerminated()){
                log.info("所有任务线程已经完成");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("e", e);
            }
        }

        while(true){
            if(consumerPool.getExecutor().isTerminated()){
                log.info("所有消费线程已经完成");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("e", e);
            }
        }
    }
}
