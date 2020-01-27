package com.gaoxiong.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName FlowMapper
 * @Description TODO
 * @date 2019/11/18 0018 下午 10:40
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private Text phone = new Text();
    private FlowBean flowBean = null;

    @Override
    protected void map ( LongWritable key, Text value, Context context ) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        phone = new Text(fields[1]);
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);
        flowBean = new FlowBean(upFlow, downFlow);
        context.write(phone,flowBean );
    }
}
