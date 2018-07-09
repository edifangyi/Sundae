package com.fangyi.component_library.utils.update;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fangyi.component_library.utils.SharedPrefUtil;
import com.fangyi.component_library.utils.kalle.callback.ResponseCallback;
import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.download.Callback;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/7/7
 * 说    明：
 * ================================================
 */
public class UpdateUtils {

    private static String KEY_IGNORE_VERSION = "ignore_version";

    private static Builder builder;
    private Context mContext = builder.mContext;
    private String mUrl = builder.mUrl;
    private SharedPrefUtil mSharedPrefUtil = new SharedPrefUtil(mContext);

    private OnUpdateListener mOnUpdateListener = builder.mOnUpdateListener;

    public UpdateUtils() {


        String version = getAppVersionName();

        Kalle.get(mUrl)
                .param("", getAppVersionName())
                .perform(new ResponseCallback<UpdateBean>(mContext) {
                    @Override
                    protected void onResponse(UpdateBean response) {

                        String ignoreVersion = mSharedPrefUtil.getString(KEY_IGNORE_VERSION, version);

                        if (ignoreVersion.equals(response.getNewVersion())) {
                            if (mOnUpdateListener != null)
                                mOnUpdateListener.onNoUpdate();
                            return;
                        } else if (!response.isLatest()) {
                            if (mOnUpdateListener != null)
                                mOnUpdateListener.onNoUpdate();
                            return;
                        }

                        String content = response.isForced() ? "您需要更新应用才可以继续使用\n\n" : "";
                        content += "最新版本：v" + response.getNewVersion() + "\n";
                        content += "新版本大小：" + response.getApkSize() + "\n\n";
                        content += "更新内容\n" + response.getUpdateContent();
                        new MaterialDialog.Builder(mContext)
                                .cancelable(false)
                                .title("应用更新")
                                .content(content)
                                .positiveText("立即更新")
                                .onPositive((dialog, which) -> new MaterialDialog.Builder(mContext)
                                        .title("下载中...")
                                        .content("当前速度：0kb/s")
                                        .cancelable(false)
                                        .progress(false, 100, true)
                                        .showListener(progressDialog -> {
                                            MaterialDialog materialDialog = (MaterialDialog) progressDialog;
                                            Kalle.Download.get(response.getApkUrl())
                                                    .directory(Environment.getExternalStorageDirectory() + File.separator + "NeepuNotice" + File.separator + "Download")
                                                    .onProgress((progress, byteCount, speed) -> {
                                                        // progress：进度，[0, 100]。
                                                        // byteCount: 目前已经下载的byte大小。
                                                        // speed：此时每秒下载的byte大小。
                                                        materialDialog.setContent("当前速度：" + (speed / 1024) + "kb/s");
                                                        materialDialog.setProgress(progress);
                                                    })
                                                    .perform(new Callback() {
                                                        @Override
                                                        public void onStart() {

                                                        }

                                                        @Override
                                                        public void onFinish(String path) {
                                                            materialDialog.dismiss();
                                                            AndPermission.with(mContext)
                                                                    .install()
                                                                    .file(new File(path))
                                                                    .start();
                                                        }

                                                        @Override
                                                        public void onException(Exception e) {
                                                            if (mOnUpdateListener != null)
                                                                mOnUpdateListener.onError(e.getMessage());
                                                        }

                                                        @Override
                                                        public void onCancel() {

                                                        }

                                                        @Override
                                                        public void onEnd() {

                                                        }
                                                    });

                                        }).show())
                                .negativeText(response.isForced() ? "" : "以后再说")
                                .onNegative((dialog, which) -> {
                                    if (mOnUpdateListener != null)
                                        mOnUpdateListener.onLater();
                                })
                                .neutralText(response.isForced() ? "退出应用" : "忽略该版")
                                .onNeutral((dialog, which) -> {
                                    if (response.isForced()) {//退出应用
                                        Toast.makeText(mContext, "即将退出应用", Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(() -> ((Activity) mContext).onBackPressed(), 1500);
                                    } else {//忽略该版
                                        mSharedPrefUtil.putString(KEY_IGNORE_VERSION, response.getNewVersion());
                                        if (mOnUpdateListener != null)
                                            mOnUpdateListener.onIgnore(response.getNewVersion());
                                    }
                                }).show();
                    }

                    @Override
                    protected void onError(int code, String message) {
                        if (mOnUpdateListener != null)
                            mOnUpdateListener.onError(message);
                    }
                });


    }

    private String getAppVersionName() {
        String packageName = mContext.getPackageName();

        if (TextUtils.isEmpty(packageName)) return "";
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    public interface OnUpdateListener {
        void onNoUpdate();//暂无更新

        void onLater();//以后再说

        void onIgnore(String newVersion);//无视

        void onError(String message);
    }

    public static Builder getConfig() {
        if (builder == null) {
            synchronized (UpdateUtils.class) {
                builder = new Builder();
            }
        }
        return builder;
    }

    public static class Builder {
        private Context mContext;
        private String mUrl;
        private OnUpdateListener mOnUpdateListener;

        public UpdateUtils check(Context context) {
            this.mContext = context;
            valid();
            return new UpdateUtils();
        }

        public Builder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public Builder setOnUpdateListener(OnUpdateListener listener) {
            mOnUpdateListener = listener;
            return this;
        }

        private void valid() {
            if (this.mContext == null)
                throw new RuntimeException("content can't be null");
        }
    }
}
