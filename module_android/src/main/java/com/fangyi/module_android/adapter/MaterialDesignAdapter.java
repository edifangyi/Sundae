package com.fangyi.module_android.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangyi.module_android.R;

import java.util.List;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/23
 * 说    明：
 * ================================================
 */
public class MaterialDesignAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MaterialDesignAdapter(@Nullable List<String> data) {
        super(R.layout.item_material_design, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text, item);
    }
}
