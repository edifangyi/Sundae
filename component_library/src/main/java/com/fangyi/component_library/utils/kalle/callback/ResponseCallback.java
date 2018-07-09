/*
 * Copyright © 2018 Yan Zhenjie.
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

import com.yanzhenjie.kalle.simple.SimpleResponse;

/**
 * Created by YanZhenjie on 2018/3/26.
 */
public abstract class ResponseCallback<S> extends SimpleCallback<S> {

    public ResponseCallback(Context context) {
        super(context);
    }

    @Override
    public void onResponse(SimpleResponse<S, String> response) {
        if (response.isSucceed()) {
            onResponse(response.succeed());
        } else {
            int code = response.code();
            String message = response.failed();

            switch (code) {
                case 101:
                    break;
                case 102:
                    break;
                case 103:
                    break;
                case 104:
                    break;
                case 105:
                    break;
            }
            onError(code, message);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onCancel() {
        super.onCancel();
    }

    @Override
    public void onEnd() {
        super.onEnd();
    }

    @Override
    public void onException(Exception e) {
        super.onException(e);
    }

    protected abstract void onResponse(S response);

    protected abstract void onError(int code, String message);
}