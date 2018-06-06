package com.fangyi.module_android.demo_rxbinding;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxCountdownActivity extends BaseToolbarActivity {

    private final static String key_code = "title";

    private Disposable disposable;

    @BindView(R2.id.btn_clicks)
    Button mBtnClicks;
    @BindView(R2.id.btn_close)
    Button mBtnClose;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;


    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxCountdownActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_countdown;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mBtnClose.setEnabled(false);

        mBtnClicks.setOnClickListener(v -> {

            //倒计时操作
            final int count = 10;
            Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                    .take(count + 1)//设置循环次数
                    .map(new Function<Long, Long>() {
                        @Override
                        public Long apply(Long aLong) throws Exception {
                            return count - aLong;
                        }
                    })
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            //在发送数据的时候设置为不能点击
                            mBtnClicks.setEnabled(false);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onNext(Long value) {
                            TextView textView = new TextView(mContext);
                            textView.setText(" 倒计时 ==> " + value);

                            mWhiteBoard.addView(textView);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            mBtnClicks.setEnabled(true);
                            mBtnClose.setEnabled(false);
                        }
                    });

            mBtnClose.setEnabled(true);
        });

        mBtnClose.setOnClickListener(v -> {
            if (disposable != null || !disposable.isDisposed()) {
                disposable.dispose();
            }

            mBtnClose.setEnabled(false);
            mBtnClicks.setEnabled(true);
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
