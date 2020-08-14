package ai.yue.library.data.oss.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yue.aliyun-oss")
public class YueAliyunOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;



}
