package com.example.qzhu.metrofit;

/**
 * Created by Qzhu on 2017/8/28.
 */

public class DefaultCallAdapter<T> extends CallAdapter<CallImpler<T>,T>{
    @Override
    CallImpler<T> adapt(CallImpler<T> call) {
        return call;
    }
}
