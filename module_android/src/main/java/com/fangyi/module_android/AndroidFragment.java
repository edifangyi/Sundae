package com.fangyi.module_android;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.base.BasePagerAdapter;
import com.fangyi.component_library.config.SundaeAppRoutingPaths;
import com.fangyi.module_android.ui.ButterKnifeFragment;
import com.fangyi.module_android.ui.DataBindingFragment;
import com.fangyi.module_android.ui.GlideFragment;
import com.fangyi.module_android.ui.KotlinFragment;
import com.fangyi.module_android.ui.LottieFragment;
import com.fangyi.module_android.ui.MaterialDesignFragment;
import com.fangyi.module_android.ui.RxBindingFragment;
import com.fangyi.module_android.ui.X5WebFragment;

import java.util.ArrayList;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
@Route(path = SundaeAppRoutingPaths.ANDROID_PAHT)
public class AndroidFragment extends BaseFragment {

    private String mTitle;

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


        initView();

        initToolbar();


        ArrayList<String> titles = new ArrayList<>();
        titles.add("Lottie");
        titles.add("Glide");
        titles.add("MaterialDesign");
        titles.add("Kotlin");
        titles.add("RxBinding");
        titles.add("DataBinding");
        titles.add("ButterKnife");
        titles.add("X5WebView");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(LottieFragment.newInstance());
        fragments.add(GlideFragment.newInstance());
        fragments.add(MaterialDesignFragment.newInstance());
        fragments.add(KotlinFragment.newInstance());
        fragments.add(RxBindingFragment.newInstance());
        fragments.add(DataBindingFragment.newInstance());
        fragments.add(ButterKnifeFragment.newInstance());
        fragments.add(X5WebFragment.newInstance());
        BasePagerAdapter adapter = new BasePagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewPager, true);


//        IService iService = ARouter.getInstance().navigation(IService.class);


//        mTvHahahaha.setOnClickListener(v -> {
//            String s = iService.vueTag();
//            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//        });
//
//        mTvTag1.setOnClickListener(v -> ARouter.getInstance().build(AppPahts.VUE_ACTIVITY_PAHT).navigation());
    }

    private void initToolbar() {
        mTitle = getArguments().getString("title");
        mToolbar.setTitle(mTitle);
    }


    private void initView() {
        mAppBarLayout = rootView.findViewById(R.id.appBarLayout);
        mToolbar = rootView.findViewById(R.id.toolbar);
        mTabLayout = rootView.findViewById(R.id.tabLayout);
        mViewPager = rootView.findViewById(R.id.viewPager);
    }
}
