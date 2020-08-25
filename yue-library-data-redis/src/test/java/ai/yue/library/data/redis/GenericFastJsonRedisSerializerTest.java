package ai.yue.library.data.redis;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.junit.Test;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.util.HashMap;
import java.util.Map;

public class GenericFastJsonRedisSerializerTest {

    @Test
    public void testSerializer() throws Exception{
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        String source = "I am 中国人";
        Map map = new HashMap();
        map.put("key", "value");
        map.put("area", "shenzhen");
        byte[] temp = serializer.serialize(source);
        byte[] temp_ = source.getBytes("UTF-8");
        String reTemp = (String) serializer.deserialize(temp);

        Assert.isTrue(source.equals(reTemp));
        System.out.println("1--->" + new String(temp));
        System.out.println("2--->" + new String(temp_));

        byte[] temp2 = serializer.serialize(map);
        Map reTemp2 = (Map)serializer.deserialize(temp2);

        Assert.isTrue(reTemp2.size() == 2);
        System.out.println("3--->" + new String(temp2));

        byte[] temp3 = serializer.serialize(1);
        Integer reTemp3 = (Integer)serializer.deserialize(temp3);

        Assert.isTrue(Long.valueOf(new String(temp3)).intValue() == 1);

        System.out.println("DONE");
    }
}
