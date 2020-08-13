package ai.yue.library.data.mp.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yue.mybatis-plus")
public class YueMybatisPlusProperties {

    //是否启用乐观锁版本控制
    private Boolean enableVersion = Boolean.FALSE;
    //自动填充create_time,update_time,version
    private Boolean autoFill = Boolean.TRUE;


}
