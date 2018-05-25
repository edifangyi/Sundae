package com.fangyi.module_android.ui;

import android.os.Bundle;

import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/25
 * 说    明：
 * ================================================
 */
public class RxJavaFragment extends BaseFragment {
    public static RxJavaFragment newInstance() {
        RxJavaFragment fragment = new RxJavaFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rxjava;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
