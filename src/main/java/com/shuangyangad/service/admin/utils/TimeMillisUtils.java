package com.shuangyangad.service.admin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMillisUtils {
    public static final String PATTEN_DEFAULT_YMD = "yyyy-MM-dd";

    public static boolean isNow(Date date) {
        // 当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(PATTEN_DEFAULT_YMD);
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }
}
