package com.fangyi.module_me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangyi.module_me.R;
import com.fangyi.module_me.bean.MeMessageBean;

import java.util.List;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/27
 * 说    明：
 * ================================================
 */
public class MeMessageAdapter extends BaseQuickAdapter<MeMessageBean, BaseViewHolder> {

    public MeMessageAdapter(@Nullable List<MeMessageBean> data) {
        super(R.layout.item_me_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeMessageBean item) {
        helper.setBackgroundRes(R.id.iv_icon, item.getIcon());
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
