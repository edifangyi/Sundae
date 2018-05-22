package com.fangyi.component_library.mvp;



/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/1/25
 * ================================================
 */
public interface IModel {

    interface Listener<Result> {
        void onResult(Result result);
        void onError(String mssage);
    }
}
