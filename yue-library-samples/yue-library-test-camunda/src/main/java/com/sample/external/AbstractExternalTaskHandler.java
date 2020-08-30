package com.sample.external;

import com.sample.external.client.ClientHandlerFactory;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author yangxiao
 * @Description: todo
 * @date 2020/6/9 21:26
 */
public abstract class AbstractExternalTaskHandler implements ExternalTaskHandler,InitializingBean {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        ClientHandlerFactory.register(topicName(),this);
    }

    /**
     * 服务的serviceName
     * @return
     */
    protected abstract String topicName();
}
