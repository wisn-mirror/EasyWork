package com.easywork.network.api;

import com.easywork.enty.HomeApiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mac on 2018/3/25.
 */

public interface HomeApi {
    @GET("/cms/page/getAppHomePage")
    Observable<HomeApiBean> getHomeData();
}
