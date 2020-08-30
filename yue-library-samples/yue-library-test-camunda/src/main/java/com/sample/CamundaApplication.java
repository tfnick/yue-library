package com.sample;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@EnableProcessApplication
public class CamundaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;

    @PostConstruct
    private void deploy(){
        log.info("部署流程");
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deploy = deploymentBuilder.tenantId("none").addClasspathResource("process/demo.bpmn").deploy();
        log.info("部署成功{}", deploy.getId());
    }
}
