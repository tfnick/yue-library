package com.sample.kafka;

import ai.yue.library.message.kafka.support.YueKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/12/29.
 */
@SpringBootApplication
@EnableKafka
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Slf4j
  @RestController
  public static class KafkaController {
    

    @Autowired
    private YueKafkaProducer yueKafkaProducer;

    @GetMapping(value = "/send")
    public Map send() {
      ListenableFuture<SendResult<byte[], byte[]>> future = yueKafkaProducer
          .send(Topic.TOPIC_NAME, DemoInfo.builder().name("loc").age(123).id(1000).score(100).build());
      future.addCallback(
          (result) -> log.info("send message success"),
          (e) -> log.error(e.getMessage(), e)
      );
      Map ret = new HashMap();

      return ret;
    }
  }



}
