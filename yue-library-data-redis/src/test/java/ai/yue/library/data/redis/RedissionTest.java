package ai.yue.library.data.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RedissionTest {

    private static final Logger log = LoggerFactory.getLogger(RedissionTest.class);

    RedissonClient redisson = null;

    String prefix = "lwx_";

    @Before
    public void init(){
        System.out.println("初始客户端");
        // 1. Create config object
        Config config = new Config();
        config.useSentinelServers().addSentinelAddress("redis://10.118.238.66:26379")
                .addSentinelAddress("redis://10.118.238.48:26379")
                .addSentinelAddress("redis://10.118.238.71:26379")
                .setMasterName("mymaster")
                .setPassword("myredis");

        redisson = Redisson.create(config);
    }

    @After
    public void close(){
        System.out.println("关闭客户端");
        if (redisson != null) {
            redisson.shutdown();
        }
    }

    @Test
    public void testKeyValue(){
        RBucket<String> stringKey = redisson.getBucket(prefix + "stringKey");
        stringKey.set("A String Value");

        RBucket<Integer> intKey = redisson.getBucket(prefix + "integerKey");
        intKey.set(1000);

    }

    public void testMap(){
        RMap<String, String> map = redisson.getMap(prefix + "mapKey");
        map.put("a", "100");
    }

    public void testList(){
        RList<String> list = redisson.getList(prefix + "listKey");
        list.add("100");
        list.expire(5, TimeUnit.SECONDS);
    }



    public void testIncr(){
        RAtomicLong atomicInteger = redisson.getAtomicLong(prefix + "counter");
        long result = atomicInteger.addAndGet(100);
    }

    public void testLock() throws Exception{
        String lockKey = prefix + "recordId";
        RLock lock = redisson.getLock(lockKey);
        try {
            boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
            if (bs) {
                // 业务代码
                log.info("进入业务代码: " + lockKey);

                lock.unlock();
            } else {
                Thread.sleep(300);
            }
        } catch (Exception e) {
            log.error("", e);
            lock.unlock();
        }
    }

    @Test
    @Scheduled(cron = "")
    public void testJob(){

    }

    public void testMessage(){

    }
}
