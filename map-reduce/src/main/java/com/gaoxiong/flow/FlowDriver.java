package com.gaoxiong.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName FlowDriver
 * @Description TODO
 * @date 2019/11/18 0018 下午 10:40
 */
public class FlowDriver {
    public static void main ( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Path out = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(conf);
        if (fileSystem.exists(out)) {
            fileSystem.delete(out, true);
            System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
        }

        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowDriver.class);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, out);

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);


    }
}
