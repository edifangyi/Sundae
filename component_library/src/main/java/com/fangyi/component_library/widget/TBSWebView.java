package com.fangyi.component_library.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.socks.library.KLog;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/7/8
 * 说    明：
 * ================================================
 */
public class TBSWebView extends FrameLayout implements TbsReaderView.ReaderCallback {

    private TbsReaderView mTbsReaderView;
    private Context mContext;


    public TBSWebView(@NonNull Context context) {
        this(context, null, 0);
    }

    public TBSWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TBSWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTbsReaderView = new TbsReaderView(context, this);

        this.addView(mTbsReaderView, new LinearLayout.LayoutParams(-1, -1));
        this.mContext = context;
    }

    private OnGetFilePathListener mOnGetFilePathListener;

    public void setOnGetFilePathListener(OnGetFilePathListener mOnGetFilePathListener) {
        this.mOnGetFilePathListener = mOnGetFilePathListener;
    }

    public void displayFile(String path) {

        File mFile = new File(path);

        if (mFile != null && !TextUtils.isEmpty(mFile.getPath())) {
            //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
            String bsReaderTemp = Environment.getExternalStorageDirectory() + File.separator +"TbsReaderTemp";
            File bsReaderTempFile = new File(bsReaderTemp);

            if (!bsReaderTempFile.exists()) {
                KLog.e("====准备创建/storage/emulated/0/TbsReaderTemp！！");
                boolean mkdir = bsReaderTempFile.mkdir();
                if (!mkdir) {
                    KLog.e("====创建/storage/emulated/0/TbsReaderTemp失败！！！！！");
                }
            }

            //加载文件
            Bundle localBundle = new Bundle();
            KLog.e(mFile.toString());
            localBundle.putString("filePath", mFile.getPath());

            localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + File.separator + "TbsReaderTemp");

            if (this.mTbsReaderView == null)
                this.mTbsReaderView = getTbsReaderView(mContext);
            boolean bool = this.mTbsReaderView.preOpen(getFileType(mFile.toString()), false);
            if (bool) {
                this.mTbsReaderView.openFile(localBundle);
            }
        } else {
            KLog.e("====文件路径无效！");
        }

    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            KLog.e("=======paramString---->null");
            return str;
        }
        KLog.e("======paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            KLog.e("i <= -1");
            return str;
        }

        str = paramString.substring(i + 1);
        KLog.e("========paramString.substring(i + 1)------>" + str);
        return str;
    }

    private TbsReaderView getTbsReaderView(Context context) {
        return new TbsReaderView(context, this);
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
        KLog.e("=====****************************************************" + integer);
    }

    public void show() {
        if (mOnGetFilePathListener != null) {
            mOnGetFilePathListener.onGetFilePath(this);
        }
    }

    /***
     * 将获取File路径的工作，“外包”出去
     */
    public interface OnGetFilePathListener {
        void onGetFilePath(TBSWebView tbsWebView);
    }

    public void onStopDisplay() {
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }
}
