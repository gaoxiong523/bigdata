package com.gaoxiong.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author gaoxiong @ClassName TestHbaseApi_2 @Description TODO
 * @date 2020/2/20 0020 下午 3:04
 */
public class TestHbaseApi_2 {
  public static void main(String[] args) throws IOException {
    //
    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", "192.168.150.134");
    conf.set("hbase.zookeeper.property.clientPort", "2181");
    Connection connection = ConnectionFactory.createConnection(conf);
    Admin admin = connection.getAdmin();
    TableName tableName = TableName.valueOf("atguigu:student");
    //      NamespaceDescriptor nd = NamespaceDescriptor.create("atguigu").build();
    //      admin.createNamespace(nd);
    if (admin.tableExists(tableName)) {
      //          connection.getTable(tableName);
      // 禁用表
      admin.disableTable(tableName);
      // 删除表
      admin.deleteTable(tableName);
    } else {
      // 创建表之前要先创建表空间 namespace

      ColumnFamilyDescriptor cd =
          new ColumnFamilyDescriptorBuilder.ModifyableColumnFamilyDescriptor(Bytes.toBytes("info"));
      TableDescriptor td = TableDescriptorBuilder.newBuilder(tableName).setColumnFamily(cd).build();

      admin.createTable(td);
    }
  }
}
