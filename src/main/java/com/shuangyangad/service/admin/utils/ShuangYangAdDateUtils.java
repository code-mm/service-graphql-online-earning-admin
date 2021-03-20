package com.shuangyangad.service.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShuangYangAdDateUtils {

    /**
     * mongodb 用户签到时间格式
     */
    private static final SimpleDateFormat SIMPLEDATEFORMAT_MONGO_DB_DATE = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 双阳系统标准的时间格式
     */
    private static final SimpleDateFormat SIMPLEDATEFORMAT_SHUANGYANGAD = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final SimpleDateFormat SIMPLEDATEFORMAT_ALIYUN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String mongoDbUserSigninDate(Date date) {
        return SIMPLEDATEFORMAT_MONGO_DB_DATE.format(date);
    }

    public static String mongoDbUserSigninDate() {
        return SIMPLEDATEFORMAT_MONGO_DB_DATE.format(new Date());
    }

    public static String date2String(Date date) {
        return SIMPLEDATEFORMAT_SHUANGYANGAD.format(date);
    }

    public static Date aliyunStromg2Date(String date) {
        try {
            return SIMPLEDATEFORMAT_ALIYUN.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}