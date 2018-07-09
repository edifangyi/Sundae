package com.fangyi.module_vue.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.config.SundaeAppRoutingPaths;
import com.fangyi.component_library.mvp.IService;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/23
 * 说    明：
 * ================================================
 */
@Route(path = SundaeAppRoutingPaths.WX_LOGIN)
public class WxText implements IService {
    @Override
    public void init(Context context) {

    }


    @Override
    public String vueTag() {
        return "hahaha";
    }
}
