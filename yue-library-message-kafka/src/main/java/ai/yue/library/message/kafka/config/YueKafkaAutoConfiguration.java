package ai.yue.library.message.kafka.config;

import ai.yue.library.message.kafka.support.KafkaUtils;
import ai.yue.library.message.kafka.support.YueKafkaConsumerErrorHandler;
import ai.yue.library.message.kafka.support.YueKafkaProducer;
import ai.yue.library.message.kafka.support.YueLoggingProducerListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * Created on 2018/2/24.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@ConditionalOnClass({KafkaTemplate.class, EnableKafka.class})
@AutoConfigureAfter({KafkaAutoConfiguration.class})
public class YueKafkaAutoConfiguration {

    private final ObjectMapper objectMapper;

    public YueKafkaAutoConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public YueKafkaProducer yueKafkaProducer(KafkaTemplate<byte[], byte[]> kafkaTemplate,
                                             YueLoggingProducerListener<byte[], byte[]> yueLoggingProducerListener) {
        kafkaTemplate.setProducerListener(yueLoggingProducerListener);
        return new YueKafkaProducer(kafkaTemplate, objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public YueLoggingProducerListener<byte[], byte[]> yueLoggingProducerListener() {
        return new YueLoggingProducerListener<>();
    }

    @Bean
    @ConditionalOnMissingBean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter(objectMapper);
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        ContainerProperties containerProperties = factory.getContainerProperties();
        factory.setRecordFilterStrategy(yueMessageFilterStrategy());
        factory.setErrorHandler(new YueKafkaConsumerErrorHandler());
        factory.setMessageConverter(recordMessageConverter());
        configurer.configure(factory, kafkaConsumerFactory);
        return factory;
    }

    private RecordFilterStrategy<Object, Object> yueMessageFilterStrategy() {
        return (consumerRecord) -> {
            Object value = consumerRecord.value();
            String message;
            if (value instanceof byte[]) {
                message = KafkaUtils.getMessage((byte[]) value, "consumer filter", consumerRecord);
                log.info(message);
                return false;
            } else {
                log.error("Only byte[] or string supported");
                return true;
            }
        };
    }
}
