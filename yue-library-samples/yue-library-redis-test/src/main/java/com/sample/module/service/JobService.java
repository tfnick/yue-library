package com.sample.module.service;

import com.sample.module.common.Const;
import com.sample.module.common.TaskException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class JobService {

    protected static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    ResourceManager resourceManager;
    @Autowired
    RedissonClient redisson;

    /**
     * 单线程中运行
     */
    @Scheduled(cron = "0/20 * * * * ?")
    @Async
    public void closeOrderTaskV3() {
        log.info("复杂任务执行开始");
        Integer result = lockAndDoJob();
        log.info("复杂任务执行结束");
    }

    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void testTaskPool() {
        log.info("简单任务执行开始");
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("简单任务执行结束");
    }

    /**
     * 线程池中运行
     */
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void closeOrderTaskV4() throws Exception {
//        Future<Integer> future = resourceManager.getTaskPool().submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return lockAndDoJob();
//            }
//        });
//        Integer result = future.get();
//        log.info("任务执行结果{}", result);
//    }

    private Integer lockAndDoJob(){
        RLock lock = redisson.getLock(Const.REDIS_LOCK.WX_ACCESS_TOKEN);
        boolean getLock = false;
        try {
            // 若任务执行时间过短，则有可能在等锁的过程中2个服务任务都会获取到锁，
            // 这与实际需要的功能不一致，故需要将waitTime设置为0
            // leaseTime，锁占用时间，一般需要保证锁占用时间大于任务执行时间
            if (getLock = lock.tryLock(0, 5, TimeUnit.SECONDS)) {
                log.info("Redisson获得分布式锁:{},ThreadName :{}", Const.REDIS_LOCK.WX_ACCESS_TOKEN, Thread.currentThread().getName());
                doTask();
                return 1;
            } else {
                log.info("Redisson分布式锁没有获取到锁:{},ThreadName :{}", Const.REDIS_LOCK.WX_ACCESS_TOKEN, Thread.currentThread().getName());
                return 0;
            }
        } catch (InterruptedException e) {
            log.error("Redisson获取分布式锁异常", e);
        } catch (TaskException e) {
            log.error("任务执行异常", e);
        } finally {
            if (!getLock) {
                return 0;
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁:{},ThreadName :{}", Const.REDIS_LOCK.WX_ACCESS_TOKEN, Thread.currentThread().getName());
        }
        return 0;
    }

    //核心业务逻辑
    private void doTask() throws TaskException {

        try {
            Thread.sleep(4 * 1000);
        } catch (InterruptedException e) {
            throw new TaskException();
        }
    }
}
