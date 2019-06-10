package com.gaoxiong.reduce;

import com.gaoxiong.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author gaoxiong
 * @ClassName YearBaseReduce
 * @Description TODO
 * @date 2019/6/6 0006 下午 9:42
 */
public class YearBaseReduce implements ReduceFunction<YearBase> {
    @Override
    public YearBase reduce ( YearBase yearBase, YearBase t1 ) throws Exception {
        String yearType = yearBase.getYearType();
        Long count1 = yearBase.getCount();
        Long count2 = t1.getCount();
        YearBase finalyearBase = new YearBase();
        finalyearBase.setYearType(yearType);
        finalyearBase.setCount(count1+count2);
        return finalyearBase;
    }
}
