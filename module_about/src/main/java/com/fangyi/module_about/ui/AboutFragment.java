package com.fangyi.module_about.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.config.AppPahts;
import com.fangyi.module_about.R;
import com.socks.library.KLog;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/25
 * 说    明：
 * ================================================
 */
@Route(path = AppPahts.ABOUT_PAHT)
public class AboutFragment extends BaseFragment {
    private android.support.design.widget.AppBarLayout mAppBarLayout;
    private android.support.v7.widget.Toolbar mToolbar;
    private com.fangyi.module_about.widget.MultiShapeView mIvCircle;
    private android.widget.LinearLayout mLlBottom;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //这个是计算App bar Layout的总高度的API
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float ratio = Math.abs((float) verticalOffset / totalScrollRange);
                mToolbar.setAlpha(1 - ratio);


                KLog.e("============" + "onOffsetChanged: " + ratio + "---" + verticalOffset);

            }
        });

        mIvCircle.setImageResource(R.mipmap.image_head);
    }

    private void initView() {
        mAppBarLayout = rootView.findViewById(R.id.appBarLayout);
        mToolbar = rootView.findViewById(R.id.toolbar);
        mIvCircle = rootView.findViewById(R.id.iv_circle);


        mLlBottom = rootView.findViewById(R.id.ll_bottom);
    }
}
