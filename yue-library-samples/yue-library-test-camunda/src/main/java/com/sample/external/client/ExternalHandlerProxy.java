package com.sample.external.client;

import ai.yue.library.flow.camunda.listener.FlowConst;
import com.sample.Const;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author yangxiao
 * @Description: 外部任务处理器 静态代理类
 * @date 2020/7/17 17:33
 */
public class ExternalHandlerProxy implements ExternalTaskHandler{

    private static final Logger logger = LoggerFactory.getLogger(ExternalHandlerProxy.class);

    public ExternalHandlerProxy(ExternalTaskHandler externalTaskHandler) {
        this.externalTaskHandler = externalTaskHandler;
    }

    ExternalTaskHandler externalTaskHandler;

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService){
        try {
            String missionNo = externalTask.getVariable(FlowConst.MNO);
            //String activityId = externalTask.getActivityId();
            //String processDefinitionId = externalTask.getProcessDefinitionId();

            MDC.put(FlowConst.MNO, missionNo);
            //MDC.put("activityId", activityId);
            //MDC.put("processDefinitionId", processDefinitionId);

            externalTaskHandler.execute(externalTask, externalTaskService);
        } catch (Exception e) {
            logger.error("外部任务异常", e);
            throw e;
        }finally {
            MDC.remove(FlowConst.MNO);
        }

    }
}

