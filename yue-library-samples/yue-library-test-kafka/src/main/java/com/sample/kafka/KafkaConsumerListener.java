package com.sample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/2/25.
 */
@Slf4j
@Service
public class KafkaConsumerListener {

  @KafkaListener(topics = "test_topic")
  public void consumerTestTopicMessage(DemoInfo message) {
    log.info(message.toString());
  }
}
