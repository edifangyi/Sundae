package com.fangyi.module_me.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.config.SundaeAppRoutingPaths;
import com.fangyi.component_library.widget.MultiShapeView;
import com.fangyi.module_me.R;
import com.fangyi.module_me.adapter.MeMessageAdapter;
import com.fangyi.module_me.bean.MeMessageBean;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/25
 * 说    明：
 * ================================================
 */
@Route(path = SundaeAppRoutingPaths.ME_PAHT)
public class MeFragment extends BaseFragment {
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private MultiShapeView mMsvHead;
    private RecyclerView mRvMessage;
    private MeMessageAdapter mAdapter;


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

        List<MeMessageBean> list = new ArrayList<>();
        list.add(new MeMessageBean(0, R.drawable.ic_location, "现居 - 重庆"));
        list.add(new MeMessageBean(1, R.drawable.ic_business, "Android开发工程师"));
        list.add(new MeMessageBean(2, R.drawable.ic_github, "Github", "https://github.com/edifangyi"));
        mAdapter = new MeMessageAdapter(list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (list.get(position).getTitle()) {

                }


            }
        });
        mRvMessage.setAdapter(mAdapter);

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

        mMsvHead = rootView.findViewById(R.id.msv_head);
        mRvMessage = rootView.findViewById(R.id.rv_message);
    }
}
