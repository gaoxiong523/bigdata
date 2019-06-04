package com.gaoxiong.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author gaoxiong
 * @ClassName HBaseUtils
 * @Description TODO
 * @date 2019/6/4 0004 下午 8:58
 */
public class HBaseUtils {
    private static Admin admin = null;
    private static Connection conn = null;
    static {
        //插件hbase配置对象
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir", "hdfs://192.168.2.171:9000/hbase");
        //使用eclipse时必须添加这个，否则无法定位
        conf.set("hbase.zookeeper.quorum", "192.168.2.170");
        conf.set("hbase.client.scanner.timeout.period", "60000");
        conf.set("hbase.rpc.timeout", "600000");
        try {
            conn = ConnectionFactory.createConnection(conf);
            //得到管理程序
            admin=conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建表
     */
    public void createTable(String tableName,String familyName) throws IOException {
        HTableDescriptor tab = new HTableDescriptor(tableName);
        //添加列族，每个表至少有一个列族
        HColumnDescriptor colDesc = new HColumnDescriptor(familyName);
        tab.addFamily(colDesc);
        //创建表
        admin.createTable(tab);
        System.out.println("over");
    }

    /**
     * 插入数据
     * create "userflaginfo,"baseinfo"
     * @param tableName 表名称
     * @param rowKey
     * @param familyName
     * @param dataMap 数据
     * @throws IOException
     */
    public static void put( String tableName, String rowKey, String familyName, Map<String,String> dataMap ) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        //将字符串转换为byte[]
        byte[] rowKeyBytes = Bytes.toBytes(rowKey);
        Put put = new Put(rowKeyBytes);
        if (dataMap != null) {
            Set<Map.Entry<String, String>> set = dataMap.entrySet();
            for (Map.Entry<String, String> entry : set) {
                String key = entry.getKey();
                Object value = entry.getValue();
                put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(key), Bytes.toBytes(value + ""));
            }
        }
        table.put(put);
        table.close();
        System.out.println("OK");
    }

    /**
     * 获取数据
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param colum
     * @return
     */
    public static String getData(String tableName,String rowKey,String familyName,String colum) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        byte[] rowKeyBytes = Bytes.toBytes(rowKey);
        Get get = new Get(rowKeyBytes);
        Result result = table.get(get);
        byte[] resultBytes = result.getValue(familyName.getBytes(), colum.getBytes());
        if (resultBytes == null) {
            return null;
        }
        return new String(resultBytes);
    }

    /**
     * 插入一条数据
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param colum 指定了列
     * @throws IOException
     */
    public static void put ( String tableName, String rowKey, String familyName,String colum ,String data ) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(familyName.getBytes(), colum.getBytes(), data.getBytes());
        table.put(put);

    }

    }
