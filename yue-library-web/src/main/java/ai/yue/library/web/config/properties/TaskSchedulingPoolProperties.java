package ai.yue.library.web.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yue.thread-pool.schedule")
public class TaskSchedulingPoolProperties {

    private boolean enabled = false;
    private int coreSize = 5;
}
