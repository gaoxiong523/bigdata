package com.gaoxiong.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * @author gaoxiong
 * @ClassName FlowReducer
 * @Description TODO
 * @date 2019/11/18 0018 下午 10:40
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {



    @Override
    protected void reduce ( Text key, Iterable<FlowBean> values, Context context ) throws IOException, InterruptedException {
         long totalUpFlow = 0;
         long totalDownFlow = 0;
        for (FlowBean value : values) {
            totalUpFlow += value.getUpFlow();
            totalDownFlow += value.getDownFlow();
        }

        FlowBean flowBean = new FlowBean(totalUpFlow, totalDownFlow);
        context.write(key,flowBean );

    }
}
