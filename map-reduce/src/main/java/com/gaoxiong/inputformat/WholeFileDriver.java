package com.gaoxiong.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName WholeFileDriver
 * @Description TODO
 * @date 2019/11/20 0020 下午 10:22
 */
public class WholeFileDriver {
    public static void main ( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {

        args = new  String[]{"F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input3","F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\output3"};
        Configuration conf = new Configuration();

        Path out = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(conf);
        if (fileSystem.exists(out)) {
            fileSystem.delete(out, true);
            System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
        }
        Job job = Job.getInstance(conf);

        job.setJarByClass(WholeFileDriver.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);


        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,out );

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
