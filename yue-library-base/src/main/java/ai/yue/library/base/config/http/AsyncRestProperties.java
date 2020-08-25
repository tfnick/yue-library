package ai.yue.library.base.config.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yue.rest.async")
public class AsyncRestProperties {
    /**
     * 启用AsyncRestTemplate
     */
    private Boolean enable = false;
}
