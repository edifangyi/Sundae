package com.fangyi.module_android.ui;


import android.os.Bundle;

import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class KotlinFragment extends BaseFragment {


    public static KotlinFragment newInstance() {
        KotlinFragment fragment = new KotlinFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kotlin;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


}
