package com.fangyi.module_android;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.config.AppPahts;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/22
 * 说    明：
 * ================================================
 */
@Route(path = AppPahts.ANDROID_PAHT)
public class AndroidFragment extends BaseFragment {
    
    @Override
    protected int getLayoutId() {
        return R.layout.android_fragment_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
