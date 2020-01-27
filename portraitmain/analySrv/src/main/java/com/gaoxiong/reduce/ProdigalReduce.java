package com.gaoxiong.reduce;

import com.gaoxiong.entity.CarrierInfo;
import com.gaoxiong.entity.ProdigalInfo;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoxiong
 * @ClassName
 * @Description TODO
 * @date 2019/6/6 0006 下午 9:42
 */
public class ProdigalReduce implements  ReduceFunction<ProdigalInfo>{


    @Override
    public ProdigalInfo reduce ( ProdigalInfo prodigalInfo, ProdigalInfo t1 ) throws Exception {
        String userId = prodigalInfo.getUserId();
        List<ProdigalInfo> list1 = prodigalInfo.getList();
        List<ProdigalInfo> list2 = t1.getList();
        List<ProdigalInfo> finalList = new ArrayList<>();
        finalList.addAll(list1);
        finalList.addAll(list2);
        ProdigalInfo prodigalInfoFinal = new ProdigalInfo();
        prodigalInfoFinal.setUserId(userId);
        prodigalInfoFinal.setList(finalList);
        return prodigalInfoFinal;
    }
}
