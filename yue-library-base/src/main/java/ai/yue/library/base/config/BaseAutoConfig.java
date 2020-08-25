package ai.yue.library.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import ai.yue.library.base.annotation.api.version.ApiVersionProperties;
import ai.yue.library.base.config.datetime.DateTimeFormatConfig;
import ai.yue.library.base.config.handler.ExceptionHandlerProperties;
import ai.yue.library.base.config.http.HttpsRequestFactory;
import ai.yue.library.base.config.http.RestProperties;
import ai.yue.library.base.config.properties.CorsProperties;
import ai.yue.library.base.config.thread.pool.AsyncConfig;
import ai.yue.library.base.util.ApplicationContextUtils;
import ai.yue.library.base.util.SpringUtils;
import ai.yue.library.base.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLContext;

/**
 * base bean 自动配置
 * 
 * @author	ylyue
 * @since	2018年11月26日
 */
@Slf4j
@Configuration
@Import({ AsyncConfig.class, ApplicationContextUtils.class, SpringUtils.class, DateTimeFormatConfig.class })
@EnableConfigurationProperties({ ApiVersionProperties.class, ExceptionHandlerProperties.class, RestProperties.class,
		CorsProperties.class, })
public class BaseAutoConfig {
	//https://www.jianshu.com/p/08c5cad9c566
	// RestTemplate-HTTPS客户端
//	@Bean
//	public HttpClient httpClient() {
//		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//				.register("http", PlainConnectionSocketFactory.getSocketFactory())
//				.register("https", SSLConnectionSocketFactory.getSocketFactory())
//				.build();
//		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
//		//设置整个连接池最大连接数 根据自己的场景决定
//		connectionManager.setMaxTotal(200);
//		//路由是对maxTotal的细分
//		connectionManager.setDefaultMaxPerRoute(100);
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setSocketTimeout(10000) //服务器返回数据(response)的时间，超过该时间抛出read timeout
//				.setConnectTimeout(5000)//连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
//				.setConnectionRequestTimeout(1000)//从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
//				.build();
//		return HttpClientBuilder.create()
//				.setDefaultRequestConfig(requestConfig)
//				.setConnectionManager(connectionManager)
//				.build();
//	}



//	public HttpComponentsClientHttpRequestFactory generateHttpsRequestFactory() {
//		try {
//
//			CloseableHttpClient httpClient = httpClientBuilder.build();
//			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//			factory.setHttpClient(httpClient);
//			factory.setConnectTimeout(10 * 1000);
//			factory.setReadTimeout(30 * 1000);
//			return factory;
//		} catch (Exception e) {
//			throw new RuntimeException("创建HttpsRestTemplate失败", e);
//		}
//
//	}
	
	@Bean
	@ConditionalOnMissingBean
    public RestTemplate restTemplate(RestProperties restProperties){
    	HttpsRequestFactory factory = new HttpsRequestFactory();
    	
    	// 设置链接超时时间
    	Integer connectTimeout = restProperties.getConnectTimeout();
		if (connectTimeout != null) {
			factory.setConnectTimeout(connectTimeout);
		}
		
    	// 设置读取超时时间
    	Integer readTimeout = restProperties.getReadTimeout();
    	if (readTimeout != null) {
    		factory.setReadTimeout(readTimeout);
    	}
    	
		log.info("【初始化配置-HTTPS客户端】Bean：RestTemplate ... 已初始化完毕。");
        return new RestTemplate(factory);
    }
	
	// Validator-校验器
	
	@Bean
	@ConditionalOnMissingBean
    public Validator validator(){
		log.info("【初始化配置-校验器】Bean：Validator ... 已初始化完毕。");
        return new Validator();
    }
	
}
