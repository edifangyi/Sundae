package com.fangyi.sundae.system.mvp.presenter;

import com.fangyi.component_library.config.UrlConfig;
import com.fangyi.component_library.http.DialogCallback;
import com.fangyi.sundae.system.bean.LoginBean;
import com.fangyi.sundae.system.mvp.contract.MainContract;
import com.socks.library.KLog;
import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.simple.SimpleResponse;


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
public class MainPresenter extends MainContract.Presenter {


    @Override
    public void doLogin(String username, String password) {

        Kalle.post(UrlConfig.LOGIN)
                .param("name", username)
                .param("password", password)
                .tag(this)
                .perform(new DialogCallback<LoginBean>(mActivity) {
                    @Override
                    public void onResponse(SimpleResponse<LoginBean, String> response) {
                        if (response.isSucceed()) {
                            mView.onLoginSucceed(response.succeed());
                        }
                    }
                });

    }
}