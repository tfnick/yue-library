package com.sample.external.client;

import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandlerFactory {
    private static Logger logger = LoggerFactory.getLogger(ClientHandlerFactory.class);

    @Autowired
    private static Map<String, ExternalTaskHandler> handlerHolder = new ConcurrentHashMap<String, ExternalTaskHandler>();

    public static void register(String topicName, ExternalTaskHandler externalTaskHandler){
        logger.info("注册ExternalTaskHandler, topicName:{}, externalTaskHandler:{}",topicName, externalTaskHandler);
        handlerHolder.put(topicName, externalTaskHandler);
    }

    public static ExternalTaskHandler getExternalTaskHandler(String topicName){
        return handlerHolder.get(topicName);
    }
}
