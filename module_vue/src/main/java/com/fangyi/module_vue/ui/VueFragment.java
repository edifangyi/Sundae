package com.fangyi.module_vue.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.app.SundaeBaseFragment;
import com.fangyi.component_library.config.SundaeAppRoutingPaths;
import com.fangyi.module_vue.R;
import com.fangyi.module_vue.mvp.contract.VueContract;
import com.fangyi.module_vue.mvp.model.VueModel;
import com.fangyi.module_vue.mvp.presenter.VuePresenter;

/**
 * Create By admin On 2017/7/11
 * 功能：
 */
@Route(path = SundaeAppRoutingPaths.VUE_PAHT)
public class VueFragment extends SundaeBaseFragment<VuePresenter, VueModel> implements VueContract.View {


    public static VueFragment newInstance() {
        VueFragment fragment = new VueFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vue;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


}
