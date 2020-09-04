package ai.yue.library.base.config.thread.pool;


import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * 此配置已经在library中实现
 */
@Slf4j
@Configuration
@ConditionalOnClass({EnableScheduling.class})
public class SchedulingExecutorConfigure implements SchedulingConfigurer {

    public SchedulingExecutorConfigure() {
        log.info("【初始化配置-调度线程池】SchedulingExecutorConfigure ... 已初始化完毕。");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(applicationTaskExecutor());
    }

    @Bean(destroyMethod = "shutdown",name = "applicationTaskExecutor")
    public Executor applicationTaskExecutor() {
        return Executors.newScheduledThreadPool(5, new NamedThreadFactory("sf-schedule-", false));
    }

}
