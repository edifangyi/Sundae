package com.fangyi.module_android.demo_rxbinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangyi.component_library.base.BaseActivity;
import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxLongClicksActivity extends BaseToolbarActivity {
    private final static String key_code = "title";

    @BindView(R2.id.btn_clicks)
    Button mBtnClicks;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;

    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxLongClicksActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_long_clicks;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        /**
         * 防止多次点击--2秒内执行一次点击
         */
        RxView.clicks(mBtnClicks)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(c ->{

                    TextView textView = new TextView(mContext);
                    textView.setText(" 点击 ==> " + System.currentTimeMillis());

                    mWhiteBoard.addView(textView);
                });


        /**
         * 长按事件
         */
        RxView.longClicks(mBtnClicks)
                .subscribe(c -> {

                    TextView textView = new TextView(mContext);
                    textView.setText(" 长按 ==> " + System.currentTimeMillis());

                    mWhiteBoard.addView(textView);

                });
    }


    @Override
    protected String setToolbarTitle() {
        return getIntent().getStringExtra(key_code);
    }

    @Override
    protected void onBack() {
        onBackPressed();
    }
}
