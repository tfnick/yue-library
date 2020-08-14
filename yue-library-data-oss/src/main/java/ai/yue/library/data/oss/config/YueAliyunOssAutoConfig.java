package ai.yue.library.data.oss.config;


import ai.yue.library.data.oss.config.properties.YueAliyunOssProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.OssAcsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(YueAliyunOssProperties.class)
public class YueAliyunOssAutoConfig {

    @Autowired
    private void init(YueAliyunOssProperties yueAliyunOssProperties) {
        log.info("【yue-library-data-oss】配置完成 ... 已初始化完毕。");
    }

    @Bean(name="oss",destroyMethod="shutdown")
    @ConditionalOnBean({ YueAliyunOssProperties.class})
    public OSS oss(YueAliyunOssProperties yueAliyunOssProperties){
        String endpoint = yueAliyunOssProperties.getEndpoint();
        String accessKeyId = yueAliyunOssProperties.getAccessKeyId();
        String accessKeySecret = yueAliyunOssProperties.getAccessKeySecret();
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
