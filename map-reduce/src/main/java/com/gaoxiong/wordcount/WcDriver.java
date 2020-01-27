package com.gaoxiong.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName WcDriver
 * @Description TODO
 * @date 2019/11/18 0018 下午 8:45
 */
public class WcDriver {
    public static void main ( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
        //1.获取一个job实例
        Job job = Job.getInstance(new Configuration());

        //2.设置类路劲
        job.setJarByClass(WcDriver.class);
        //3.设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReduce.class);

        //4.设置Mapper和Reducer输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //5.设置输入输出数据

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //6.提交我们的job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
