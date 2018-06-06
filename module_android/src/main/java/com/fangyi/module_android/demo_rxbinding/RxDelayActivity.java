package com.fangyi.module_android.demo_rxbinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxDelayActivity extends BaseToolbarActivity {

    private final static String key_code = "title";

    @BindView(R2.id.btn_clicks)
    Button mBtnClicks;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;


    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxDelayActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_delay;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);


        mBtnClicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textView = new TextView(mContext);
                textView.setText(" 开始 ==> " + System.currentTimeMillis());

                mWhiteBoard.addView(textView);

                // 2 秒后发送数据
                Observable.timer(2, TimeUnit.SECONDS)
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                KLog.e("+====1===========");
                            }

                            @Override
                            public void onNext(Long value) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView = new TextView(mContext);
                                        textView.setText(" 2 秒后发送数据 ==> " + System.currentTimeMillis());

                                        mWhiteBoard.addView(textView);
                                    }
                                });

                            }

                            @Override
                            public void onError(Throwable e) {
                                KLog.e("+====2===========");
                            }

                            @Override
                            public void onComplete() {
                                KLog.e("+====3===========");
                            }
                        });
            }
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
