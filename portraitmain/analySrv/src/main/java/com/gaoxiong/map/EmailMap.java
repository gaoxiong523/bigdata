package com.gaoxiong.map;

import com.gaoxiong.entity.EmailInfo;
import com.gaoxiong.utils.EmailUtils;
import com.gaoxiong.utils.HBaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author gaoxiong
 * @Description 邮箱地址
 * @date 2019/6/7 0007 下午 5:53
 */
public class EmailMap implements MapFunction<String,EmailInfo> {
    @Override
    public EmailInfo map ( String s ) throws Exception {
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
        String emailTypeBy = EmailUtils.getEmailTypeBy(email);

        String tableName = "userflaginfo";
        String rowKey = userId;
        String familyName = "baseinfo";
        String colum = "emailinfo";//运营商
        HBaseUtils.put(tableName, rowKey, familyName, colum, emailTypeBy);
        EmailInfo emailInfo = new EmailInfo();
        String groupfield="carrierinfo=="+emailTypeBy;
        emailInfo.setEmailTye(emailTypeBy);
        emailInfo.setCount(1L);
        emailInfo.setGroupfield(groupfield);
        return emailInfo;
    }
}
