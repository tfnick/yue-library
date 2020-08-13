package ai.yue.library.flow.camunda.plugins;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwenxue
 * @Description: 使用插件机制来实现对流程图的通用化的定制，减少开发人员配置的工作量,同时减少出错概率
 *  比如：添加一些定制的监听器 配置所有后台任务都异步化
 * @date 2020/6/12 13:42
 */
public class ProgressSupportParseListenerPlugin extends AbstractProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

        List<BpmnParseListener> preParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();
        if (preParseListeners == null) {
            preParseListeners = new ArrayList<BpmnParseListener>();
            processEngineConfiguration.setCustomPreBPMNParseListeners(preParseListeners);
        }
        preParseListeners.add(new ProgressSupportParseListener());
    }
}
