package ai.yue.library.flow.camunda.configure;

import ai.yue.library.flow.camunda.plugins.ProgressSupportParseListenerPlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/6/12 16:26
 */
//@Configuration
//@Profile("camunda")
public class CamundaConfiguration {

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
