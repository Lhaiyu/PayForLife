package com.example.administrator.payforlife.util;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/30.
 */

public class OkhttpUtil {
    public static void sendOkHttpFormBodyRequest(String address, FormBody body, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).post(body).build();
        Log.d("LHY", "request");
        client.newCall(request).enqueue(callback);
        Log.d("LHY", "call");
    }

    public static void sendOKHttpRequestBodyRequest(String address,RequestBody body,Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).post(body).build();
        client.newCall(request).enqueue(callback);
    }
}
