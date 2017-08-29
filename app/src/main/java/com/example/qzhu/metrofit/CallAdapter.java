package com.example.qzhu.metrofit;

/**
 * Created by Qzhu on 2017/8/28.
 */

public abstract class CallAdapter<T,R> {
    abstract T adapt(CallImpler<R> call);
}
