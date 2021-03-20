package com.shuangyangad.service.admin.utils;

import cn.hutool.core.util.IdUtil;

/**
 * 用户id生成
 */
public class GenerateUserIdUtils {

    public static String getUserId() {
        return MD5Utils.md5(IdUtil.simpleUUID());
    }

}
