package com.laiyifen.library.network;

import okhttp3.OkHttpClient;


public class RequestBuilder {

    public ReqType reqType = ReqType.DEFAULT_CACHE_LIST;
    public String baseUrl;
    //用于自定义请求设置，比如设置超时时间
    public OkHttpClient.Builder okHttpClientBuilder;

    public enum ReqType {
        //没有缓存
        NO_CACHE_MODEL,
        No_CACHE_LIST,
        //默认Retrofit缓存
        DEFAULT_CACHE_MODEL,
        DEFAULT_CACHE_LIST,
        //自定义磁盘缓存，返回List
        DISK_CACHE_LIST_LIMIT_TIME,
        //自定义磁盘缓存，返回Model
        DISK_CACHE_MODEL_LIMIT_TIME,
        //自定义磁盘缓存，没有网络返回磁盘缓存，返回List
        DISK_CACHE_NO_NETWORK_LIST,
        //自定义磁盘缓存，没有网络返回磁盘缓存，返回Model
        DISK_CACHE_NO_NETWORK_MODEL,
        //保存网络数据到本地磁盘，可以设定网络请求是否返回数据
        DISK_CACHE_NETWORK_SAVE_RETURN_MODEL,
        DISK_CACHE_NETWORK_SAVE_RETURN_LIST
    }


    public <T> T create(Class<T> service) {
        T apiService = RetrofitManager.getApiService(service, this);
        //todo
        return apiService;
    }
}
