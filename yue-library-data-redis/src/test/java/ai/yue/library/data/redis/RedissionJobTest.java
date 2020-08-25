package ai.yue.library.data.redis;

import org.junit.Test;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class RedissionJobTest extends RedissionTestBase {

    private static Logger logger = LoggerFactory.getLogger(RedissionJobTest.class);

    @Test
    @Scheduled(cron = "* * 1 * * ?")
    public void testUpdateAccessToken(){
        easyThread.execute(new Runnable() {
            @Override
            public void run() {
                RLock lock = redission.getLock("lock_update_access_token");
                try {
                    if (lock.tryLock()) {
                        logger.info("获取成功，执行定时任务，更新AccessToken");
                    }
                } catch (Exception e) {
                    logger.error("error", e);
                }finally {
                    lock.unlock();
                }


            }
        });
    }
}
