package com.gaoxiong.entity;

import lombok.Data;

import java.util.List;

/**
 * @author gaoxiong
 * @ClassName ProdigalInfo
 * @Description 败家指数
 * @date 2019/6/10 0010 下午 10:11
 */
@Data
public class ProdigalInfo {
    /**
     * 败家指数的区段
     * 0-20
     * 20-50
     * 50-70
     * 70-80
     * 80-90
     * 90-100
     */
    private String prodigalScore;
    private Long count;
    private String groupfield;

    private List<ProdigalInfo> list;

    private String userId;
    private String createTime;
    private String amount;
    private String payType;
    private String payTime;
    private String payStatus; //0 ,未支付,1,已支付,2已退款
    private String couponAmount;
    private String totalAmount;
    private String refundAmount;

}
