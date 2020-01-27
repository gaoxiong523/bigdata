package com.gaoxiong.compress;

import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author gaoxiong @ClassName TestCompress @Description TODO
 * @date 2019/12/17 0017 下午 10:32
 */
public class TestCompress {
  public static void main(String[] args) throws Exception {
    //
    compress(
        "F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input7\\web.log",
        BZip2Codec.class);
    decompress("F:\\project\\bigdata\\map-reduce\\src\\main\\resources\\input7\\web.log.bz2");
  }

  private static void decompress(String path) throws IOException {

    CompressionCodecFactory fa = new CompressionCodecFactory(new Configuration());
    CompressionCodec codec = fa.getCodec(new Path(path));
    CompressionInputStream cis = codec.createInputStream(new FileInputStream(path));
    FileOutputStream fos = new FileOutputStream(path.substring(0, path.length() - 4));
    IOUtils.copyBytes(cis, fos, 1024);
    IOUtils.closeStream(cis);
    IOUtils.closeStream(fos);
  }

  private static void compress(String path, Class<? extends CompressionCodec> codecClass)
      throws IOException {
    FileInputStream fis = new FileInputStream(path);
    CompressionCodec compressionCodec =
        ReflectionUtils.newInstance(codecClass, new Configuration());
    FileOutputStream fos = new FileOutputStream(path + compressionCodec.getDefaultExtension());

    CompressionOutputStream cos = compressionCodec.createOutputStream(fos);
    IOUtils.copyBytes(fis, cos, 1024);
    IOUtils.closeStream(fis);
    IOUtils.closeStream(cos);
  }
}
