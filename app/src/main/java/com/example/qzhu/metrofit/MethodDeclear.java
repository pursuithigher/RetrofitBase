package com.example.qzhu.metrofit;

/**
 * Created by Qzhu on 2017/8/28.
 */

public interface MethodDeclear {
    CallImpler<String> post(String url, String json);
    String login(String url, String json);
    String register(String url, String json);
    String logout(String url, String json);
}
