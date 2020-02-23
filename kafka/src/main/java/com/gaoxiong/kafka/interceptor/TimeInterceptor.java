package com.gaoxiong.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author gaoxiong
 * @ClassName TimeInterceptor
 * @Description TODO
 * @date 2020/2/12 0012 下午 4:29
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {
    @Override
    public ProducerRecord<String, String> onSend ( ProducerRecord<String, String> record ) {

    ProducerRecord<String, String> record1 =
        new ProducerRecord<String, String>(
            record.topic(),
            record.partition(),
            record.key(),
            System.currentTimeMillis() + record.value(),
            record.headers());
        return record1;
    }

    @Override
    public void onAcknowledgement ( RecordMetadata metadata, Exception exception ) {

    }

    @Override
    public void close () {

    }

    @Override
    public void configure ( Map<String, ?> configs ) {

    }
}
