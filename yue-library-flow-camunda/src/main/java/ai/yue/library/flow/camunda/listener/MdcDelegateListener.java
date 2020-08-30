package ai.yue.library.flow.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/7/17 20:03
 */

public class MdcDelegateListener implements ExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(MdcDelegateListener.class);

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String eventName = execution.getEventName();

        if ("start".equals(eventName)) {

            MDC.put(FlowConst.MNO, (String) execution.getVariable(FlowConst.MNO));
            //MDC.put(FmpConstants.MDC_PROCESS_INS_IN, execution.getProcessInstanceId());

            log.info(execution.getProcessDefinitionId() + " - " +  eventName + " - " + execution.getCurrentActivityName());
        } else if ("end".equals(eventName)) {

            log.info(execution.getProcessDefinitionId() + " - " +  eventName + " - " + execution.getCurrentActivityName());

            MDC.remove(FlowConst.MNO);
            //MDC.remove(FmpConstants.MDC_PROCESS_INS_IN);
        } else {
            //nothing to do
        }
    }
}
