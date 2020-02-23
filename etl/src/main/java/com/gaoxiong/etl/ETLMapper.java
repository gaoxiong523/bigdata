package com.gaoxiong.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author gaoxiong @ClassName ETLMapper @Description TODO
 * @date 2020/1/28 0028 下午 9:30
 */
public class ETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

  private Text k = new Text();
  private StringBuilder sb = new StringBuilder();

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString();

    String result = handlerLine(line);
    if (result == null) {
      context.getCounter("ETL", "False").increment(1);
    } else {
      context.getCounter("ETL", "True").increment(1);
      k.set(result);
      context.write(k, NullWritable.get());
    }
  }

  /**
   * ETL方法,处理掉长度不够的数据, 并且把数据形式做转换
   *
   * @param line
   * @return
   */
  private String handlerLine(String line) {
    String[] fields = line.split("\t");

    if (fields.length < 9) {
      return null;
    }
    sb.delete(0, sb.length());
    fields[3] = fields[3].replace(" ", "");
    for (int i = 0; i < fields.length; i++) {
      if (i == fields.length - 1) {
        sb.append(fields[i]);
      } else if (i < 9) {
        sb.append(fields[i]).append("\t");
      } else {
        sb.append(fields[i]).append("&");
      }
    }
    return sb.toString();
  }
}
