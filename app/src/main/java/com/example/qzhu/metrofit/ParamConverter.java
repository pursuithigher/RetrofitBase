package com.example.qzhu.metrofit;

import java.util.Objects;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Qzhu on 2017/8/28.
 */
public abstract class ParamConverter<I,O> {
    abstract RequestBody convertRequest(I request);
    abstract O convertResponse(ResponseBody response);
}
