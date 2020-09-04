package ai.yue.library.data.mp.config;

import ai.yue.library.data.mp.config.properties.YueMybatisPlusProperties;
import ai.yue.library.data.mp.handler.MyMetaObjectHandler;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.List;

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
    @Order(1)
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    @ConditionalOnMissingBean(MyMetaObjectHandler.class)
    @ConditionalOnProperty(prefix = "yue.mybatis-plus", name = "autoFill", havingValue = "true", matchIfMissing = true)
    @Order(2)
    public MyMetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }

    @Bean
    @Order(3)
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


//    @Bean
//    public EncryptServiceProxy encryptServiceProxy(@Value("${tass.key.index}")Integer keyIndex,
//                                                   @Value("${tass.cfg.path}")String cfgPath){
//        EncryptServiceProxy pxy = new EncryptServiceProxy();
//        pxy.setEncryptService(PlatformEncryptService.getInstance(keyIndex,cfgPath));
//        return pxy;
//    }
}
