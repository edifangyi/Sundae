package com.fangyi.sundae.system.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangyi.component_library.config.AppPahts;
import com.fangyi.component_library.utils.FragmentUtils;
import com.fangyi.module_android.AndroidFragment;
import com.fangyi.sundae.R;
import com.fangyi.sundae.base.MyBaseActivity;
import com.fangyi.sundae.system.bean.LoginBean;
import com.fangyi.sundae.system.mvp.contract.MainContract;
import com.fangyi.sundae.system.mvp.model.MainModel;
import com.fangyi.sundae.system.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class MainActivity extends MyBaseActivity<MainPresenter, MainModel> implements MainContract.View {


    @BindView(R.id.framelayout)
    FrameLayout mFramelayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        AndroidFragment fragment = (AndroidFragment) ARouter.getInstance().build(AppPahts.ANDROID_PAHT).navigation();

        FragmentUtils.add(getSupportFragmentManager(), fragment, R.id.framelayout);
    }

    @Override
    public void onLoginSucceed(LoginBean succeed) {

    }

}
