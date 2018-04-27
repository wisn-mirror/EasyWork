package com.laiyifen.library.network;

import android.text.TextUtils;

import com.laiyifen.library.config.LibConfig;
import com.laiyifen.library.https.HttpsUtils;
import com.laiyifen.library.network.Interceptor.HttpLoggingInterceptor;
import com.laiyifen.library.utils.LogUtils;

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


    public static Retrofit getRetrofit(RequestBuilder builder, boolean existCache) {
        if (builder != null && builder.okHttpClientBuilder != null) {
            OkHttpClient okHttpClient = getOkHttpClient(builder.okHttpClientBuilder, existCache);
            return buildRetrofit(okHttpClient, builder.baseUrl);
        }
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = getOkHttpClient(null, existCache);
            mRetrofit = buildRetrofit(okHttpClient, builder.baseUrl);
        }
        return mRetrofit;
    }

    private static Retrofit buildRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static OkHttpClient getOkHttpClient(OkHttpClient.Builder builder, boolean existCache) {
        if (builder == null) {
            builder = new OkHttpClient.Builder();
        }
        //如果不是在正式包，添加拦截 打印响应json
        if (LibConfig.isDebug) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LibConfig.HttpLogTAG);
            httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            httpLoggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        if (existCache && !TextUtils.isEmpty(LibConfig.baseUrl) && LibConfig.CONTEXT != null) {
            //设置缓存
            File httpCacheDirectory = new File(LibConfig.URL_CACHE);
            builder.cache(new Cache(httpCacheDirectory, LibConfig.MAX_MEMORY_SIZE));
            builder.addInterceptor(RequestManager.getInterceptor());
        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);
        return builder.build();
    }

    public static <T> T getApiService(Class<T> clazz, RequestBuilder requestBuilder) {
        LogUtils.d("getApiService" + clazz);
        mRetrofit = getRetrofit(requestBuilder, false);
        return mRetrofit.create(clazz);
    }

    /**
     * 接口定义类,获取到没有缓存的retrofit
     *
     * @param tClass
     * @param <T>
     *
     * @return
     */
    public static <T> T getNoCacheApiService(Class<T> tClass, RequestBuilder requestBuilder) {
        mNoCacheRetrofit = getRetrofit(requestBuilder, true);
        return mNoCacheRetrofit.create(tClass);
    }


}
