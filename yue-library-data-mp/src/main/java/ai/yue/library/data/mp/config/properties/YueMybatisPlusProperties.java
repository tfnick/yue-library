package ai.yue.library.data.mp.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yue.mybatis-plus")
public class YueMybatisPlusProperties {

    private String echo = "start yue libary-data-mp success";


}
