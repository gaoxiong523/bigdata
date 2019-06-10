package com.gaoxiong.entity;

import lombok.Data;

/**
 * @author gaoxiong
 * @ClassName CarrierInfo
 * @Description TODO
 * @date 2019/6/7 0007 下午 5:54
 */
@Data
public class EmailInfo {
    private String emailTye; //邮箱类型
    private Long count;//数量
    private String groupfield; //分组字段
}
