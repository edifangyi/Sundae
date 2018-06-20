package com.fangyi.module_android.demo_rxbinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxFormValidationActivity extends BaseToolbarActivity {

    private final static String key_code = "title";


    @BindView(R2.id.edit_username)
    EditText mEditUsername;
    @BindView(R2.id.edit_password)
    EditText mEditPassword;
    @BindView(R2.id.btn_login)
    Button mBtnLogin;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;


    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxFormValidationActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_form_validation;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mBtnLogin.setEnabled(false);

        /**
         * 注册登录等情况下，所有输入都合法再点亮登录按钮
         */
        Observable<CharSequence> userName = RxTextView.textChanges(mEditUsername).skip(1);
        Observable<CharSequence> password = RxTextView.textChanges(mEditPassword).skip(1);

        Observable.combineLatest(userName, password, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {

                boolean isUserNameEmpty = TextUtils.isEmpty(mEditUsername.getText());
                boolean isPasswordEmpty = TextUtils.isEmpty(mEditPassword.getText());

                return !isUserNameEmpty && !isPasswordEmpty;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

                mBtnLogin.setEnabled(aBoolean);

                TextView textView = new TextView(mContext);
                if (aBoolean) {
                    textView.setText(" 登录按钮 ==> 可以用了");
                } else {
                    textView.setText(" 登录按钮 ==> 关闭了呢");
                }

                mWhiteBoard.addView(textView);
            }
        });

        mBtnLogin.setOnClickListener(v -> {
            TextView textView = new TextView(mContext);

            if (TextUtils.isEmpty(mEditUsername.getText())) {
                textView.setText(" 登录 ==> 用户名不能为空！");
                mWhiteBoard.addView(textView);
                return;
            }

            if (TextUtils.isEmpty(mEditPassword.getText())) {
                textView.setText(" 登录 ==> 密码不能为空！");
                mWhiteBoard.addView(textView);
                return;
            }

            textView.setText(" 登录 ==> 成功");
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
