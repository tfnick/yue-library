//package com.sample.configure;
//
//import cn.hutool.core.thread.NamedThreadFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//
///**
// * 此配置已经在library中实现
// */
//@Slf4j
//@Configuration
//public class SchedulingExecutorConfigure implements SchedulingConfigurer {
//
//    public SchedulingExecutorConfigure() {
//        log.info("【初始化配置-调度线程池】SchedulingExecutorConfigure-1 ... 已初始化完毕。");
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskExecutor());
//    }
//
//    @Bean(destroyMethod="shutdown")
//    public Executor taskExecutor() {
//        return Executors.newScheduledThreadPool(5,new NamedThreadFactory("schedule-",false)); //指定线程池大小
//    }
//
//}
