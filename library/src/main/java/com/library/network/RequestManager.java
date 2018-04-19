package com.library.network;

import com.library.rx.RxObservableListener;
import com.library.rx.RxSchedulers;
import com.library.rx.RxSubscriber;
import com.library.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wisn on 2018/4/7 下午3:52.
 */

public class RequestManager {
    /**
     * 只通过网络返回数据
     */
    public static <T> DisposableObserver<T> loadOnlyNetWork(final Observable<T> netWorkObservable,
                                                            final RxObservableListener<T> rxObservableListener) {

        DisposableObserver<T> observer = netWorkObservable.compose(RxSchedulers.<T>io_main())
                .subscribeWith(new RxSubscriber<T>() {
                    @Override
                    public void _onNext(T t) {
                        LogUtils.d("_onNext");
                        rxObservableListener.onNext(t);
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        LogUtils.d("_onError"+e.getMessage());
                        rxObservableListener.onError(e);
                    }

                    @Override
                    public void _onComplete() {
                        LogUtils.d("_onComplete");
                        rxObservableListener.onComplete();
                    }
                });

        return observer;
    }

    public static Interceptor getInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365, TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();

                Request request = chain.request();
                if (!NetworkUtils.isNetworkConnected(Config.CONTEXT)) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetworkUtils.isNetworkConnected(Config.CONTEXT)) {
                    int maxAge = 0; // read from cache
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    long maxStale = Config.MAX_CACHE_SECONDS; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };

        return interceptor;
    }
}
