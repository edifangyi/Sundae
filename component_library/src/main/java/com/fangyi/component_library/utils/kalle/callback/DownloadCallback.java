/*
 * Copyright 2018 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fangyi.component_library.utils.kalle.callback;

import android.content.Context;

import com.fangyi.component_library.R;
import com.yanzhenjie.kalle.download.SimpleCallback;
import com.yanzhenjie.kalle.exception.ConnectTimeoutError;
import com.yanzhenjie.kalle.exception.HostError;
import com.yanzhenjie.kalle.exception.NetworkError;
import com.yanzhenjie.kalle.exception.ReadTimeoutError;
import com.yanzhenjie.kalle.exception.URLError;
import com.yanzhenjie.kalle.exception.WriteException;


/**
 * Created by YanZhenjie on 2018/3/28.
 */
public abstract class DownloadCallback extends SimpleCallback {

    private Context mContext;

    public DownloadCallback(Context context) {
        mContext = context;
    }

    @Override
    public final void onException(Exception e) {
        String message;
        if (e instanceof NetworkError) {
            message = mContext.getString(R.string.http_exception_network);
        } else if (e instanceof URLError) {
            message = mContext.getString(R.string.http_exception_url);
        } else if (e instanceof HostError) {
            message = mContext.getString(R.string.http_exception_host);
        } else if (e instanceof ConnectTimeoutError) {
            message = mContext.getString(R.string.http_exception_connect_timeout);
        } else if (e instanceof WriteException) {
            message = mContext.getString(R.string.http_exception_write);
        } else if (e instanceof ReadTimeoutError) {
            message = mContext.getString(R.string.http_exception_read_timeout);
        } else {
            message = mContext.getString(R.string.http_exception_unknow_error);
        }

        onException(message);
    }

    /**
     * Error message.
     */
    public abstract void onException(String message);

}