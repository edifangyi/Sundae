package com.fangyi.module_me;

import android.os.Bundle;

import com.blankj.utilcode.util.FragmentUtils;
import com.fangyi.component_library.base.BaseActivity;
import com.fangyi.module_me.ui.MeFragment;

public class MeActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MeFragment meFragment = new MeFragment();


        FragmentUtils.add(getSupportFragmentManager(), meFragment, R.id.framelayout);
    }
}
