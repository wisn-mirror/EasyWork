package com.easywork.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by mac on 2018/3/25.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("LogInterceptor", "request:" + request);
        Log.e("LogInterceptor", "header:" + request.headers());
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        Log.e("LogInterceptor", "count:" + (endTime - startTime));

        Response.Builder builder = response.newBuilder();
        Response clone = builder.build();
        MediaType mediaType = clone.body().contentType();
        String content = clone.body().string();
        Log.e("LogInterceptor", "content:" + content);
        return response;
    }
}
