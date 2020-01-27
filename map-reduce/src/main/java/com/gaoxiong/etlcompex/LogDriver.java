package com.gaoxiong.etlcompex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author gaoxiong @ClassName LogDriver @Description TODO
 * @date 2019/12/17 0017 下午 8:08
 */
public class LogDriver {
  public static void main(String[] args)
      throws IOException, ClassNotFoundException, InterruptedException {
    //
    args =
        new String[] {
          "F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input7",
          "F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\output7"
        };
    Configuration conf = new Configuration();

    Path out = new Path(args[1]);
    FileSystem fileSystem = FileSystem.get(conf);
    if (fileSystem.exists(out)) {
      fileSystem.delete(out, true);
      System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
    }
    Job job = Job.getInstance(conf);
    job.setJarByClass(LogDriver.class);
    job.setMapperClass(LogMapper.class);

    job.setNumReduceTasks(0);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(NullWritable.class);

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, out);

    boolean b = job.waitForCompletion(true);
    System.exit(b ? 0 : 1);
  }
}
