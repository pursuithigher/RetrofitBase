package com.example.qzhu.metrofit;


import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Qzhu on 2017/8/28.
 */

public class CallExector {
    private OkHttpClient client = new OkHttpClient();

    private ParamConverter paramConverter;
    private CallAdapter callAdapter;

    public CallExector(OkHttpClient client, ParamConverter paramConverter, CallAdapter callAdapter) {
        this.client = client;
        this.paramConverter = paramConverter;
        this.callAdapter = callAdapter;
    }

    public static final class Builder {
        private OkHttpClient client;
        private ParamConverter paramConverter;
        private CallAdapter callAdapter;
        public Builder setClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Builder setParamConverter(ParamConverter paramConverter) {
            this.paramConverter = paramConverter;
            return this;
        }

        public Builder setCallAdapter(CallAdapter callAdapter) {
            this.callAdapter = callAdapter;
            return this;
        }

        public CallExector build(){
            if(this.client == null)
                client = new OkHttpClient();
            if(this.paramConverter == null)
                paramConverter = new DefaultConvert();
            if(this.callAdapter == null)
                this.callAdapter = new DefaultCallAdapter();
            return new CallExector(client,paramConverter,callAdapter);
        }
    }

    public <T> T create(Class<T> service){
        if(!service.isInterface()) {
            throw new RuntimeException("service must be a interface");
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class<?>[] { service },methodHandler);
    }

    private InvocationHandler methodHandler = new InvocationHandler() {
        /**
         * 接口方法调用的具体实现
         * @param proxy 代理类
         * @param method 代理方法
         * @param args 方法参数
         * @return 方法的返回类型
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            return execteMethod(method,args);
        }
    };
    private Object execteMethod(Method method, Object[] args)throws IOException {
        //默认参数一为uri
        String uri = (String) args[0];
        //默认参数二为请求参数
//        String params = (String) args[1];
//        RequestBody body = RequestBody.create(JSON, params);
        RequestBody body = paramConverter.convertRequest(args[1]);
        return post(uri,body);
    }
    Object post(String uri,RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(uri)
                .post(body)
                .build();
        CallImpler callImpler = new CallImpler(client.newCall(request),paramConverter);//paramConverter.convertResponse(response);
        return callAdapter.adapt(callImpler);
    }

}
