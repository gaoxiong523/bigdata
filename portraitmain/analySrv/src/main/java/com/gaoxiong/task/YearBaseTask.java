package com.gaoxiong.task;

import com.gaoxiong.entity.YearBase;
import com.gaoxiong.map.YearBaseMap;
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
public class YearBaseTask {
    public static void main ( String[] args ) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        //set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);
        //get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<YearBase> mapresult =text.map(new YearBaseMap());
        DataSet<YearBase> reduceresult =mapresult.groupBy("groupfield").reduce(new YearBaseReduce());
        try {
            List<YearBase> resultList = reduceresult.collect();
            for (YearBase yearBase : resultList) {
                String yearType = yearBase.getYearType();
                Long count = yearBase.getCount();
                Document doc = MongoUtil.findOneBy("yearbasestatics", "portrait", yearType);
                if (doc == null) {
                    doc = new Document();
                    doc.put("yearbasetype", yearType);
                    doc.put("count", count);
                } else {
                    Long countpre = doc.getLong("count");
                    Long total = countpre + count;
                    doc.put("count", total);
                }
                MongoUtil.saveorupdatemongo("yearbasestatics", doc, "portrait");
            }
            env.execute("year base");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
