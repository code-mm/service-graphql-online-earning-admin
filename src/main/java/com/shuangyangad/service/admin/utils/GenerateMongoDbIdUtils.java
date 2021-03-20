package com.shuangyangad.service.admin.utils;

import cn.hutool.core.util.IdUtil;

/**
 * mongo db id 随机生成
 */
public class GenerateMongoDbIdUtils {

    public static String getId() {
        return MD5Utils.md5(IdUtil.simpleUUID() + System.currentTimeMillis());
    }

}
