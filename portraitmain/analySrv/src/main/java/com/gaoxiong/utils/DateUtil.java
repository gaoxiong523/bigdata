package com.gaoxiong.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author gaoxiong
 * @ClassName DateUtil
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:18
 */
public class DateUtil {
    public static String getYearBaseByAge(String age){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -Integer.valueOf(age));
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String format = dateFormat.format(newDate);
        Integer newdateInteger = Integer.valueOf(format);
        String yearBaseType = "未知";
        if (newdateInteger >= 1940 && newdateInteger < 1950) {
            yearBaseType = "40后";
        } else if (newdateInteger >= 1950 && newdateInteger < 1960) {
            yearBaseType = "50后";
        }else if (newdateInteger >= 1960 && newdateInteger < 1970) {
            yearBaseType = "60后";
        }else if (newdateInteger >= 1970 && newdateInteger < 1980) {
            yearBaseType = "70后";
        }else if (newdateInteger >= 1980 && newdateInteger < 1990) {
            yearBaseType = "80后";
        }else if (newdateInteger >= 1990 && newdateInteger < 2000) {
            yearBaseType = "90后";
        }else if (newdateInteger >= 2000 && newdateInteger < 2010) {
            yearBaseType = "00后";
        }else if (newdateInteger >= 2010 ) {
            yearBaseType = "10后";
        }
        return yearBaseType;
    }

    public static int getDaysBetweenByStartAndEnd ( String startTime, String endTime, String dateFormatString ) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        Date start = dateFormat.parse(startTime);
        Date end = dateFormat.parse(endTime);
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalenar = Calendar.getInstance();
        startCalendar.setTime(start);
        endCalenar.setTime(end);
        int days = 0;
        while (startCalendar.before(endCalenar)) {
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
            days+=1;
        }
        return days;
    }
}
