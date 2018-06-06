package com.fangyi.module_android.demo_rxbinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
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
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxSearchActivity extends BaseToolbarActivity {

    private final static String key_code = "title";


    @BindView(R2.id.edit_search)
    EditText mEditSearch;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;


    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxSearchActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_search;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        //优化搜索功能
        RxTextView.textChanges(mEditSearch)
                // 跳过一开始et内容为空时的搜索
                .skip(1)
                //debounce 在一定的时间内没有操作就会发送事件
                .debounce(1000, TimeUnit.MILLISECONDS)
                //下面这两个都是数据转换
                //flatMap：当同时多个网络请求访问的时候，前面的网络数据会覆盖后面的网络数据
                //switchMap：当同时多个网络请求访问的时候，会以最后一个发送请求为准，前面网路数据会被最后一个覆盖
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(CharSequence charSequence) throws Exception {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String searchKey = charSequence.toString();
                                TextView textView = new TextView(mContext);
                                textView.setText(" 要搜索的内容 ==> " + searchKey + "  " + System.currentTimeMillis());
                                mWhiteBoard.addView(textView);
                            }
                        });


                        //这里执行网络操作，获取数据
                        List<String> list = new ArrayList<String>();
                        list.add("PHP");
                        list.add("可爱多");
                        list.add("HTML");
                        list.add("C++");
                        list.add("Android");
                        list.add("Java");
                        return Observable.just(list);
                    }
                })
                // .onErrorResumeNext()
                //网络操作，获取我们需要的数据
                .subscribeOn(Schedulers.io())
                //界面更新在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        TextView textView1 = new TextView(mContext);
                        textView1.setText(" 搜索到 ==> " + strings.size() + "条数据" + System.currentTimeMillis());
                        mWhiteBoard.addView(textView1);

                        for (String string : strings) {
                            TextView textView = new TextView(mContext);
                            textView.setText(" 搜索到的内容 ==> " + string);
                            mWhiteBoard.addView(textView);
                        }
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
