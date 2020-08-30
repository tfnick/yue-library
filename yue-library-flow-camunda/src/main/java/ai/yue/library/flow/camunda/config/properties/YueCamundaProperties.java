package ai.yue.library.flow.camunda.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "camunda.bpm.external")
public class YueCamundaProperties {
    //外部任务url地址
    private String serverUrl;
    //外部任务轮询间隔 单位毫秒
    private Integer interval = 30000;

}
