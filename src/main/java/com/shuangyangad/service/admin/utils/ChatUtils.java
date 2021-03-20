package com.shuangyangad.service.admin.utils;

public class ChatUtils {

    /**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84\xF0\x9F）的解决方案 ��
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i + j] = 0x30;// 0x30 int=48   字符=0
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }

    /**
     * 将emoji表情替换成*
     *
     * @return 过滤后的字符串
     * 过滤的方式很简单，直接使用正则表达式匹配编码范围，然后替换就行了。
     */

    public static String filterEmoji(String source) {
        if (source != null && !"".equals(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        }
        return source;
    }

    public static void main(String[] args) {
        String name = "桃洛憬\uD83D\uDC9E";
        String s = removeFourChar(name);
        System.out.println(s);
        System.out.println(filterEmoji(name));
    }
}
