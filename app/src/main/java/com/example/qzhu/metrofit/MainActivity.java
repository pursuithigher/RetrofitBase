package com.example.qzhu.metrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void testCustom(){
        CallExector exector = new CallExector.Builder()
                .setClient(new OkHttpClient())
                .setParamConverter(new DefaultConvert())
                .setCallAdapter(new DefaultCallAdapter())
                .build();
        MethodDeclear interf = exector.create(MethodDeclear.class);
        CallImpler<String> caller = interf.post("url","args");
        caller.enqueue(new CallBack<String>() {
            @Override
            public void onResponse(CallImpler<String> call, String response) {
            }

            @Override
            public void onFailure(CallImpler<String> call, Throwable t) {
            }
        });
    }
}
