package com.fangyi.sundae.system.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangyi.component_library.app.MyBaseActivity;
import com.fangyi.component_library.base.BasePagerAdapter;
import com.fangyi.component_library.config.AppPahts;
import com.fangyi.module_android.AndroidFragment;
import com.fangyi.module_vue.ui.fragment.VueFragment;
import com.fangyi.sundae.R;
import com.fangyi.sundae.system.mvp.contract.MainContract;
import com.fangyi.sundae.system.mvp.model.MainModel;
import com.fangyi.sundae.system.mvp.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class MainActivity extends MyBaseActivity<MainPresenter, MainModel> implements MainContract.View {


    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.navPage)
    PageNavigationView mNavPage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();


        AndroidFragment androidFragment = (AndroidFragment) ARouter.getInstance().build(AppPahts.ANDROID_PAHT).navigation();
        AndroidFragment a = (AndroidFragment) ARouter.getInstance().build(AppPahts.ANDROID_PAHT).navigation();
        VueFragment vueFragment = (VueFragment) ARouter.getInstance().build(AppPahts.VUE_PAHT).navigation();
        VueFragment b = (VueFragment) ARouter.getInstance().build(AppPahts.VUE_PAHT).navigation();

        fragments.add(androidFragment);
        titles.add("Android");
        fragments.add(a);
        titles.add("Vue");
        fragments.add(b);
        titles.add("GitHub");
        fragments.add(vueFragment);
        titles.add("Utils");


        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);

        NavigationController navigationController = mNavPage.custom()
                .addItem(newItem(0, R.drawable.main_ic_android_uncheck, R.drawable.main_ic_android_check, "Android"))
                .addItem(newItem(1, R.drawable.main_ic_vue_uncheck, R.drawable.main_ic_vue_check, "Vue"))
                .addItem(newItem(2, R.drawable.main_ic_github_uncheck, R.drawable.main_ic_github_check, "GitHub"))
                .addItem(newItem(3, R.drawable.main_ic_utils_uncheck, R.drawable.main_ic_utils_check, "QQ"))
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
}
