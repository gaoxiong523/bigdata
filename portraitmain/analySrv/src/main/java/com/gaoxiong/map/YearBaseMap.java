package com.gaoxiong.map;

import com.gaoxiong.entity.YearBase;
import com.gaoxiong.utils.DateUtil;
import com.gaoxiong.utils.HBaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author gaoxiong
 * @ClassName YearBaseMap
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:04
 */
public class YearBaseMap implements MapFunction<String, YearBase> {

    @Override
    public YearBase map ( String s ) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] userInfos = s.split(",");
        String userId = userInfos[0];
        String userName = userInfos[1];
        String sex = userInfos[2];
        String telphone = userInfos[3];
        String email = userInfos[4];
        String age = userInfos[5];
        String registerTime = userInfos[6];
        //终端类型0，pc 1，移动端，2，小程序
        String userType = userInfos[7];
        String yearBaseType = DateUtil.getYearBaseByAge(age);

        String tableName = "userflaginfo";
        String rowKey = userId;
        String familyName = "baseinfo";
        String colum = "yaerbase";//年代
        HBaseUtils.put(tableName, rowKey, familyName, colum, yearBaseType);

        return null;
    }
}
