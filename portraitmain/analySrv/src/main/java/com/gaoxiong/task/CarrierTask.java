package com.gaoxiong.task;

import com.gaoxiong.entity.CarrierInfo;
import com.gaoxiong.entity.YearBase;
import com.gaoxiong.map.CarrierMap;
import com.gaoxiong.map.YearBaseMap;
import com.gaoxiong.reduce.CarrierReduce;
import com.gaoxiong.reduce.YearBaseReduce;
import com.gaoxiong.utils.MongoUtil;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * @author gaoxiong
 * @ClassName YearBaseTask
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:00
 */
public class CarrierTask {
    public static void main ( String[] args ) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        //set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);
        //get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<CarrierInfo> mapresult =text.map(new CarrierMap());
        DataSet<CarrierInfo> reduceresult =mapresult.groupBy("groupfield").reduce(new CarrierReduce());
        try {
            List<CarrierInfo> resultList = reduceresult.collect();
            for (CarrierInfo carrierInfo : resultList) {
                String carrier = carrierInfo.getCarrier();
                Long count = carrierInfo.getCount();
                Document doc = MongoUtil.findOneBy("carrierstatics", "portrait", carrier);
                if (doc == null) {
                    doc = new Document();
                    doc.put("carrier", carrier);
                    doc.put("count", count);
                } else {
                    Long countpre = doc.getLong("count");
                    Long total = countpre + count;
                    doc.put("count", total);
                }
                MongoUtil.saveorupdatemongo("carrierstatics", doc, "portrait");
            }
            env.execute("carrier anally");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
