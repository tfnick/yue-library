package com.sample.controller;

import ai.yue.library.base.view.Result;
import ai.yue.library.base.view.ResultInfo;
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

    @GetMapping("/start")
    public Result<?> startProcess(){
        Map vars = new HashMap<>();
        ProcessInstance pi = runtimeService.startProcessInstanceById("DEMO_PD_ID", vars);
        return ResultInfo.success(pi);
    }
}
