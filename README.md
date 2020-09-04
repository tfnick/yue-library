
### TODO base

- validator 待简化
- fastjson && jsonpath

### 相关技术点

#### 后端技术

- [抖音auth2.o的access_token生命周期](https://www.it610.com/article/1287591770242199552.htm)
- [微信公众号auth2.0的access_token的更新]()
- [阿里云Oss计费规则](https://help.aliyun.com/document_detail/59636.html?spm=a2c4g.11186623.6.570.6ba1218cHaht0O)
- [阿里云Oss Web端上传流程](https://help.aliyun.com/document_detail/31926.html?spm=a2c4g.11186623.6.643.75a76212ODpfe2)
- [阿里云Oss 视频截图生成](https://help.aliyun.com/document_detail/64555.html?spm=a2c4g.11186623.6.1743.1f65218ctlXyqO)
- [阿里云Oss 如何(自动)删除](https://developer.aliyun.com/ask/206686)
- [网络图文如何发布到微信-重要](https://www.cnblogs.com/gogood/p/6544439.html)
- [使用redis+@scheduled实现简单任务调度,解决access_token刷新问题](https://www.cnblogs.com/slowcity/p/11671231.html)
- [@scheduled简单调度的问题](https://www.cnblogs.com/muxi0407/p/11936221.html)
- [使用redission操作redis](https://www.baeldung.com/redis-redisson)


#### app认证机制

- 百家号 app_id 及 app_token
- 微信公众号 access_token(refresh_token)
- 抖音 access_token(refresh_token)

#### 前端编辑器

- [秀米编辑器对接](https://r.xiumi.us/board/v5/2a5va/16516964)
- [vue1](https://www.bilibili.com/video/BV11s411A7h6/?spm_id_from=333.788.videocard.0)
- [vue2](https://www.runoob.com/vue2/vue-tutorial.html)
- [html js css](https://www.bilibili.com/video/av96953550/?spm_id_from=333.788.b_636f6d6d656e74.29)

#### 费用

- kafka- ￥ 19,002元/年
- redis,1G 单节点 ￥ 540元/年
- ecs-共享标准型 s6 	ecs.s6-c1m2.large	2 vCPU	4 GiB	-	2.5 GHz/3.2 GHz	0.2 Gbps	20 万 PPS	-	是	1M 带宽 ￥ 1400.0 /年
- ecs-共享标准型 g6e  	ecs.g6e.large	4 vCPU	8 GiB	2.5 GHz/3.2 GHz	1.2 Gbps	90 万 PPS	2.1 万	是	1M 带宽 ￥ 2500.0 /月
- mysql-通用型 rds.mysql.s3.large 4C 8GB（通用型） 2000 5000  ¥ 10914.00/年
- mysql-通用型 rds.mysql.s3.large 2C 4GB（通用型） 2000 5000  ¥ 6300.00/年
- mysql-通用型 rds.mysql.s3.large 1C 2GB（通用型） 2000 5000  ¥ 4406.00/年
- oss-1Tb ￥ 999/年（单指存储）

#### 推荐

- 网络入口[EIP(弹性IP) + SLB负载均衡] + ecs服务器(2c4G) + ecs自建mysql(2c4G->后期转RDS) + 云redis(256M) + oss(1T)

#### 近期优秀源码阅读推荐

- [hadoop学习之路](https://developer.aliyun.com/article/28400)
- [hadoop0.23.0](http://blog.sina.com.cn/s/blog_4a1f59bf01010i9r.html)
- [redission](https://github.com/redisson/redisson/wiki/Redisson%E9%A1%B9%E7%9B%AE%E4%BB%8B%E7%BB%8D)
- [redisson-spring-boot-starter](https://github.com/tfnick/redisson-spring-boot-starter.git)
- [xxl-job](https://github.com/xuxueli/xxl-job)
- [kafka-client](https://github.com/apache/kafka)
- [spring-kafka](https://github.com/spring-projects/spring-kafka.git)
- [spring-boot-starter-kafka](https://github.com/spring-projects/spring-boot)
- [kafka](https://github.com/apache/kafka)
- [flink](https://github.com/apache/flink)
- [camunda]
- [QlExpress]

#### spring framework extension

- [InitializingBean-针对单个Bean]
- [BeanPostProcessor-针对spring上下文所有bean-1](https://www.cnblogs.com/twelve-eleven/p/8080038.html)
- [BeanPostProcessor-针对spring上下文所有bean-2](https://www.jianshu.com/p/369a54201943)
- DisposableBean 
- CommandLineRunner 
- @PostConstruct
- @PreDestroy
- [AutoCloseable与try(){}catch{}](https://www.cnblogs.com/756623607-zhang/p/9216091.html)


#### 架构文章

- [挖财资深架构师：如何建立基于大数据的信贷审批系统](https://www.sohu.com/a/114837765_355140)
- [图解消息队列用途](https://my.oschina.net/u/4364157/blog/4283168)
- [【Kafka】消费者客户端小结](https://xie.infoq.cn/article/8e54c04a74f301d4685c1c20d)
- [【Kafka】生产者客户端小结](https://xie.infoq.cn/article/934a746dc0458e24a03340e6b)
- [工作流与pipeline区别](https://www.zhihu.com/question/52498881/answer/923260493)
- [阿里云vpc与传统架构DCN区、DMZ区的区别]()
- [docker swarm 网络模型1](https://www.jianshu.com/p/60bccbdb6af9)
- [docker swarm 网络模型2](https://juejin.im/post/6844903958222209038)
- [RPC架构选型](https://www.jianshu.com/p/b0343bfd216e)
- [模式细品](https://www.cnblogs.com/jackson-yqj/p/7784694.html)

#### 其他

- [Sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)
- [Robbin](https://blog.csdn.net/qq_27520051/article/details/103037496)
- [Skywalking - 1](https://gitee.com/OpenSkywalking/sky-walking)
- [Skywalking - 2](https://skyapm.github.io/document-cn-translation-of-skywalking/zh/8.0.0/)