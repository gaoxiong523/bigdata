package com.gaoxiong.map;

import com.gaoxiong.entity.CarrierInfo;
import com.gaoxiong.entity.YearBase;
import com.gaoxiong.utils.CarrierUtils;
import com.gaoxiong.utils.DateUtil;
import com.gaoxiong.utils.HBaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author gaoxiong
 * @ClassName CarrierMap
 * @Description 运营商
 * @date 2019/6/7 0007 下午 5:53
 */
public class CarrierMap implements MapFunction<String,CarrierInfo> {
    @Override
    public CarrierInfo map ( String s ) throws Exception {
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
        Integer carrierType = CarrierUtils.getCarrierByTel(telphone);
        String carrierTypeString = carrierType == 0 ? "未知运营商" : carrierType == 1 ? "移动用户" : carrierType == 2 ? "联通用户" : "电信用户";
        String tableName = "userflaginfo";
        String rowKey = userId;
        String familyName = "baseinfo";
        String colum = "carrierinfo";//运营商
        HBaseUtils.put(tableName, rowKey, familyName, colum, carrierTypeString);
        CarrierInfo carrierInfo = new CarrierInfo();
        String groupfield="carrierinfo=="+carrierTypeString;
        carrierInfo.setCarrier(carrierTypeString);
        carrierInfo.setCount(1L);
        carrierInfo.setGroupfield(groupfield);
        return carrierInfo;
    }
}
