package com.gaoxiong.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author gaoxiong
 * @ClassName HDFSClient
 * @Description TODO
 * @date 2019/11/11 0011 下午 9:53
 */
public class HDFSClient {
    private FileSystem fileSystem = null;
    private Configuration configuration = null;

    @Before
    public void  init() throws URISyntaxException, IOException, InterruptedException {
        configuration = new Configuration();
        fileSystem = FileSystem.get(URI.create("hdfs://192.168.150.134:9000"), configuration, "hadoop");
        //配置HA,为什么在连接standby的namenode时会报错
    }

    @Test
    public void  put() throws IOException {
        fileSystem.copyFromLocalFile(new Path("F:\\project\\bigdata\\hdfs\\src\\main\\resources\\1.txt"), new Path("/"));

    }

    @Test
    public void get() throws IOException {
        fileSystem.copyToLocalFile(new Path("/1.txt"),new Path("F:\\project\\bigdata\\hdfs\\src\\main\\resources\\test1.txt") );
    }

    @Test
    public void rename() throws IOException {
        fileSystem.rename(new Path("/1.txt"), new Path("/1-1.txt"));
    }

    @Test
    public void delete(){

    }

    @Test
    public void ls(){

    }
    @Test
    public void append() throws IOException {
        FSDataOutputStream append = fileSystem.append(new Path("/1.txt"));
        FileInputStream fileInputStream = new FileInputStream("F:\\project\\bigdata\\hdfs\\src\\main\\resources\\1.txt");
        IOUtils.copyBytes(fileInputStream,append ,1024 );
    }

    @Test
    public void listFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            LocatedFileStatus fileStatus = files.next();
            System.out.println("=========================");
            System.out.println("fileStatus.getPath() = " + fileStatus.getPath());
            System.out.println("块信息");

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                System.out.println("块在 ");
                for (String host : hosts) {
                    System.out.println("host = " + host);
                }
            }
        }
    }

    @After
    public void  after() throws IOException {
        fileSystem.close();
    }
}
