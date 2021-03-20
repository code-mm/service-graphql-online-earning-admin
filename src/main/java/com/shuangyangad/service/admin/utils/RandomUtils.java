package com.shuangyangad.service.admin.utils;

import java.util.Random;

public class RandomUtils {
    private static RandomUtils instance;
    public static RandomUtils getInstance() {
        if (instance == null) {
            instance = new RandomUtils();
        }
        return instance;
    }
    private Random random;

    private RandomUtils() {
        random = new Random();
    }
    public Random getRandom() {
        return random;
    }
}