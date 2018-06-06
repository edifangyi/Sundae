package com.fangyi.module_android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/1
 * 说    明：
 * ================================================
 */
public class ButterKnifeFragment extends BaseFragment {


    @BindView(R2.id.btn_butterknife)
    Button mBtnButterknife;


    public static ButterKnifeFragment newInstance() {
        ButterKnifeFragment fragment = new ButterKnifeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_butterknife;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this, rootView);
        mBtnButterknife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("ButterKnife 绑定成功！");
            }
        });
    }


}
