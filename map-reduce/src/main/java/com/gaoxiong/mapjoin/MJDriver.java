package com.gaoxiong.mapjoin;

import com.gaoxiong.flow.FlowBean;
import com.gaoxiong.flow.FlowDriver;
import com.gaoxiong.flow.FlowMapper;
import com.gaoxiong.flow.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author gaoxiong @ClassName MJDriver @Description TODO
 * @date 2019/12/12 0012 下午 11:13
 */
public class MJDriver {
  public static void main(String[] args)
      throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    //
    args =
        new String[] {
          "F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input6\\order.txt",
          "F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\output6"
        };

    Configuration conf = new Configuration();

    Path out = new Path(args[1]);
    FileSystem fileSystem = FileSystem.get(conf);
    if (fileSystem.exists(out)) {
      fileSystem.delete(out, true);
      System.out.println("output is exist,will be  delete !fileSystem = " + fileSystem);
    }

    Job job = Job.getInstance(conf);

    job.setJarByClass(MJDriver.class);

    job.setMapperClass(MJMapper.class);
    job.setNumReduceTasks(0);
    job.addCacheFile(
        new URI("file:///F:/project/bigdata/map-reduce/src/main/resources/input6/pd.txt"));

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, out);

    boolean b = job.waitForCompletion(true);
    System.exit(b ? 0 : 1);
  }
}
