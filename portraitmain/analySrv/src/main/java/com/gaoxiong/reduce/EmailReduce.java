package com.gaoxiong.reduce;

import com.gaoxiong.entity.EmailInfo;
import com.gaoxiong.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * @author gaoxiong
 * @ClassName YearBaseReduce
 * @Description TODO
 * @date 2019/6/6 0006 下午 9:42
 */
public class EmailReduce implements ReduceFunction<EmailInfo> {
    @Override
    public EmailInfo reduce ( EmailInfo emailInfo, EmailInfo e1 ) throws Exception {
        String emailTye = emailInfo.getEmailTye();
        Long count1 = emailInfo.getCount();
        Long count2 = e1.getCount();
        EmailInfo finalEmailInfo = new EmailInfo();
        finalEmailInfo.setEmailTye(emailTye);
        finalEmailInfo.setCount(count1+count2);
        return finalEmailInfo;
    }
}
