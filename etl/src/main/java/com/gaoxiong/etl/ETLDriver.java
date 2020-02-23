package com.gaoxiong.etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author gaoxiong @ClassName ETLDriver @Description TODO
 * @date 2020/1/28 0028 下午 10:11
 */
public class ETLDriver {
  public static void main(String[] args) throws Exception {
    //
    Configuration conf = new Configuration();

    Path out = new Path(args[1]);
    FileSystem fileSystem = FileSystem.get(conf);
    if (fileSystem.exists(out)) {
      fileSystem.delete(out, true);
      System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
    }
    Job job = Job.getInstance(conf);
    job.setJarByClass(ETLDriver.class);
    job.setMapperClass(ETLMapper.class);

    job.setNumReduceTasks(0);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(NullWritable.class);

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, out);

    boolean b = job.waitForCompletion(true);
    System.exit(b ? 0 : 1);
  }
}
