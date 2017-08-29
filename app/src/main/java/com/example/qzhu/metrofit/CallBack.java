package com.example.qzhu.metrofit;



/**
 * Created by Qzhu on 2017/8/28.
 */

public interface CallBack<T> {
    void onResponse(CallImpler<T> call, T response);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    void onFailure(CallImpler<T> call, Throwable t);
}
