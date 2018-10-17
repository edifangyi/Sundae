package com.fangyi.module_android.ui;


import android.os.Bundle;

import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class PopupFragment extends BaseFragment {


    public static PopupFragment newInstance() {
        PopupFragment fragment = new PopupFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_popup;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


}
