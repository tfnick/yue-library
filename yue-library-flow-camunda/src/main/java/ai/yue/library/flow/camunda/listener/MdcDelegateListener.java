package ai.yue.library.flow.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/7/17 20:03
 */

public class MdcDelegateListener implements ExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(MdcDelegateListener.class);

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String eventName = execution.getEventName();

        if ("start".equals(eventName)) {

            //MDC.put(FmpConstants.MDC_MISSION_NO, (String) execution.getVariable(FmpConstants.MDC_MISSION_NO));
            //MDC.put(FmpConstants.MDC_PROCESS_INS_IN, execution.getProcessInstanceId());

            logger.info(execution.getProcessDefinitionId() + " - " +  eventName + " - " + execution.getCurrentActivityName());
        } else if ("end".equals(eventName)) {

            logger.info(execution.getProcessDefinitionId() + " - " +  eventName + " - " + execution.getCurrentActivityName());

            //MDC.remove(FmpConstants.MDC_MISSION_NO);
            //MDC.remove(FmpConstants.MDC_PROCESS_INS_IN);
        } else {
            //nothing to do
        }
    }
}
