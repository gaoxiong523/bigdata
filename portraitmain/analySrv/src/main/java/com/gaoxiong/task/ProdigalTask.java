package com.gaoxiong.task;

import com.gaoxiong.entity.CarrierInfo;
import com.gaoxiong.entity.ProdigalInfo;
import com.gaoxiong.map.CarrierMap;
import com.gaoxiong.map.ProdigalMap;
import com.gaoxiong.reduce.CarrierReduce;
import com.gaoxiong.reduce.ProdigalReduce;
import com.gaoxiong.utils.DateUtil;
import com.gaoxiong.utils.MongoUtil;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gaoxiong
 * @ClassName YearBaseTask
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:00
 */
public class ProdigalTask {
    public static void main ( String[] args ) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        //set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);
        //get input data
        //默认近一年的数据
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<ProdigalInfo> mapresult =text.map(new ProdigalMap());
        DataSet<ProdigalInfo> reduceresult =mapresult.groupBy("groupfield").reduce(new ProdigalReduce());
        try {
            List<ProdigalInfo> resultList = reduceresult.collect();
            for (ProdigalInfo prodigalInfo : resultList) {
                String userId = prodigalInfo.getUserId();
                List<ProdigalInfo> list = prodigalInfo.getList();
                Collections.sort(list, new Comparator<ProdigalInfo>() {
                    @Override
                    public int compare ( ProdigalInfo o1, ProdigalInfo o2 ) {
                        String timeo1 = o1.getCreateTime();
                        String timeo2 = o2.getCreateTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
                        Date timenow = new Date();
                        Date time1 = timenow;
                        Date time2 = timenow;
                        try {
                            time1 = dateFormat.parse(timeo1);
                            time2 = dateFormat.parse(timeo2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return time1.compareTo(time2);
                    }
                });
                ProdigalInfo before  = null;
                Map<Integer, Integer> frequencyMap = new HashMap<>();
                Double maxAmount = 0d;
                Double sum = 0d;
                for (ProdigalInfo info : list) {
                    if (before == null) {
                        before = info;
                        continue;
                    }
                    //计算购买的频率
                    String beforeCreateTime = before.getCreateTime();
                    String createTime = info.getCreateTime();
                    int days = DateUtil.getDaysBetweenByStartAndEnd(beforeCreateTime, createTime, "yyyyMMdd hhmmss");
                    int beforeDays = frequencyMap.get(days) == null ? 0 : frequencyMap.get(days);
                    frequencyMap.put(days, beforeDays + 1);
                    //计算最大 金额
                    String totalAmountString = info.getTotalAmount();
                    Double totalAmount = Double.valueOf(totalAmountString);
                    if (totalAmount > maxAmount) {
                        maxAmount = totalAmount;
                    }
                    //计算平均值
                    sum += totalAmount;
                    before =info;
                }
               //TODO 下一步 算出  败家的评分

                MongoUtil.saveorupdatemongo("carrierstatics", doc, "portrait");
            }
            env.execute("carrier anally");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
