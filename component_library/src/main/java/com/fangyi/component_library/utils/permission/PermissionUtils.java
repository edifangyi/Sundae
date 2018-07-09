package com.fangyi.component_library.utils.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.fangyi.component_library.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/4
 * 说    明：
 * ================================================
 */
public class PermissionUtils {
    private static Builder builder;
    private Context mContext = builder.mContext;
    private String[] mPermissions = builder.mPermissions;
    private OnGrantedListener mOnGrantedListener = builder.mOnGrantedListener;


    public PermissionUtils() {
        requestPermission(mPermissions);
    }

    private void requestPermission(String... permissions) {


        AppUtils.getAppVersionName();
        AndPermission.with(mContext)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                        if (mOnGrantedListener != null) {
                            mOnGrantedListener.onSuccess();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
//                        if (AndPermission.hasAlwaysDeniedPermission(ZXApp.getContext(), permissions)) {
//
//                        }
                        Toast.makeText(mContext, "获取权限失败", Toast.LENGTH_SHORT).show();
                        showSettingDialog(mContext, data);
                    }
                })
                .start();
    }

    /**
     * Display setting dialog.
     */
    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton("跳转到设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton(R.string.resume, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(mPermissions);
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission() {
        AndPermission.with(mContext)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        requestPermission(mPermissions);
                    }
                })
                .start();
    }

    public interface OnGrantedListener {
        void onSuccess();
    }

    public static Builder newBuilder() {
        if (builder == null) {
            synchronized (PermissionUtils.class) {
                builder = new Builder();
            }
        }
        return builder;
    }

    public static class Builder {
        private Context mContext;
        private String[] mPermissions;
        private OnGrantedListener mOnGrantedListener;

        public PermissionUtils builder(Context context) {
            this.mContext = context;
            valid();
            return new PermissionUtils();
        }

        public Builder requestPermission(String... permissions) {
            mPermissions = permissions;
            return this;
        }

        public Builder requestPermissionGroup(String[]... groups) {
            List<String> permissionList = new ArrayList<>();
            for (String[] group : groups) {
                permissionList.addAll(Arrays.asList(group));
            }
            mPermissions = permissionList.toArray(new String[permissionList.size()]);
            return this;
        }

        public Builder setOnGrantedListener(OnGrantedListener onGranted) {
            mOnGrantedListener = onGranted;
            return this;
        }

        private void valid() {
            if (this.mContext == null)
                throw new RuntimeException("content can't be null");
        }

    }
}
