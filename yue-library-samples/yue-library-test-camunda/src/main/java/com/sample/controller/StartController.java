package com.sample.controller;

import ai.yue.library.base.util.UUIDUtils;
import ai.yue.library.base.view.Result;
import ai.yue.library.base.view.ResultInfo;
import ai.yue.library.flow.camunda.listener.FlowConst;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StartController {

    @Autowired
    RuntimeService runtimeService;

    @GetMapping("/post")
    public Result<?> startProcess(){
        Map vars = new HashMap<>();
        vars.put(FlowConst.MNO, UUIDUtils.getOrderNo_19());
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("DEMO_PD_ID", vars);
        return ResultInfo.success(pi.getProcessDefinitionId());
    }

    @GetMapping("/query")
    public Result<?> queryResult(String mno){
        //根据mno从业务表中查询业务结果
        return ResultInfo.success();
    }
}
