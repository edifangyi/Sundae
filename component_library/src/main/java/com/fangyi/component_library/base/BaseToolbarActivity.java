package com.fangyi.component_library.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fangyi.component_library.R;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public abstract class BaseToolbarActivity extends BaseActivity {
    TextView mToolbarTitle;
    Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar(setToolbarTitle());
    }

    private void initToolbar(String title) {
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_black);
        mToolbarTitle.setText(title);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> onBack());
    }

    /**
     * 设置标题
     *
     * @return
     */
    protected abstract String setToolbarTitle();


    /**
     * 按下返回箭头操作
     */
    protected abstract void onBack();
}
