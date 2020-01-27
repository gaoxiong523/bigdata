package com.gaoxiong.flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName FlowBean
 * @Description TODO
 * @date 2019/11/18 0018 下午 10:40
 */
public class FlowBean implements Writable {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public FlowBean () {
    }

    public FlowBean ( Long upFlow, Long downFlow ) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public Long getUpFlow () {
        return upFlow;
    }

    public void setUpFlow ( Long upFlow ) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow () {
        return downFlow;
    }

    public void setDownFlow ( Long downFlow ) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow () {
        return sumFlow;
    }

    public void setSumFlow ( Long sumFlow ) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString () {
        return
                upFlow + "\t" + downFlow + "\t" + sumFlow;

    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    public void write ( DataOutput out ) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    public void readFields ( DataInput in ) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}
