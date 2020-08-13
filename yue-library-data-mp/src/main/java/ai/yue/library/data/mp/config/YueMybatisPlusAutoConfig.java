package ai.yue.library.data.mp.config;

import ai.yue.library.data.mp.config.properties.YueMybatisPlusProperties;
import ai.yue.library.data.mp.handler.MyMetaObjectHandler;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(YueMybatisPlusProperties.class)
@AutoConfigureAfter({MybatisPlusAutoConfiguration.class})
public class YueMybatisPlusAutoConfig {



    @Autowired
    private void init(YueMybatisPlusProperties yueMybatisPlusProperties) {
        // debug only
        log.info("【yue-library-data-mp】配置完成 ... 已初始化完毕。");
    }

    @Bean
    @ConditionalOnMissingBean(OptimisticLockerInterceptor.class)
    @ConditionalOnProperty(prefix = "yue.mybatis-plus", name = "enableVersion", havingValue = "true", matchIfMissing = true)
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    @ConditionalOnMissingBean(MyMetaObjectHandler.class)
    @ConditionalOnProperty(prefix = "yue.mybatis-plus", name = "autoFill", havingValue = "true", matchIfMissing = true)
    public MyMetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }


}
