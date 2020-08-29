package ai.yue.library.web.config;

import ai.yue.library.base.config.thread.pool.AsyncProperties;
import ai.yue.library.web.config.properties.TaskSchedulingPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 是否开启任务调度线程池（覆盖系统默认的单一线程的调度）
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(TaskSchedulingPoolProperties.class)
@ConditionalOnProperty(prefix = "yue.thread-pool.schedule", name = "enabled", havingValue = "true")
public class TaskSchedulingPoolConfig implements SchedulingConfigurer {


    @Autowired
    TaskSchedulingPoolProperties taskSchedulingPoolProperties;

    @PostConstruct
    private void init() {
        log.info("【初始化配置-定时任务线程池】定时任务线程池配置已加载，待使用时初始化 ...");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(taskSchedulingPoolProperties.getCoreSize()); //指定线程池大小
    }



}
