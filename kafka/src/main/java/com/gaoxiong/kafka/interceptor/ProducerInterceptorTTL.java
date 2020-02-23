package com.gaoxiong.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author gaoxiong
 * @ClassName ProducerInterceptorTTL
 * @Description TODO
 * @date 2020/2/11 0011 下午 11:47
 */
public class ProducerInterceptorTTL implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend ( ProducerRecord record ) {
        return null;
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
