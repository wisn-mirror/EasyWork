package com.library.network;

import android.text.TextUtils;

import com.library.config.LibConfig;
import com.library.network.Interceptor.HttpLoggingInterceptor;

import java.io.File;
import java.util.logging.Level;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wisn on 2018/4/2 下午2:12.
 */

public class RetrofitManager {
    private static Retrofit mRetrofit;
    private static Retrofit mNoCacheRetrofit;

    public static void initHttpClient() {

    }

    private static Retrofit getRetrofit() {

        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //如果不是在正式包，添加拦截 打印响应json
            if (LibConfig.isDebug) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LibConfig.HttpLogTAG);
                httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
                httpLoggingInterceptor.setColorLevel(Level.INFO);
                builder.addInterceptor(httpLoggingInterceptor);
            }

            if (!TextUtils.isEmpty(LibConfig.baseUrl) && LibConfig.CONTEXT != null) {
                //设置缓存
                File httpCacheDirectory = new File(LibConfig.URL_CACHE);
                builder.cache(new Cache(httpCacheDirectory, LibConfig.MAX_MEMORY_SIZE));
//                builder.addInterceptor(RequestManager.getInterceptor());
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(LibConfig.URL_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return mRetrofit;
    }

    private static Retrofit getNoCacheRetrofit() {

        if (mNoCacheRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //如果不是在正式包，添加拦截 打印响应json
            if (LibConfig.isDebug) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LibConfig.HttpLogTAG);
                httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
                httpLoggingInterceptor.setColorLevel(Level.INFO);
                builder.addInterceptor(httpLoggingInterceptor);
            }

            OkHttpClient okHttpClient = builder.build();
            mNoCacheRetrofit = new Retrofit.Builder()
                    .baseUrl(LibConfig.URL_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return mNoCacheRetrofit;
    }

    public static <T> T getApi(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    public static <T> T getNoCacheApi(Class<T> clazz) {
        return getNoCacheRetrofit().create(clazz);
    }
}
