package com.fangyi.module_android.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.fangyi.component_library.base.BaseFragment;
import com.fangyi.module_android.R;
import com.fangyi.module_android.demo_x5.X5TBSActivity;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/7/9
 * 说    明：
 * ================================================
 */
public class X5WebFragment extends BaseFragment {

    private com.fangyi.component_library.widget.X5WebView mWebView;

    public static X5WebFragment newInstance() {
        X5WebFragment fragment = new X5WebFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_x5_web;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.clearCache(true);
        mWebView.clearHistory();

        mWebView.addJavascriptInterface(new TBSWebViewProxy(), "TBSWebViewProxy");

        mWebView.loadUrl("https://edifangyi.github.io/neepunotice_01.html");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

            }
        });
    }

    public class TBSWebViewProxy {
        @JavascriptInterface
        public void showAnnex(String url, String title) {
            X5TBSActivity.startAction((Activity) mContext, false, url, title);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    private void initView() {
        mWebView = rootView.findViewById(R.id.webView);
    }
}
