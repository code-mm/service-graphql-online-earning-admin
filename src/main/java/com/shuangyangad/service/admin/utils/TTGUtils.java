package com.shuangyangad.service.admin.utils;

import org.json.JSONObject;

public class TTGUtils {

    public static boolean verification(String ttg) {
        try {
            JSONObject ttgJsonObject = new JSONObject(ttg);
            String os = ttgJsonObject.getString("os");
            boolean b = checkOs(os);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean checkOs(String os) {
        String s = os.toLowerCase();
        if ("android".equals(s)) {
            return true;
        }
        if ("ios".equals(s)) {
            return true;
        }
        if ("windows".equals(s)) {
            return true;
        }
        if ("osx".equals(s)) {
            return true;
        }
        if ("linux".equals(s)) {
            return true;
        }
        return false;
    }
}
