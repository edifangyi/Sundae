package com.fangyi.module_android.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.component_library.utils.VisibilityCheckUtil;
import com.fangyi.module_android.R;
import com.fangyi.module_android.adapter.LottieAdapter;
import com.fangyi.module_android.adapter.LottieBannerAdapter;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/23
 * 说    明：
 * ================================================
 */
public class LottieFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    private int mFirstVisiblePosition;
    private int mLastVisiblePosition;
    private int mVisibleCount;
    private GridLayoutManager mGridLayoutManager;
    private LottieAdapter mAdapter;

    public static LottieFragment newInstance() {
        LottieFragment fragment = new LottieFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lottie;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();


        List<String> list = new ArrayList<>();

        list.add("AndroidWave.json");
        list.add("HamburgerArrow.json");
        list.add("lottiefiles.com - Camera.json");
        list.add("lottiefiles.com - Emoji Shock.json");
        list.add("lottiefiles.com - Emoji Tongue.json");
        list.add("lottiefiles.com - Emoji Wink.json");
        list.add("lottiefiles.com - Favorite Star.json");
        list.add("lottiefiles.com - Gears.json");
        list.add("lottiefiles.com - Loading 1.json");
        list.add("lottiefiles.com - Mail Sent.json");
        list.add("lottiefiles.com - React.json");
        list.add("lottiefiles.com - Retweet.json");
        list.add("lottiefiles.com - Touch ID.json");
        list.add("lottiefiles.com - VR.json");
        list.add("LottieLogo1.json");
        list.add("LottieLogo2.json");
        list.add("lottifiles.com - QR Scan.json");
        list.add("PinJump.json");
        list.add("TwitterHeart.json");


        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mRecyclerView.setNestedScrollingEnabled(true);

        mAdapter = new LottieAdapter(list);
        mRecyclerView.setAdapter(mAdapter);


        List<String> banners = new ArrayList<>();
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");
        banners.add("Learn");

        RecyclerView mRecyclerViewBanner = new RecyclerView(mContext);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        mRecyclerViewBanner.setLayoutParams(layoutParams);
        mRecyclerViewBanner.setHasFixedSize(true);
        mRecyclerViewBanner.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewBanner.setAdapter(new LottieBannerAdapter(banners));
        mRecyclerViewBanner.setNestedScrollingEnabled(false);
        mRecyclerViewBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mAdapter.addHeaderView(mRecyclerViewBanner);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: //滚动停止
                        autoPlay();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING: //手指拖动
                        autoPlay();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING: //惯性滚动
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mGridLayoutManager == null) {
                    return;
                }
                //在屏幕可见区域的第一项位置
                mFirstVisiblePosition = mGridLayoutManager.findFirstVisibleItemPosition();
                //在屏幕可见区域的最后一项位置
                mLastVisiblePosition = mGridLayoutManager.findLastVisibleItemPosition();
                //屏幕可见项的数目
                mVisibleCount = mLastVisiblePosition - mFirstVisiblePosition;

            }
        });


    }

    private void autoPlay() {
        for (int i = 0; i < mVisibleCount + 1; i++) {
            if (mGridLayoutManager != null && mGridLayoutManager.getChildAt(i) != null && mGridLayoutManager.getChildAt(i).findViewById(R.id.lottie) != null) {
                LottieAnimationView lottie = mGridLayoutManager.getChildAt(i).findViewById(R.id.lottie);
                if (VisibilityCheckUtil.checkVisibility(lottie) == VisibilityCheckUtil.ALL_INVISIBLE) {
                    lottie.pauseAnimation();
                } else {
                    lottie.resumeAnimation();
                }
            }
        }
        KLog.e("=autoPlay=======First=====" + mFirstVisiblePosition + "===Last==" + mLastVisiblePosition);
    }

    private void initView() {
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

    }
}
