package com.shuangyangad.service.admin.utils;


import java.util.Random;

/**
 * 随机生产邀请编码
 */
public class GenerateShareCodeUtils {

    /**
     * 邀请码组合 - 数字、大写字母 - 常量
     * 不包换：0,O,1,I
     */
    private static final String RULE_INVITATION_CODE = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final int RULE_INVITATION_CODE_LENGTH = 32;

    /**
     * 邀请码长度 - 常量
     */
    private static final int CODE_LENGTH_5 = 5;
    private static final int CODE_LENGTH_6 = 6;

    /**
     * 随机产生指定位数的随机字符串
     */
    private static String randomCodeLength(int length) {
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int i1 = random.nextInt(RULE_INVITATION_CODE_LENGTH);
            char c = RULE_INVITATION_CODE.charAt(i1);
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    /**
     * 随机生产邀请码
     *
     * @return
     */
    public static String randomCode6() {
        return randomCodeLength(CODE_LENGTH_6);
    }

}

