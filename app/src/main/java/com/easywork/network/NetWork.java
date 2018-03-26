package com.easywork.network;

import com.easywork.interceptor.HttpLoggingInterceptor;
import com.easywork.interceptor.LogInterceptor;
import com.easywork.network.api.HomeApi;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 2018/3/25.
 */

public class NetWork {
    private static HomeApi  homeApi;
//    private static OkHttpClient.Builder  okHttpClient=new OkHttpClient.Builder().addNetworkInterceptor(new LogInterceptor());
    private static OkHttpClient.Builder  okHttpClient=null;
    private static Converter.Factory  gsonConverter= GsonConverterFactory.create();
    private static CallAdapter.Factory callAdapter= RxJava2CallAdapterFactory.create();
    private static Retrofit retrofit;

    public static HomeApi getHomeApi(){
        if(retrofit==null){
            initHttpClient();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl("http://api.laiyifen.com/")
                    .addConverterFactory(gsonConverter)
                    .addCallAdapterFactory(callAdapter)
                    .build();
        }
        if(homeApi==null){
            homeApi = retrofit.create(HomeApi.class);
        }
        return homeApi;
    }
    public static void initHttpClient(){
        HttpLoggingInterceptor  httpLoggingInterceptor=new HttpLoggingInterceptor("EasyWork");
        httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.setColorLevel(Level.INFO);
        okHttpClient=new OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor);
    }
}
