package com.gaoxiong.inputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName WholeFileInputFormat
 * @Description TODO
 * @date 2019/11/20 0020 下午 8:34
 */
public class WholeFileInputFormat  extends FileInputFormat<Text,BytesWritable> {

    @Override
    protected boolean isSplitable ( JobContext context, Path filename ) {
        return false;
    }

    @Override
    public RecordReader<Text, BytesWritable> createRecordReader ( InputSplit split, TaskAttemptContext context ) throws IOException, InterruptedException {
        return new WholeFileRecordReader();
    }
}
