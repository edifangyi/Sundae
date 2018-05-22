package com.fangyi.component_library.config;


import java.util.HashMap;
import java.util.Map;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2017/12/13
 * 说    明：
 * ================================================
 */
public class ApiParams {


    //密码登录 和 手机动态登录
    public static Map<String, String> getLoginParams(String userName, String userPwd) {
        Map<String, String> map = new HashMap<>();

        map.put("name", userName);
        map.put("password", userPwd);
        return map;
    }

}
