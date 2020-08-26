package ai.yue.library.job.schedulerx.config.propertes;

import com.alibaba.schedulerx.SchedulerxAutoConfigure;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.schedulerx2")
public class YueSchedulerxProperties extends SchedulerxAutoConfigure {
}
