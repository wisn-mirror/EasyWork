package com.library.config;

import android.content.Context;

/**
 * Created by Wisn on 2018/4/2 下午2:14.
 */

public class LibConfig {
    public static boolean isDebug = true;
    public static String HttpLogTAG = "";

    public static String baseUrl = "";
    //网络请求的域名
    public static String URL_DOMAIN = "https://api.douban.com/";
    //网络缓存地址
    public static String URL_CACHE;
    //设置Context
    public static Context CONTEXT;
    //设置OkHttp的缓存机制的最大缓存时间,默认为一天
    public static long MAX_CACHE_SECONDS = 60 * 60 * 24;
    //缓存最大的内存,默认为10M
    public static long MAX_MEMORY_SIZE = 10 * 1024 * 1024;
    //设置网络请求json通用解析类
    public static Class MClASS;


}
