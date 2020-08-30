package com.sample.external.task;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.sample.Const;
import com.sample.external.AbstractExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Collections;

public class ExternalTaskQuery extends AbstractExternalTaskHandler {
    @Override
    protected String topicName() {
        return Const.TOPIC_QUERY_XXX;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        int result = RandomUtil.randomInt(0, 2);
        if (1 == result) {//获得结果
            externalTaskService.complete(externalTask, Collections.singletonMap(Const.GET_RESULT_EXECUTION_ID, externalTask.getExecutionId()));
        } else if (0 == result) {//异常
            externalTaskService.handleBpmnError(externalTask,"SYS_ERROR","系统异常", MapUtil.newHashMap());
        }else{//处理中
            externalTaskService.handleFailure(externalTask, "处理中", "处理中", 1, 60 * 1000);
        }
    }
}
