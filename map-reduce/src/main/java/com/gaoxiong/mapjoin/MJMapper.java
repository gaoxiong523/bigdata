package com.gaoxiong.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoxiong @ClassName MJMapper @Description TODO
 * @date 2019/12/12 0012 下午 11:13
 */
public class MJMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

  private Map<String, String> map = new HashMap<>();

  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
    URI[] cacheFiles = context.getCacheFiles();
    String path = cacheFiles[0].getPath().toString();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
    String line;
    while (StringUtils.isNotEmpty(line = bufferedReader.readLine())) {
      String[] fields = line.split("\t");
      map.put(fields[0], fields[1]);
    }
    IOUtils.closeStream(bufferedReader);
  }

  private Text key = new Text();

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String[] fields = value.toString().split("\t");
    String pname = map.get(fields[1]);
    this.key.set(fields[0] + "\t" + pname + "\t" + fields[2]);
    context.write(this.key, NullWritable.get());
  }
}
