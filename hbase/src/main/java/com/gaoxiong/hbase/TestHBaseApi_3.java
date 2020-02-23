package com.gaoxiong.hbase;

import com.gaoxiong.hbase.util.HBaseUtils;

import java.io.IOException;

/**
 * @author gaoxiong
 * @ClassName TestHBaseApi_3
 * @Description TODO
 * @date 2020/2/20 0020 下午 4:32
 */
public class TestHBaseApi_3 {
  public static void main(String[] args) throws Exception {
    //
      HBaseUtils.makeConnection("192.168.150.134");

    HBaseUtils.insertData("atguigu:student", "1002", "info", "age", "20");

    HBaseUtils.close();
  }
}
