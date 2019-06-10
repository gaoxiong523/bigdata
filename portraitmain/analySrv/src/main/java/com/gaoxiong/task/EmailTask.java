package com.gaoxiong.task;

import com.gaoxiong.entity.EmailInfo;
import com.gaoxiong.entity.YearBase;
import com.gaoxiong.map.EmailMap;
import com.gaoxiong.map.YearBaseMap;
import com.gaoxiong.reduce.EmailReduce;
import com.gaoxiong.reduce.YearBaseReduce;
import com.gaoxiong.utils.MongoUtil;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * @author gaoxiong
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:00
 */
public class EmailTask {
    public static void main ( String[] args ) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        //set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);
        //get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<EmailInfo> mapresult =text.map(new EmailMap());
        DataSet<EmailInfo> reduceresult =mapresult.groupBy("groupfield").reduce(new EmailReduce());
        try {
            List<EmailInfo> resultList = reduceresult.collect();
            for (EmailInfo emailInfo : resultList) {
                String emailTye = emailInfo.getEmailTye();
                Long count = emailInfo.getCount();
                Document doc = MongoUtil.findOneBy("emailstatics", "portrait", emailTye);
                if (doc == null) {
                    doc = new Document();
                    doc.put("emailType", emailTye);
                    doc.put("count", count);
                } else {
                    Long countpre = doc.getLong("count");
                    Long total = countpre + count;
                    doc.put("count", total);
                }
                MongoUtil.saveorupdatemongo("emailstatics", doc, "portrait");
            }
            env.execute("email anally");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
