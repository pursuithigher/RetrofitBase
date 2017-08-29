package com.example.qzhu.metrofit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Qzhu on 2017/8/28.
 */
public class CallImpler<T>{
    Call call;
    private ParamConverter<Object,T> paramConverter;
    public CallImpler(Call call, ParamConverter<Object, T> paramConverter) {
        this.call = call;
        this.paramConverter = paramConverter;
    }
    Request request(){
        return call.request();
    }
    Response execute() throws IOException{
        return call.execute();
    }
    void cancel(){
        call.cancel();
    }
    boolean isExecuted(){
        return call.isExecuted();
    }
    boolean isCanceled(){
        return call.isCanceled();
    }

    public void enqueue(final CallBack<T> responseCallback){
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                responseCallback.onFailure(CallImpler.this,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseCallback.onResponse(CallImpler.this,paramConverter.convertResponse(response.body()));
            }
        });
    }
}
