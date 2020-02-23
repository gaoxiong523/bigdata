package com.gaoxiong.hbase.hbase_mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author gaoxiong
 * @ClassName File2TableApplication
 * @Description TODO
 * @date 2020/2/20 0020 下午 5:17
 */
public class File2TableApplication {
  public static void main(String[] args) throws Exception {
    //
      Configuration conf = HBaseConfiguration.create();
      HbaseMapperReduceTool hbaseMapperReduceTool = new HbaseMapperReduceTool();
      ToolRunner.run( hbaseMapperReduceTool,args );

  }
}
