package com.gaoxiong.hbase.hbase_mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.mapred.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * @author gaoxiong @ClassName HbaseMapperReduceTool @Description TODO
 * @date 2020/2/20 0020 下午 5:59
 */
public class HbaseMapperReduceTool implements Tool {
  @Override
  public int run(String[] strings) throws Exception {
    Job job = Job.getInstance();
    job.setJarByClass(HbaseMapperReduceTool.class);
    // mapper
//    TableMapReduceUtil.initTableMapJob();
    // reducer
//    org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil.initTableReducerJob(, , , , );

    // input

    // out

    // 执行
    boolean flag = job.waitForCompletion(true);
    return flag ? JobStatus.State.SUCCEEDED.getValue() : JobStatus.State.FAILED.getValue();
  }

  @Override
  public void setConf(Configuration configuration) {}

  @Override
  public Configuration getConf() {
    return null;
  }
}
