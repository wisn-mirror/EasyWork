package com.library.network;

import android.text.TextUtils;

import com.library.config.LibConfig;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wisn on 2018/4/2 下午2:12.
 */

public class RetrofitManager {
    private static Retrofit mRetrofit;
    private static Retrofit mNoCacheRetrofit;

    private static Retrofit getRetrofit() {

        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //如果不是在正式包，添加拦截 打印响应json
            if (LibConfig.isDebug) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
//                        LogUtils.info("RetrofitManager", "收到响应: " + message);
                    }
                });

                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
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
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
//                        LogUtils.info("RetrofitManager", "收到响应: " + message);
                    }
                });

                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
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
