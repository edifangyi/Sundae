package com.fangyi.module_android.demo_md;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.fangyi.component_library.base.BaseActivity;
import com.fangyi.module_android.R;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/23
 * 说    明：
 * ================================================
 */
public class ToolBarActivity extends BaseActivity {

    public static void startAction(Activity activity, boolean isFinish) {
        Intent intent = new Intent(activity, ToolBarActivity.class);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_md_tool_bar;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
