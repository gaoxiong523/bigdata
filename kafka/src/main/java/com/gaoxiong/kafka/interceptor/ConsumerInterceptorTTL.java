package com.gaoxiong.kafka.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

/**
 * @author gaoxiong
 * @ClassName ConsumerInterceptorTTL
 * @Description TODO
 * @date 2020/2/11 0011 下午 11:45
 */
public class ConsumerInterceptorTTL implements ConsumerInterceptor {
    @Override
    public ConsumerRecords onConsume ( ConsumerRecords records ) {
        return null;
    }

    @Override
    public void close () {

    }

    @Override
    public void onCommit ( Map offsets ) {

    }

    @Override
    public void configure ( Map<String, ?> configs ) {

    }
}
