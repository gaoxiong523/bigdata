package com.gaoxiong.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.ProducerInterceptors;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author gaoxiong
 * @ClassName ConsumerProducer
 * @Description TODO
 * @date 2020/2/12 0012 下午 4:42
 */
public class ConsumerProducer {

    static final String brokerList = "192.168.150.134:9092";
    static final String topic = "first";

    /**
     * 初始化参数
     *
     * @return
     */
    private static Properties initProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        properties.put(ProducerConfig.ACKS_CONFIG,"all" );
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384 );
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1 );

        List<String> list = new ArrayList<>();
        //拦截器调用的顺序,按照添加的先后顺序执行
        list.add("com.gaoxiong.kafka.interceptor.CounterInterceptor");
        list.add("com.gaoxiong.kafka.interceptor.TimeInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,list );

        return properties;
    }

  public static void main(String[] args) {
    //
      KafkaProducer<String, String> producer = new KafkaProducer<>(initProperties());
      for (int i = 0; i < 1000; i++) {
          ProducerRecord<String, String> record =
                  new ProducerRecord<String, String>(topic, "hello kafka = " + i);
          producer.send(
                  record,
                  (metadata, exception) -> {
                      if (exception == null) {
                          System.out.println("发送成功");
                      } else {
                          System.out.println("发送失败,记录日志");
                          System.out.println("消息当前的offset++++" + metadata.offset());
                          System.out.println("消息的partition++++" + metadata.partition());
                      }
                  });
      }
      producer.close();


  }
}
