package com.gaoxiong.kafka.quickstart;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author gaoxiong @ClassName QuickStartProducer kafka生产者, 主要三大组件, 序列化器,必须配置 分区器,选配 生产者拦截器 选配
 *     全部配置的情况下执行顺序为 生产者拦截器 ->序列化器->分区器
 * @date 2020/2/8 0008 下午 11:27
 */
public class QuickStartProducer {

  static final String brokerList = "192.168.150.134:9092";
  static final String topic = "ProducerQuickStart";

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

    return properties;
  }

  public static void main(String[] args) throws InterruptedException {
    // 构建producer实例
    KafkaProducer<String, String> producer = new KafkaProducer<>(initProperties());
    for (int i = 100; i < 105; i++) {
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
      Thread.sleep(5000);
    }
    producer.close();
  }
}
