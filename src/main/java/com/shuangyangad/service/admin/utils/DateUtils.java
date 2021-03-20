package com.shuangyangad.service.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    /**
     * 年月日
     */
    public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 双阳系统标准时间格式
     */
    private static final SimpleDateFormat SHUANGYANGAD_STANDARD = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");


    public static long getTimeTo24(String date) {
        Date date1 = new Date();
        try {
            date1 = yyyy_MM_dd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDateTime midnight = LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(date1.toInstant(),
                ZoneId.systemDefault());
        //使用ChronoUnit.SECONDS.between方法，传入两个LocalDateTime对象即可得到相差的秒数
        return ChronoUnit.SECONDS.between(currentDateTime, midnight);
    }
}
