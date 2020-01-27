package com.gaoxiong.partition;

import com.gaoxiong.flow.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author gaoxiong @ClassName MyPartitioner @Description TODO
 * @date 2019/11/25 0025 下午 10:50
 */
public class MyPartitioner extends Partitioner<Text, FlowBean> {
  @Override
  public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
    String phone = text.toString();
    switch (phone.substring(0, 3)) {
      case "136":
        return 0;
      case "137":
        return 1;
      case "138":
        return 2;
      case "139":
        return 3;
      default:
        return 4;
    }
  }
}
