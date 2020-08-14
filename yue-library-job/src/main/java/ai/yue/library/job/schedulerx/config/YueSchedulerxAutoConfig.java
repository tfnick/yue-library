package ai.yue.library.job.schedulerx.config;

import ai.yue.library.job.schedulerx.config.propertes.YueSchedulerxProperties;
import com.alibaba.schedulerx.SchedulerxAutoConfigure;
import com.alibaba.schedulerx.SchedulerxProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(SchedulerxProperties.class)
@EnableConfigurationProperties(YueSchedulerxProperties.class)
@AutoConfigureAfter({SchedulerxAutoConfigure.class})
public class YueSchedulerxAutoConfig {

}
