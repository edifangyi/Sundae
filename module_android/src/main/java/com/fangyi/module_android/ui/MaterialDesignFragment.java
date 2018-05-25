package com.fangyi.module_android.ui;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;
import com.fangyi.module_android.adapter.DemoAdapter;
import com.fangyi.module_android.demo_md.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class MaterialDesignFragment extends BaseFragment {


    private android.support.v7.widget.RecyclerView mRecyclerView;
    private DemoAdapter mAdapter;

    public static MaterialDesignFragment newInstance() {
        MaterialDesignFragment fragment = new MaterialDesignFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_material_design;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));

        List<String> list = new ArrayList<>();
        list.add("ToolBar");
        list.add("Menu");
        list.add("NavigationView");
        list.add("FloatingActionButton");
        list.add("DrawerLayout");
        list.add("Behavior");
        list.add("CoordinatorLayout");
        list.add("AppbarLayout ");
        list.add("TabLayout");
        list.add("CardView");
        list.add("TextInputLayout");
        list.add("TextInputEdittext");
        list.add("ConstraintLayout");

        mAdapter = new DemoAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                ToolBarActivity.startAction(mActivity, false);

            }
        });


    }


    private void initView() {
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
    }
}
