package ai.yue.library.data.redis.config;
import org.redisson.Redisson;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis自动配置
 * 
 * @author	ylyue
 * @since	2018年6月11日
 */
@Slf4j
@Configuration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableConfigurationProperties({RedissonProperties.class})
public class YueRedissonAutoConfig {

	@Autowired(required = false)
	private List<RedissonAutoConfigurationCustomizer> redissonAutoConfigurationCustomizers;


	@Autowired
	RedissonProperties redissonProperties;

	@Autowired
	private ApplicationContext ctx;

	/**
	 * 此Bean应该不会执行，应为上级配置已经完成bean的构造,后续再考虑是否增加plus的功能
	 * @return
	 * @throws IOException
	 */
	@Bean(destroyMethod = "shutdown")
	@ConditionalOnMissingBean(RedissonClient.class)
	public RedissonClient redisson() throws IOException {
		Config config = null;
		if (redissonProperties.getConfig() != null) {
			try {
				InputStream is = getConfigStream();
				config = Config.fromJSON(is);
			} catch (IOException e) {
				// trying next format
				try {
					InputStream is = getConfigStream();
					config = Config.fromYAML(is);
				} catch (IOException e1) {
					throw new IllegalArgumentException("Can't parse config", e1);
				}
			}
		}
		if (redissonAutoConfigurationCustomizers != null) {
			for (RedissonAutoConfigurationCustomizer customizer : redissonAutoConfigurationCustomizers) {
				customizer.customize(config);
			}
		}
		return Redisson.create(config);
	}

	private String[] convert(List<String> nodesObject) {
		List<String> nodes = new ArrayList<String>(nodesObject.size());
		for (String node : nodesObject) {
			if (!node.startsWith("redis://") && !node.startsWith("rediss://")) {
				nodes.add("redis://" + node);
			} else {
				nodes.add(node);
			}
		}
		return nodes.toArray(new String[nodes.size()]);
	}

	private InputStream getConfigStream() throws IOException {
		Resource resource = ctx.getResource(redissonProperties.getConfig());
		InputStream is = resource.getInputStream();
		return is;
	}


}
