package com.gaoxiong.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gaoxiong @ClassName CounterInterceptor @Description TODO
 * @date 2020/2/12 0012 下午 4:38
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {
    private long successsNum = 0 ;
    private long errorNum = 0;
  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
    return record;
  }

  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    if (exception == null) {
      successsNum++;
    } else {
        errorNum++;
    }
  }

  @Override
  public void close() {
    System.out.println("successNum===="+successsNum );
    System.out.println("errorNum===="+errorNum );
  }

  @Override
  public void configure(Map<String, ?> configs) {}
}
