package com.fangyi.module_android.demo_rxbinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangyi.component_library.base.BaseToolbarActivity;
import com.fangyi.module_android.R;
import com.fangyi.module_android.R2;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/6
 * 说    明：
 * ================================================
 */
public class RxCheckedChangesActivity extends BaseToolbarActivity {
    private final static String key_code = "title";

    @BindView(R2.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R2.id.white_board)
    LinearLayout mWhiteBoard;

    public static void startAction(Activity activity, boolean isFinish, String title) {
        Intent intent = new Intent(activity, RxCheckedChangesActivity.class);
        intent.putExtra(key_code, title);
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_checked_changes;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        /**
         * checkbox 选中就修改textview
         */
        RxCompoundButton.checkedChanges(mCheckbox)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        TextView textView = new TextView(mContext);

                        if (aBoolean) {
                            textView.setText("  ==> 按钮选中了");
                        } else {
                            textView.setText("  ==> 按钮未选中");
                        }
                        mWhiteBoard.addView(textView);
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
