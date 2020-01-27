package com.gaoxiong.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author gaoxiong @ClassName OrderComparator @Description TODO
 * @date 2019/11/27 0027 下午 10:23
 */
public class OrderComparator extends WritableComparator {
  /**
   * 指定构造器,调用父类实现,避免空指针
   */
  public OrderComparator () {
    super(OrderBean.class,true);
  }

  @Override
  public int compare(WritableComparable a, WritableComparable b) {
    OrderBean oa = (OrderBean) a;
    OrderBean ob = (OrderBean) b;
    return oa.getOrderId().compareTo(ob.getOrderId());
  }
}
