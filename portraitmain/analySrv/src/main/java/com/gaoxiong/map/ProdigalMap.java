package com.gaoxiong.map;

import com.gaoxiong.entity.ProdigalInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoxiong
 * @ClassName ProdigalMap 败家指数
 * @Description TODO
 * @date 2019/6/10 0010 下午 10:10
 */
public class ProdigalMap implements MapFunction<String,ProdigalInfo> {
    @Override
    public ProdigalInfo map ( String s ) throws Exception {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String[] orderInfos = s.split(",");
        String id = orderInfos[0];
        String productId = orderInfos[1];
        String productTypeId = orderInfos[2];
        String createTime = orderInfos[3];
        String payTime = orderInfos[4];
        String payType = orderInfos[5];
        String payStatus = orderInfos[6];
        String amount = orderInfos[7];
        String couponsAmount = orderInfos[8];
        String totalAmount = orderInfos[9];
        String refundAmount = orderInfos[10];
        String userId = orderInfos[11];
        String num = orderInfos[12];

        String groupfield = "baijia"+userId;
        ProdigalInfo prodigalInfo = new ProdigalInfo();
        prodigalInfo.setUserId(userId);
        prodigalInfo.setAmount(amount);
        prodigalInfo.setCouponAmount(couponsAmount);
        prodigalInfo.setCreateTime(createTime);
        prodigalInfo.setPayStatus(payStatus);
        prodigalInfo.setPayTime(payTime);
        prodigalInfo.setPayType(payType);
        prodigalInfo.setTotalAmount(totalAmount);
        prodigalInfo.setRefundAmount(refundAmount);
        prodigalInfo.setCount(1L);
        prodigalInfo.setGroupfield(groupfield);
        List<ProdigalInfo> list = new ArrayList<>();
        list.add(prodigalInfo);
        return prodigalInfo;

    }
}
