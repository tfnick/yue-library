package com.sample.controller;

import ai.yue.library.base.util.UUIDUtils;
import ai.yue.library.base.view.Result;
import ai.yue.library.base.view.ResultInfo;
import com.sample.Const;
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
        vars.put(Const.MNO, UUIDUtils.getOrderNo_19());
        ProcessInstance pi = runtimeService.startProcessInstanceById("DEMO_PD_ID", vars);
        return ResultInfo.success(pi);
    }

    @GetMapping("/query")
    public Result<?> queryResult(String mno){
        //根据mno从业务表中查询业务结果
        return ResultInfo.success();
    }
}