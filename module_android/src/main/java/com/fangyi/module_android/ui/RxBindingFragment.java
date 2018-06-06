package com.fangyi.module_android.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;
import com.fangyi.module_android.adapter.LottieAdapter;
import com.fangyi.module_android.adapter.RxBindingAdapter;
import com.fangyi.module_android.demo_rxbinding.RxCheckedChangesActivity;
import com.fangyi.module_android.demo_rxbinding.RxClicksActivity;
import com.fangyi.module_android.demo_rxbinding.RxCountdownActivity;
import com.fangyi.module_android.demo_rxbinding.RxDelayActivity;
import com.fangyi.module_android.demo_rxbinding.RxFormValidationActivity;
import com.fangyi.module_android.demo_rxbinding.RxLongClicksActivity;
import com.fangyi.module_android.demo_rxbinding.RxPollActivity;
import com.fangyi.module_android.demo_rxbinding.RxSearchActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/25
 * 说    明：
 * ================================================
 */
public class RxBindingFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RxBindingAdapter mAdapter;
    private List<String> list;

    public static RxBindingFragment newInstance() {
        RxBindingFragment fragment = new RxBindingFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rx_binding;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        list = new ArrayList<>();
        list.add("防止重复点击");
        list.add("长按动作");
        list.add("CheckBox选中就修改View");
        list.add("搜索优化");
        list.add("注册登录的表单验证");
        list.add("倒计时");
        list.add("延时执行");
        list.add("轮询");
        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mRecyclerView.setNestedScrollingEnabled(true);

        mAdapter = new RxBindingAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position)) {
                    case "防止重复点击":
                        RxClicksActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "长按动作":
                        RxLongClicksActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "CheckBox选中就修改View":
                        RxCheckedChangesActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "搜索优化":
                        RxSearchActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "注册登录的表单验证":
                        RxFormValidationActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "倒计时":
                        RxCountdownActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "延时执行":
                        RxDelayActivity.startAction(mActivity, false, list.get(position));
                        break;
                    case "轮询":
                        RxPollActivity.startAction(mActivity, false, list.get(position));
                        break;
                }
            }
        });

    }


    private void initView() {
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
    }
}
