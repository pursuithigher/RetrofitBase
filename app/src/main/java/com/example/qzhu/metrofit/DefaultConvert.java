package com.example.qzhu.metrofit;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Qzhu on 2017/8/28.
 */

public class DefaultConvert extends ParamConverter<String, Call>{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    RequestBody convertRequest(String request) {
        RequestBody body = RequestBody.create(JSON, request);
        return body;
    }
    @Override
    Call convertResponse(ResponseBody response) {
        return null;//response.string();
    }
}
