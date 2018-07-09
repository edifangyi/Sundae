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
package com.fangyi.component_library.utils.kalle;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * Created by YanZhenjie on 2018/3/26.
 */
public class BaseResponse implements Parcelable {

    @JSONField(name = "code")
    private int mCode;

    @JSONField(name = "message")
    private String mMessage;

    @JSONField(name = "data")
    private String mData;

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
        mCode = in.readInt();
        mMessage = in.readString();
        mData = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCode);
        dest.writeString(mMessage);
        dest.writeString(mData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }
}