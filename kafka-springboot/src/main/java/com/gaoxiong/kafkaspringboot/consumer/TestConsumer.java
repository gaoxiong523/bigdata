package com.gaoxiong.kafkaspringboot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @author gaoxiong
 * @ClassName TestConsumer
 * @Description TODO
 * @date 2020/2/9 0009 下午 10:28
 */
@Component
@Slf4j
public class TestConsumer {

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "gaoxiong",partitions = "0")
    })
    public void consumer0( String cr ){
        log.info("+++++++++++partition0++++++++"+cr.toString());
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "gaoxiong",partitions = "1")
    })
    public void consumer1( String cr ){
        log.info("++++++++++partition1+++++++"+cr.toString());
    }


    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "gaoxiong",partitions = "2")
    })
    public void consumer2( String cr ){
        log.info("++++++++partition2++++++++"+cr.toString());
    }
}
