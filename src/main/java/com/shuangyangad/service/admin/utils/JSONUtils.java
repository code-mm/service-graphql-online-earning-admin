package com.shuangyangad.service.admin.utils;

public class JSONUtils {

    private static JSONUtils instance;

    public static JSONUtils getInstance() {
        if (instance == null) {
            instance = new JSONUtils();
        }
        return instance;
    }

    private JSONUtils() {

    }
    public boolean checkJson(String json) {
        String newStr = json.trim();
        if (newStr.startsWith("{") && newStr.endsWith("}") || newStr.startsWith("[") && newStr.endsWith("]")) {
            return true;
        }
        return false;
    }
}
