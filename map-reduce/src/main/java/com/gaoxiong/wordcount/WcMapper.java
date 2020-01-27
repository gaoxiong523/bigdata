package com.gaoxiong.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName WcMapper
 * @Description TODO
 * @date 2019/11/18 0018 下午 8:44
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();
    private IntWritable one = new IntWritable(1);
    @Override
    protected void map ( LongWritable key, Text value, Context context ) throws IOException, InterruptedException {
        //拿到这一行数据
        String line = value.toString();
        //按照空格切分数据
        String[] words = line.split(" ");
        //遍历数据,把单词变成word,1的形式 返回给框架
        for (String word : words) {
            this.word.set(word);
            context.write(this.word,one);
        }
    }
}
