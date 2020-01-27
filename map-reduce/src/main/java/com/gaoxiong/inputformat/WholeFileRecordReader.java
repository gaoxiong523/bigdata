package com.gaoxiong.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName WholeFileRecordReader
 * @Description TODO
 * @date 2019/11/20 0020 下午 8:39
 */
public class WholeFileRecordReader extends RecordReader<Text, BytesWritable> {


    private FileSystem fileSystem;

    private Text key = new Text();
    private BytesWritable value = new BytesWritable();
    private FSDataInputStream inputStream;
    FileSplit fileSplit;

    private boolean readed = false;

    @Override
    public void initialize ( InputSplit split, TaskAttemptContext context ) throws IOException, InterruptedException {
        fileSplit = (FileSplit) split;
        Path path = fileSplit.getPath();
        fileSystem = FileSystem.get(context.getConfiguration());
        inputStream = fileSystem.open(path);
    }

    @Override
    public boolean nextKeyValue () throws IOException, InterruptedException {
        if (!readed) {
            //读key
            key.set(fileSplit.getPath().toString());
            // 读value
            byte[] bytes = new byte[(int) fileSplit.getLength()];
            IOUtils.readFully(inputStream,bytes,0 ,bytes.length );
            value.set(bytes, 0, bytes.length);
            readed = true;
            return true;
        }
        return false;
    }

    @Override
    public Text getCurrentKey () throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue () throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress () throws IOException, InterruptedException {
        return readed ? 1 : 0;
    }

    @Override
    public void close () throws IOException {
        IOUtils.closeStream(inputStream);
    }
}
