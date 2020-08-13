package ai.yue.library.data.mp.config;

import ai.yue.library.base.util.StringUtils;
import ai.yue.library.data.mp.config.properties.YueMybatisPlusProperties;
import cn.hutool.core.lang.Singleton;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(YueMybatisPlusProperties.class)
@AutoConfigureAfter({MybatisPlusAutoConfiguration.class})
public class YueMybatisPlusConfig {

    @Autowired
    private void init(YueMybatisPlusProperties yueMybatisPlusProperties) {
        // debug only
        String echo = yueMybatisPlusProperties.getEcho();
        if (StringUtils.isNotEmpty(echo)) {
            log.info("【yue-library-data-mp】配置完成 ... 已初始化完毕。");
        }
    }
}
