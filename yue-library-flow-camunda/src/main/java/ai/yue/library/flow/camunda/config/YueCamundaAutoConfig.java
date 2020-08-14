package ai.yue.library.flow.camunda.config;

import ai.yue.library.data.mp.config.YueMybatisPlusAutoConfig;
import ai.yue.library.flow.camunda.config.properties.YueCamundaProperties;
import ai.yue.library.flow.camunda.plugins.ProgressSupportParseListenerPlugin;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/6/12 16:26
 */
@Slf4j
@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(YueCamundaProperties.class)
@AutoConfigureAfter({YueMybatisPlusAutoConfig.class})
public class YueCamundaAutoConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Order(Ordering.DEFAULT_ORDER + 1)
    public static ProcessEnginePlugin myCustomConfiguration() {
        return new ProgressSupportParseListenerPlugin();
    }

}
