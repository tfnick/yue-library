package ai.yue.library.flow.camunda.plugins;

import ai.yue.library.flow.camunda.listener.MdcDelegateListener;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/6/12 13:48
 *
 * 样例：
 *     <bpmn:serviceTask id="Activity_0ei9qi9" name="FooTask" camunda:asyncBefore="true">
 *       <bpmn:extensionElements>
 *         <camunda:executionListener class="com.Test" event="start" />
 *         <camunda:properties>
 *           <camunda:property name="xxx" value="111" />
 *           <camunda:property name="yyy" value="222" />
 *         </camunda:properties>
 *       </bpmn:extensionElements>
 *       <bpmn:incoming>Flow_1xl5kkd</bpmn:incoming>
 *       <bpmn:outgoing>Flow_1igigr3</bpmn:outgoing>
 *     </bpmn:serviceTask>
 *
 */
public class ProgressSupportParseListener extends AbstractBpmnParseListener {

    private static final Logger logger = LoggerFactory.getLogger(ProgressSupportParseListener.class);

    @Override
    public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {

        //logger.info("-=Plugin: 设置ServiceTask {} asyncBefore=true =-", serviceTaskElement.attribute("name"));
        //所有后台任务异步执行
        serviceTaskElement.attribute("asyncBefore", "true");
        activity.setAsyncBefore(true);

        //增加日志监听
        Element extensionElement = serviceTaskElement.element("extensionElements");
        if (extensionElement != null) {
            //nothing
        }else{
            //String uri = "http://www.omg.org/spec/BPMN/20100524/MODEL";
            //String tagName = "extensionElements";
            //Element extentionEle = new Element(uri,tagName,tagName,null,null);
            //外部任务的ServiceTask 其监听器会被引擎忽略
            //logger.info("-=Plugin: 设置ServiceTask {} 监听 =-", serviceTaskElement.attribute("name"));
            activity.addExecutionListener(ExecutionListener.EVENTNAME_START, new MdcDelegateListener());
            activity.addExecutionListener(ExecutionListener.EVENTNAME_END, new MdcDelegateListener());
        }


    }


}
