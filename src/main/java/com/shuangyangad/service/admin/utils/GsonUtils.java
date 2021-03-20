package com.shuangyangad.service.admin.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private static final GsonUtils instance = new GsonUtils();

    private Gson gson;

    private GsonUtils() {
        gson = new Gson();
    }

    public static GsonUtils getInstance() {
        return instance;
    }

    public Gson getGson() {
        return gson;
    }
}
