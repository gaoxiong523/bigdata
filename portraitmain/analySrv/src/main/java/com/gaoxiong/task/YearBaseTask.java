package com.gaoxiong.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

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
        text.map()
    }
}
