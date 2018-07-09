package com.fangyi.sundae.system.ui;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.fangyi.component_library.app.SundaeBaseActivity;
import com.fangyi.component_library.utils.glide.GlideApp;
import com.fangyi.component_library.utils.permission.PermissionUtils;
import com.fangyi.sundae.R;
import com.fangyi.sundae.system.mvp.contract.SplashContract;
import com.fangyi.sundae.system.mvp.model.SplashModel;
import com.fangyi.sundae.system.mvp.presenter.SplashPresenter;
import com.yanzhenjie.permission.Permission;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class SplashActivity extends SundaeBaseActivity<SplashPresenter, SplashModel> implements SplashContract.View {

    private ImageView mIvSplashPhoto;
    private LottieAnimationView mLottie;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


        if (!isTaskRoot()) {
            finish();
            return;
        }

        initView();

        PermissionUtils.newBuilder()
                .requestPermission(
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .setOnGrantedListener(() -> {

                    mLottie.playAnimation();
                    mLottie.addAnimatorListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            MainActivity.startAction((Activity) mContext, true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                }).builder(mContext);


        GlideApp.with(this).asGif().load(R.mipmap.splash_placeholder).into(mIvSplashPhoto);

    }


    private void initView() {
        mIvSplashPhoto = findViewById(R.id.iv_splash_photo);
        mLottie = findViewById(R.id.lottie);
    }
}
