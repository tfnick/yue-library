package com.sample;

import ai.yue.library.data.redis.annotation.Lock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class JobService {

    protected static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    RedissonClient redissonClient;

    String LOCK_KEY_WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";

    /**
     * 单线程中运行
     */
    @Scheduled(cron = "0/20 * * * * ?")
    public void closeOrderTaskV3() {
        log.info("-=代码加锁开始=-");
        Integer result = lockAndDoJob();
        log.info("-=代码加锁结束=-");
    }


    /**
     * 单线程中运行
     */
    @Scheduled(cron = "0/20 * * * * ?")
    @Lock(keys = "abc_key")
    public void closeOrderTaskV4() throws Exception{
        log.info("-=注解加锁开始=-");
        doJob();
        log.info("-=注解加锁结束=-");
    }


    private Integer lockAndDoJob(){
        RLock lock = redissonClient.getLock(LOCK_KEY_WX_ACCESS_TOKEN);
        boolean getLock = false;
        try {
            // 若任务执行时间过短，则有可能在等锁的过程中2个服务任务都会获取到锁，
            // 这与实际需要的功能不一致，故需要将waitTime设置为0
            // leaseTime，锁占用时间，一般需要保证锁占用时间大于任务执行时间
            if (getLock = lock.tryLock(0, 5, TimeUnit.SECONDS)) {
                log.info("Redisson获得分布式锁:{},ThreadName :{}", LOCK_KEY_WX_ACCESS_TOKEN, Thread.currentThread().getName());
                doJob();
                return 1;
            } else {
                log.info("Redisson分布式锁没有获取到锁:{},ThreadName :{}", LOCK_KEY_WX_ACCESS_TOKEN, Thread.currentThread().getName());
                return 0;
            }
        } catch (InterruptedException e) {
            log.error("Redisson获取分布式锁异常", e);
        } catch (Exception e) {
            log.error("任务执行异常", e);
        } finally {
            if (!getLock) {
                return 0;
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁:{},ThreadName :{}", LOCK_KEY_WX_ACCESS_TOKEN, Thread.currentThread().getName());
        }
        return 0;
    }

    //核心业务逻辑
    private void doJob() throws Exception {

        try {
            Thread.sleep(4 * 1000);
        } catch (InterruptedException e) {
            throw new Exception();
        }
    }
}
