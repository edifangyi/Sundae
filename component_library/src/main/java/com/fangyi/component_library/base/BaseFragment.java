package com.fangyi.component_library.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/2/27
 * 权    限：
 * 说    明：
 * ================================================
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;

    protected BaseActivity mActivity;
    public Context mContext;
    public Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false);

        mActivity = (BaseActivity) getActivity();
        mContext = getActivity();
        mHandler = new Handler();

        init(savedInstanceState);

        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
