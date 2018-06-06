package com.fangyi.module_android.adapter;

import android.graphics.Rect;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangyi.component_library.utils.VisibilityCheckUtil;
import com.fangyi.module_android.R;
import com.socks.library.KLog;

import java.util.List;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/23
 * 说    明：
 * ================================================
 */
public class LottieAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LottieAdapter(@Nullable List<String> data) {
        super(R.layout.item_lottie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LottieAnimationView lottie = helper.getView(R.id.lottie);
        lottie.setAnimation(item);
        lottie.playAnimation();
        helper.setText(R.id.tv_title, item);
    }


}