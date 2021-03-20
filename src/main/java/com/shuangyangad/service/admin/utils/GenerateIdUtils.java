package com.shuangyangad.service.admin.utils;

import cn.hutool.core.util.IdUtil;

/**
 * id 生成
 */
public class GenerateIdUtils {

    public static String getId() {
        return MD5Utils.md5(IdUtil.simpleUUID());
    }

}
