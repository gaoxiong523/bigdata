package com.gaoxiong.flume;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.source.AbstractSource;

/**
 * @author gaoxiong
 * @ClassName MySource
 * @Description TODO
 * @date 2020/2/7 0007 下午 11:32
 */
public class MySource extends AbstractSource implements PollableSource,Configurable {
    //定义需要 从配置中读取的字段

    //两条数据之间的间隔
    private long delay;
    private String fields;

    public Status process () throws EventDeliveryException {
        return null;
    }

    public long getBackOffSleepIncrement () {
        return 0;
    }

    public long getMaxBackOffSleepInterval () {
        return 0;
    }

    public void configure ( Context context ) {
    delay = context.getLong("delay",2000L);
    fields = context.getString("field", "gaoxiong");
    }
}
