package com.gaoxiong.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author gaoxiong @ClassName MyGroupingComparator @Description TODO
 * @date 2019/11/27 0027 下午 10:03
 */
public class OrderBean implements WritableComparable<OrderBean> {

  private String orderId;
  private String productId;
  private double price;

  @Override
  public int compareTo(OrderBean o) {
    int compare = this.orderId.compareTo(o.getOrderId());
    // 如果订单相等
    if (compare == 0) {
      return Double.compare(o.price, this.price);
    } else {
      return compare;
    }
  }

  @Override
  public void write(DataOutput out) throws IOException {
    out.writeUTF(orderId);
    out.writeUTF(productId);
    out.writeDouble(price);
  }

  @Override
  public void readFields(DataInput in) throws IOException {
      orderId = in.readUTF();
      productId = in.readUTF();
      price =in.readDouble();
  }

    @Override
    public String toString () {
        return
                 orderId + ","+ productId + ","+ price ;
    }

    public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
