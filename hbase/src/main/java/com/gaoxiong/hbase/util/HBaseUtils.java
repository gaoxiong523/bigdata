package com.gaoxiong.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author gaoxiong @ClassName HBaseUtils @Description TODO
 * @date 2020/2/20 0020 下午 4:21
 */
public class HBaseUtils {

  private static ThreadLocal<Connection> connHolder = new ThreadLocal<>();

  public static void makeConnection(String zkAdress) throws Exception {
    if (connHolder.get() == null) {
      Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", zkAdress);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
      connHolder.set(ConnectionFactory.createConnection(conf));
    }
  }

  public static void close() throws Exception {
    Connection connection = connHolder.get();
    if (!connection.isClosed()) {
      connection.close();
      connHolder.remove();
    }
  }

  public static void insertData(
      String tableName, String rowKey, String family, String column, String value) throws IOException {

      Connection conn = connHolder.get();
      Table table = conn.getTable(TableName.valueOf(tableName));
      Put put = new Put(Bytes.toBytes(rowKey));
      put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column) ,Bytes.toBytes(value) );

    table.put(put);
    table.close();
  }
}
