package com.gaoxiong.groupingcomparator;

import com.gaoxiong.flow.FlowBean;
import com.gaoxiong.flow.FlowDriver;
import com.gaoxiong.flow.FlowMapper;
import com.gaoxiong.flow.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author gaoxiong
 * @ClassName OrderDriver
 * @Description TODO
 * @date 2019/11/27 0027 下午 10:23
 */
public class OrderDriver {
  public static void main(String[] args)throws Exception {
    //
      args = new  String[]{"F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input5","F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\output5"};

      Configuration conf = new Configuration();

      Path out = new Path(args[1]);

      FileSystem fileSystem = FileSystem.get(conf);
      if (fileSystem.exists(out)) {
          fileSystem.delete(out, true);
          System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
      }

      Job job = Job.getInstance(conf);

      job.setJarByClass(OrderDriver.class);

      job.setMapperClass(OrderMapper.class);
      job.setReducerClass(OrderReducer.class);

      job.setMapOutputKeyClass(OrderBean.class);
      job.setMapOutputValueClass(NullWritable.class);

      job.setGroupingComparatorClass(OrderComparator.class);

      job.setOutputKeyClass(OrderBean.class);
      job.setOutputValueClass(NullWritable.class);

      FileInputFormat.setInputPaths(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, out);

      boolean b = job.waitForCompletion(true);
      System.exit(b ? 0 : 1);

  }
}
