package com.fangyi.module_android.demo_x5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.component_library.widget.TBSWebView;
import com.fangyi.module_android.R;
import com.socks.library.KLog;
import com.yanzhenjie.kalle.Headers;
import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.download.Callback;
import com.yanzhenjie.kalle.download.Download;

import java.io.File;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/7/9
 * 说    明：
 * ================================================
 */
public class X5TBSActivity extends BaseToolbarActivity {

    private TBSWebView mTbsWebView;

    public static void startAction(Activity activity, boolean isFinish, String url, String title) {
        Intent intent = new Intent(activity, X5TBSActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }


    @Override
    protected String setToolbarTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    protected void onBack() {
        onBackPressed();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_x5_tbs;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();


        mTbsWebView.setOnGetFilePathListener(new TBSWebView.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(TBSWebView tbsWebView) {
                downloadFile(getIntent().getStringExtra("url"));
            }
        });

        mTbsWebView.show();

    }

    private void downloadFile(String url) {
        Kalle.Download.get(url)
                .directory(Environment.getExternalStorageDirectory() + File.separator + "Sundae" + File.separator + "AnnexCache")
                .policy(new Download.Policy() {
                    @Override
                    public boolean isRange() {
                        return true;
                    }

                    @Override
                    public boolean allowDownload(int code, Headers headers) {
                        return true;
                    }

                    @Override
                    public boolean oldAvailable(String path, int code, Headers headers) {
                        return true;
                    }
                })
                .onProgress((progress, byteCount, speed) -> {

                })
                .perform(new Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish(String path) {

                        mTbsWebView.displayFile(path);
                    }

                    @Override
                    public void onException(Exception e) {
                        KLog.e("======");
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onEnd() {
                    }
                });
    }

    private void initView() {
        mTbsWebView = findViewById(R.id.tbsWebView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTbsWebView != null)
            mTbsWebView.onStopDisplay();
    }
}
