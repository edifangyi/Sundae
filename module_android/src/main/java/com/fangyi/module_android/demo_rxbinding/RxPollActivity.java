package com.fangyi.module_android.demo_rxbinding;

import android.app.Activity;
import android.content.Intent;
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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
public class RxPollActivity extends BaseToolbarActivity {

    private final static String key_code = "title";

    @BindView(R2.id.btn_poll_0)
    Button mBtnPoll0;
    @BindView(R2.id.btn_poll_1)
    Button mBtnPoll1;
    @BindView(R2.id.btn_poll_2)
    Button mBtnPoll2;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;

    public Disposable mDisposable;

    private int count;

    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxPollActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_poll;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        mBtnPoll0.setOnClickListener(v -> {

            mBtnPoll0.setEnabled(false);

            TextView textView = new TextView(mContext);
            textView.setText("开始轮询 延迟2秒，间隔2秒，从0递增 ==> " + System.currentTimeMillis());
            mWhiteBoard.addView(textView);
            /**
             * 每隔2秒 输出一次日志
             * 每隔2秒产生一个数字，0开始 递增
             */
            Observable.interval(2, TimeUnit.SECONDS)
                    .subscribe(new Observer<Long>() {


                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;

                        }

                        @Override
                        public void onNext(Long value) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = new TextView(mContext);
                                    textView.setText(" 输出日志 ==> " + value + " ===== " + System.currentTimeMillis());

                                    mWhiteBoard.addView(textView);
                                }
                            });

                            if (value == 5L) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView = new TextView(mContext);
                                        textView.setText(" 日志输出结束 ==> " + value + " ===== " + System.currentTimeMillis());

                                        mWhiteBoard.addView(textView);

                                        mBtnPoll0.setEnabled(true);
                                    }
                                });
                                mDisposable.dispose();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        });

        mBtnPoll1.setOnClickListener(v -> {

            TextView textView = new TextView(mContext);
            textView.setText("使用schedulePeriodically做轮询请求 间隔3秒 ==> " + System.currentTimeMillis());
            mWhiteBoard.addView(textView);

            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(final ObservableEmitter<String> e) throws Exception {

                    Schedulers.newThread().createWorker()
                            .schedulePeriodically(new Runnable() {
                                @Override
                                public void run() {
                                    e.onNext("net work-----");
                                }
                            }, 0, 3, TimeUnit.SECONDS);

                }
            }).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = new TextView(mContext);
                            textView.setText(" 输出日志 ==> " + s + " ==== " + System.currentTimeMillis());

                            mWhiteBoard.addView(textView);
                        }
                    });
                }
            });
        });

        mBtnPoll2.setOnClickListener(v ->

                Observable.just(100)
                        .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {

                                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                                // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
                                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(Object o) throws Exception {
                                        if (count > 4) {
                                            return Observable.error(new Throwable("轮询完成"));
                                        }

                                        return Observable.just(1)
                                                .delay(2, TimeUnit.SECONDS);
                                    }
                                });
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer value) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView = new TextView(mContext);
                                        textView.setText(" repeat ==> " + count + " ==== " + System.currentTimeMillis());

                                        mWhiteBoard.addView(textView);
                                    }
                                });

                                count++;
                            }

                            @Override
                            public void onError(Throwable e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView = new TextView(mContext);
                                        textView.setText(" onError ==> " + e.toString() + " ==== " + System.currentTimeMillis());

                                        mWhiteBoard.addView(textView);
                                    }
                                });

                            }

                            @Override
                            public void onComplete() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView = new TextView(mContext);
                                        textView.setText(" repeat ==> complete ==== " + System.currentTimeMillis());

                                        mWhiteBoard.addView(textView);
                                    }
                                });

                            }
                        }));
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
