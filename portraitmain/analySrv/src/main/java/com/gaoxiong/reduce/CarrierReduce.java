package com.gaoxiong.reduce;

import com.gaoxiong.entity.CarrierInfo;
import com.gaoxiong.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author gaoxiong
 * @ClassName
 * @Description TODO
 * @date 2019/6/6 0006 下午 9:42
 */
public class CarrierReduce implements ReduceFunction<CarrierInfo> {
    @Override
    public CarrierInfo reduce ( CarrierInfo carrierInfo, CarrierInfo t1 ) throws Exception {
        String carrier = carrierInfo.getCarrier();
        Long count1 = carrierInfo.getCount();
        Long count2 = t1.getCount();
        CarrierInfo finalcarrierInfo = new CarrierInfo();
       finalcarrierInfo.setCarrier(carrier);
       finalcarrierInfo.setCount(count1+count2);
        return finalcarrierInfo;
    }
}
