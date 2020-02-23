package com.gaoxiong.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * @author gaoxiong @ClassName TestHbaseApi @Description 测试 hbase API
 * @date 2020/2/19 0019 下午 8:21
 */
public class TestHbaseApi {

  private static Connection connection = null;

  public static void main(String[] args) throws Exception {
    // 1,获取连接对象

    // 2.获取操作对象

    // 3.操作数据库

    // 4.获取操作结果

    // 5.关闭数据库连接

    init();
    getData();
  }

  /** 初始化链接 */
  private static void init() throws IOException {
    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", "192.168.150.134");
    conf.set("hbase.zookeeper.property.clientPort", "2181");
    connection = ConnectionFactory.createConnection(conf);
  }

  private static void getData() throws IOException {
    Table student = connection.getTable(TableName.valueOf("student"));
    ResultScanner results = student.getScanner(new Scan());
    for (Result result : results) {
      List<Cell> cells = result.listCells();
      for (Cell cell : cells) {
        // rowkey
        System.out.println("cell = 的行健   " + Bytes.toString(CellUtil.cloneRow(cell)));
        // 得到列族
        System.out.println("cell =  的列族   " + Bytes.toString(CellUtil.cloneFamily(cell)));
        // 得到列
        System.out.println("cell =  列   " + Bytes.toString(CellUtil.cloneQualifier(cell)));
        // 得到值
        System.out.println("cell =  值 " + Bytes.toString(CellUtil.cloneValue(cell)));
      }
    }
  }

  private static void operTable() throws IOException {
      Admin admin = connection.getAdmin();
//      Put put = new Put();
//      put.addColumn(, , )
  }
}
