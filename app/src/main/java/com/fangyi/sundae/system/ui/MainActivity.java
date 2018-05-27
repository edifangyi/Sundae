package com.fangyi.sundae.system.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangyi.component_library.app.MyBaseActivity;
import com.fangyi.component_library.base.BasePagerAdapter;
import com.fangyi.component_library.config.AppPahts;
import com.fangyi.component_library.widget.NoScrollViewPager;
import com.fangyi.module_android.ui.AndroidFragment;
import com.fangyi.module_me.ui.MeFragment;
import com.fangyi.module_vue.ui.VueFragment;
import com.fangyi.sundae.R;
import com.fangyi.sundae.system.mvp.contract.MainContract;
import com.fangyi.sundae.system.mvp.model.MainModel;
import com.fangyi.sundae.system.mvp.presenter.MainPresenter;

import java.util.ArrayList;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class MainActivity extends MyBaseActivity<MainPresenter, MainModel> implements MainContract.View {


    private NoScrollViewPager mViewPager;
    private PageNavigationView mNavPage;

    public static void startAction(Activity activity, boolean isFinish) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();


        ArrayList<String> titles = new ArrayList<>();
        titles.add("Android");
        titles.add("Vue");
        titles.add("GitHub");
        titles.add("Me");

        AndroidFragment androidFragment = (AndroidFragment) ARouter.getInstance().build(AppPahts.ANDROID_PAHT).withString("title", titles.get(0)).navigation();
        VueFragment vueFragment = (VueFragment) ARouter.getInstance().build(AppPahts.VUE_PAHT).withString("title", titles.get(2)).navigation();
        VueFragment a = (VueFragment) ARouter.getInstance().build(AppPahts.VUE_PAHT).withString("title", titles.get(2)).navigation();
        MeFragment meFragment = (MeFragment) ARouter.getInstance().build(AppPahts.ME_PAHT).withString("title", titles.get(3)).navigation();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(androidFragment);
        fragments.add(a);
        fragments.add(vueFragment);
        fragments.add(meFragment);


        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);

        NavigationController navigationController = mNavPage.custom()
                .addItem(newItem(0, R.drawable.main_ic_android_uncheck, R.drawable.main_ic_android_check, titles.get(0)))
                .addItem(newItem(1, R.drawable.main_ic_vue_uncheck, R.drawable.main_ic_vue_check, titles.get(1)))
                .addItem(newItem(2, R.drawable.main_ic_github_uncheck, R.drawable.main_ic_github_check, titles.get(2)))
                .addItem(newItem(3, R.drawable.main_ic_about_uncheck, R.drawable.main_ic_about_check, titles.get(3)))
                .build();

        navigationController.setupWithViewPager(mViewPager);


    }

    private BaseTabItem newItem(int tag, int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        if (tag != 3) {
            normalItemView.setTextCheckedColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            normalItemView.setTextCheckedColor(Color.GRAY);
        }
        return normalItemView;
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mNavPage = findViewById(R.id.navPage);
    }
}
