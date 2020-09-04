package ai.yue.library.data.mp.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yue.mybatis-plus")
public class YueMybatisPlusProperties {

    //是否启用乐观锁版本控制
    private Boolean enableVersion = Boolean.TRUE;
    //自动填充create_time,update_time,version
    private Boolean autoFill = Boolean.TRUE;
    //开启字段加密功能
    private Boolean encrypt = Boolean.FALSE;
    //加密机cfg.ini配置文件路径
    private String iniFullPath = "/tomcat/data/cfg.ini";

}
