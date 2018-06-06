package com.fangyi.module_me.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.config.AppPahts;
import com.fangyi.module_me.R;
import com.fangyi.module_me.widget.MultiShapeView;


/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/25
 * 说    明：
 * ================================================
 */
@Route(path = AppPahts.ME_PAHT)
public class MeFragment extends BaseFragment {
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private MultiShapeView mMsvHead;
    private RecyclerView mRvMessage;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();

        initToolbar();

        mMsvHead.setImageResource(R.mipmap.image_head);

        mRvMessage.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRvMessage.setLayoutManager(linearLayoutManager);

        mRvMessage.setNestedScrollingEnabled(true);


//        mRvMessage.setAdapter(mAdapter);

    }

    private void initToolbar() {

        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            //这个是计算App bar Layout的总高度的API
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            float ratio = Math.abs((float) verticalOffset / totalScrollRange);

            if (ratio >= 0.8 && ratio < 0.9) {
                mToolbar.setAlpha((float) (ratio - 0.7));
            } else if (ratio >= 0.9 && ratio < 0.95) {
                mToolbar.setAlpha((float) (ratio - 0.6));
            } else if (ratio >= 0.95 && ratio <= 1) {
                mToolbar.setAlpha((float) (ratio - 0.5));
            } else {
                mToolbar.setAlpha(0);
            }
        });
    }

    private void initView() {
        mAppBarLayout = rootView.findViewById(R.id.appBarLayout);
        mToolbar = rootView.findViewById(R.id.toolbar);


        mTabLayout = rootView.findViewById(R.id.tabLayout);

        mMsvHead = rootView.findViewById(R.id.msv_head);
        mRvMessage = rootView.findViewById(R.id.rv_message);
    }
}
