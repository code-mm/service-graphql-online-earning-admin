package com.shuangyangad.service.admin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 微信提现 OrderNo 生成
 */
public class GenerateOrderNoUtils {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String partnerTradeNo(String mchid) {
        return mchid + dataFormat.format(new Date()) + (int) ((Math.random() * 9 + 1) * 1000);
    }

}
