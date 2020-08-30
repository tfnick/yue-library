package com.sample.external.client;

import com.sample.Const;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Client implements DisposableBean, CommandLineRunner {

    @Value("${camunda.bpm.external.server-url}")
    String extTaskServerUrl;

    private ExternalTaskClient externalTaskClient;

    private void start() {
        log.info("=======================启动外部任务客户端========================");

        externalTaskClient = ExternalTaskClient.create()
                .baseUrl(extTaskServerUrl)
                .build();
        // 订阅topic
        externalTaskClient.subscribe(Const.TOPIC_QUERY_XXX)
                .lockDuration(20000)
                .handler(new ExternalHandlerProxy(ClientHandlerFactory.getExternalTaskHandler(Const.TOPIC_QUERY_XXX))).open();
    }
    
    @Override
    public void destroy() throws Exception {
        if(externalTaskClient != null){
            externalTaskClient.stop();
        }
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (!extTaskServerUrl.contains("-1")) {
            start();
        }
    }
}
