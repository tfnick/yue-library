package ai.yue.library.data.redis;

import com.lzh.easythread.EasyThread;
import org.junit.BeforeClass;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class RedissionTestBase {

    EasyThread easyThread;

    RedissonClient redission;

    @BeforeClass
    public void init(){
        Config config = new Config();
        SingleServerConfig sconfig = config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redission = Redisson.create(config);
    }
    @BeforeClass
    public void destroy(){
        if (redission != null) {
            redission.shutdown();
        }
        if (easyThread != null && !easyThread.getExecutor().isShutdown()) {
            easyThread.getExecutor().shutdown();
        }
    }
}
