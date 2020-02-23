package com.gaoxiong.kafkaspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class KafkaSpringbootApplicationTests {

  @Autowired
  private KafkaTemplate kafkaTemplate;

  @Test
  void contextLoads() {}

  @Test
  public void producer(){

    for (int i = 0; i < 100; i++) {
      kafkaTemplate.send("gaoxiong", "你好,gaoxiong + "+i);

    }

  }
}
