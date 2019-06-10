package com.gaoxiong.utils;

/**
 * @author gaoxiong
 * @ClassName EmailUtils
 * @Description TODO
 * @date 2019/6/8 0008 下午 4:38
 */
public class EmailUtils {

    /**
     * 根据邮箱地址得到邮箱运营商
     *
     * @param email
     * @return
     */
    public static String getEmailTypeBy ( String email ) {
        String emailType = "其他邮箱用户";
        if (email.contains("@163.com") || email.contains("@126.com")) {
            emailType = "网易邮箱用户";
        } else if (email.contains("@139.com")) {
            emailType = "移动邮箱用户";
        }else if (email.contains("@sohu.com")) {
            emailType = "搜狐邮箱用户";
        }else if (email.contains("@qq.com")) {
            emailType = "腾讯邮箱用户";
        }else if (email.contains("@189.com")) {
            emailType = "电信邮箱用户";
        }else if (email.contains("@tom.com")) {
            emailType = "Tom邮箱用户";
        }else if (email.contains("@aliyun.com")) {
            emailType = "阿里邮箱用户";
        }else if (email.contains("@sina.com")) {
            emailType = "新浪邮箱用户";
        }
        return emailType;
    }
}
