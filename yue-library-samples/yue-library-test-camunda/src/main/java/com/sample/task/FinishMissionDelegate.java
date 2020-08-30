package com.sample.task;

import com.sample.Const;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("finishMissionDelegate")
public class FinishMissionDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("结束任务任务,任务结果{}",delegateExecution.getVariable(Const.GET_RESULT_EXECUTION_ID));
    }
}
