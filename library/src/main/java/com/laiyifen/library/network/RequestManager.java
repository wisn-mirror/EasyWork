package com.library.network;

import com.library.config.LibConfig;
import com.library.db.impl.DataManagerImpl;
import com.library.rx.RxObservableListener;
import com.library.rx.RxSchedulers;
import com.library.rx.RxSubscriber;
import com.library.utils.LogUtils;
import com.library.utils.NetworkUtils;

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

public class RequestManager extends DataManagerImpl {
/*

    public <T> DisposableObserver<ResponseBody> request(RequestBuilder<T> builder) {

        if (builder.getReqType() == RequestBuilder.ReqType.NO_CACHE_MODEL) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadOnlyNetWorkModel(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.No_CACHE_LIST) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadOnlyNetWorkList(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DEFAULT_CACHE_MODEL) {
            Observable<ResponseBody> observable = getRetrofit(builder, true);
            return loadOnlyNetWorkModel(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DEFAULT_CACHE_LIST) {
            Observable<ResponseBody> observable = getRetrofit(builder, true);
            return loadOnlyNetWorkList(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_LIST_LIMIT_TIME) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadFormDiskResultListLimitTime(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_MODEL_LIMIT_TIME) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadFormDiskModeLimitTime(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_NO_NETWORK_LIST) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadNoNetWorkWithCacheResultList(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_NO_NETWORK_MODEL) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadNoNetWorkWithCacheModel(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_NETWORK_SAVE_RETURN_MODEL) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadOnlyNetWorkSaveModel(builder, observable);
        } else if (builder.getReqType() == RequestBuilder.ReqType.DISK_CACHE_NETWORK_SAVE_RETURN_LIST) {
            Observable<ResponseBody> observable = getRetrofit(builder, false);
            return loadOnlyNetWorkSaveList(builder, observable);
        }
        return null;
    }

    private <T> Observable<ResponseBody> getRetrofit(RequestBuilder<T> builder, boolean isCache) {
        if (builder.getHttpType() == RequestBuilder.HttpType.DEFAULT_GET) {
            if (isCache) {
                return RetrofitManager.getApiService(RequestService.class).getObservableWithQueryMap(builder.getUrl(), builder.getRequestParam());
            } else {
                return RetrofitManager.getNoCacheApiService(RequestService.class).getObservableWithQueryMap(builder.getUrl(), builder.getRequestParam());
            }
        } else if (builder.getHttpType() == RequestBuilder.HttpType.DEFAULT_POST) {
            if (isCache) {
                return RetrofitManager.getApiService(RequestService.class).getObservableWithQueryMap(builder.getUrl(), builder.getRequestParam());
            } else {
                return RetrofitManager.getNoCacheApiService(RequestService.class).getObservableWithQueryMap(builder.getUrl(), builder.getRequestParam());
            }
        } else if (builder.getHttpType() == RequestBuilder.HttpType.FIELDMAP_POST) {
            if (isCache) {
                return RetrofitManager.getApiService(RequestService.class).getObservableWithFieldMap(builder.getUrl(), builder.getRequestParam());
            } else {
                return RetrofitManager.getNoCacheApiService(RequestService.class).getObservableWithFieldMap(builder.getUrl(), builder.getRequestParam());
            }
        } else if (builder.getHttpType() == RequestBuilder.HttpType.ONE_MULTIPART_POST) {
            return RetrofitManager.getApiService(RequestService.class).getObservableWithImage(builder.getUrl(), builder.getRequestParam(), builder.getPart());
        }
        return null;
    }


    *//**
     * ============================自定义的缓存机制网络+本地json缓存===========================================
     *//*

    *//**
     * 设置缓存时间，没超过设置的时间不请求网络，只返回缓存数据
     * 返回List
     *//*
    public <T> DisposableObserver<ResponseBody> loadFormDiskResultListLimitTime(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {


        if (!FileUtils.isCacheDataFailure(builder.getFilePath() + "/" + builder.getFileName(), builder.getLimtHours())) {

            Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                    String json = FileUtils.ReadTxtFile(builder.getFilePath() + "/" + builder.getFileName());
                    T a = GsonUtils.fromJsonArray(json, builder.getTransformClass());
                    builder.getRxObservableListener().onNext(a);
                }
            }).subscribe(new Consumer<T>() {
                @Override
                public void accept(T t) throws Exception {

                }
            });

            return null;
        }

        final String[] json = new String[1];
        DisposableObserver<ResponseBody> observer = observable.compose(RxSchedulers.<ResponseBody>io_main())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        json[0] = responseBody.string();
                        FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
                    }
                })
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(final ResponseBody t) {
                        T a = GsonUtils.fromJsonArray(json[0], builder.getTransformClass());
                        builder.getRxObservableListener().onNext(a);
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        builder.getRxObservableListener().onError(e);
                    }

                    @Override
                    public void _onComplete() {
                        builder.getRxObservableListener().onComplete();
                    }
                });

        return observer;
    }

    *//**
     * 设置缓存时间，没超过设置的时间不请求网络，只返回缓存数据
     * 返回Model
     *//*
    public <T> DisposableObserver<ResponseBody> loadFormDiskModeLimitTime(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {

        if (!FileUtils.isCacheDataFailure(builder.getFilePath() + "/" + builder.getFileName(), builder.getLimtHours())) {

            Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                    String json = FileUtils.ReadTxtFile(builder.getFilePath() + "/" + builder.getFileName());
                    T a = GsonUtils.fromJsonObject(json, builder.getTransformClass());
                    builder.getRxObservableListener().onNext(a);
                }
            }).subscribe(new Consumer<T>() {
                @Override
                public void accept(T t) throws Exception {

                }
            });

            return null;
        }

        final String[] json = new String[1];
        DisposableObserver<ResponseBody> observer = observable.compose(RxSchedulers.<ResponseBody>io_main())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        json[0] = responseBody.string();
                        FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
                    }
                })
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(final ResponseBody t) {
                        T a = GsonUtils.fromJsonObject(json[0], builder.getTransformClass());
                        builder.getRxObservableListener().onNext(a);
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        builder.getRxObservableListener().onError(e);
                    }

                    @Override
                    public void _onComplete() {
                        builder.getRxObservableListener().onComplete();
                    }
                });

        return observer;
    }

    *//**
     * 没有网络再请求缓存
     * 返回List
     *//*
    private <T> DisposableObserver<ResponseBody> loadNoNetWorkWithCacheResultList(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {


        final Observable<T> observableC = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                String json = FileUtils.ReadTxtFile(builder.getFilePath() + "/" + builder.getFileName());
                if (!TextUtils.isEmpty(json) && !json.equals("")) {
                    T a = GsonUtils.fromJsonArray(json, builder.getTransformClass());
                    emitter.onNext(a);//返回数据不继续执行
                } else {
                    NetWorkCodeException.ResponseThrowable e = new NetWorkCodeException.ResponseThrowable();
                    e.code = NetWorkCodeException.NETWORD_ERROR;
                    e.message = "网络错误";
                    builder.getRxObservableListener().onError(e);
                }
            }
        });

        final String[] json = new String[1];
        DisposableObserver<ResponseBody> observer = observable
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        json[0] = responseBody.string();
                        FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
                    }
                })
                .compose(RxSchedulers.<ResponseBody>io_main())
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(ResponseBody t) {
                        T a = GsonUtils.fromJsonArray(json[0], builder.getTransformClass());
                        builder.getRxObservableListener().onNext(a);
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        observableC.subscribe(new Consumer<T>() {
                            @Override
                            public void accept(T t) throws Exception {
                                builder.getRxObservableListener().onNext(t);
                            }
                        });
                    }

                    @Override
                    public void _onComplete() {
                        builder.getRxObservableListener().onComplete();
                    }
                });

        return observer;
    }


    *//**
     * 没有网络再请求缓存
     * 返回Model
     *//*
    private <T> DisposableObserver<ResponseBody> loadNoNetWorkWithCacheModel(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {

        final Observable<T> observableC = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                String json = FileUtils.ReadTxtFile(builder.getFilePath() + "/" + builder.getFileName());
                if (!TextUtils.isEmpty(json) && !json.equals("")) {
                    T a = GsonUtils.fromJsonObject(json, builder.getTransformClass());
                    emitter.onNext(a);//返回数据不继续执行
                } else {
                    NetWorkCodeException.ResponseThrowable e = new NetWorkCodeException.ResponseThrowable();
                    e.code = NetWorkCodeException.NETWORD_ERROR;
                    e.message = "网络错误";
                    builder.getRxObservableListener().onError(e);
                }
            }
        });

        final String[] json = new String[1];

        DisposableObserver<ResponseBody> observer = observable.doOnNext(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody t) throws Exception {
                json[0] = t.string();
                FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
            }
        }).compose(RxSchedulers.<ResponseBody>io_main()).subscribeWith(new RxSubscriber<ResponseBody>() {
            @Override
            public void _onNext(ResponseBody t) {
                T a = GsonUtils.fromJsonObject(json[0], builder.getTransformClass());
                builder.getRxObservableListener().onNext(a);
            }

            @Override
            public void _onError(NetWorkCodeException.ResponseThrowable e) {
                observableC.subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        builder.getRxObservableListener().onNext(t);
                    }
                });
            }

            @Override
            public void _onComplete() {
                builder.getRxObservableListener().onComplete();
            }
        });

        return observer;
    }

    *//**
     * 把结果保存到本地，根据标志是否返回数据，如果本地存在则不需要下载
     *//*
    private <T> DisposableObserver<ResponseBody> loadOnlyNetWorkSaveList(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {

        if (FileUtils.checkFileExists(builder.getFilePath() + "/" + builder.getFileName())) { // 已经在SD卡中存在
            return null;
        }

        final String[] json = new String[1];
        DisposableObserver<ResponseBody> observer = observable.doOnNext(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody t) throws Exception {
                json[0] = t.string();
                FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
            }
        }).compose(RxSchedulers.<ResponseBody>io_main())
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(ResponseBody t) {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            T a = GsonUtils.fromJsonArray(json[0], builder.getTransformClass());
                            builder.getRxObservableListener().onNext(a);
                        }
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            builder.getRxObservableListener().onError(e);
                        }
                    }

                    @Override
                    public void _onComplete() {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            builder.getRxObservableListener().onComplete();
                        }
                    }
                });
        return observer;
    }


    *//**
     * 把结果保存到本地，根据标志是否返回数据，如果本地存在则不需要下载,返回Model
     *//*
    private <T> DisposableObserver<ResponseBody> loadOnlyNetWorkSaveModel(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {

        if (FileUtils.checkFileExists(builder.getFilePath() + "/" + builder.getFileName())) { // 已经在SD卡中存在
            return null;
        }

        final String[] json = new String[1];
        DisposableObserver<ResponseBody> observer = observable.doOnNext(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody t) throws Exception {
                json[0] = t.string();
                FileUtils.WriterTxtFile(builder.getFilePath(), builder.getFileName(), json[0], false);
            }
        }).compose(RxSchedulers.<ResponseBody>io_main())
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(ResponseBody t) {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            T a = GsonUtils.fromJsonObject(json[0], builder.getTransformClass());
                            builder.getRxObservableListener().onNext(a);
                        }
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            builder.getRxObservableListener().onError(e);
                        }
                    }

                    @Override
                    public void _onComplete() {
                        if (builder.isDiskCacheNetworkSaveReturn() == true) {
                            builder.getRxObservableListener().onComplete();
                        }
                    }
                });
        return observer;
    }

    *//**
     * 只通过网络返回数据，返回list
     *//*
    private <T> DisposableObserver<ResponseBody> loadOnlyNetWorkList(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {
        DisposableObserver<ResponseBody> observer = observable.compose(RxSchedulers.<ResponseBody>io_main())
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(ResponseBody t) {
                        try {
                            T a = GsonUtils.fromJsonArray(t.string(), builder.getTransformClass());
                            builder.getRxObservableListener().onNext(a);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        builder.getRxObservableListener().onError(e);
                    }

                    @Override
                    public void _onComplete() {
                        builder.getRxObservableListener().onComplete();
                    }
                });
        return observer;
    }


    *//**
     * 只通过网络返回数据，返回Model
     *//*
    private <T> DisposableObserver<ResponseBody> loadOnlyNetWorkModel(final RequestBuilder<T> builder, Observable<ResponseBody> observable) {
        DisposableObserver<ResponseBody> observer = observable.compose(RxSchedulers.<ResponseBody>io_main())
                .subscribeWith(new RxSubscriber<ResponseBody>() {
                    @Override
                    public void _onNext(ResponseBody t) {
                        try {
                            T a = GsonUtils.fromJsonObject(t.string(), builder.getTransformClass());
                            builder.getRxObservableListener().onNext(a);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void _onError(NetWorkCodeException.ResponseThrowable e) {
                        builder.getRxObservableListener().onError(e);
                    }

                    @Override
                    public void _onComplete() {
                        builder.getRxObservableListener().onComplete();
                    }
                });
        return observer;
    }*/

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
                        LogUtils.d("_onError" + e.getMessage());
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
                if (!NetworkUtils.isNetworkConnected(LibConfig.CONTEXT)) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetworkUtils.isNetworkConnected(LibConfig.CONTEXT)) {
                    int maxAge = 0; // read from cache
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    long maxStale = LibConfig.MAX_CACHE_SECONDS; // tolerate 4-weeks stale
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
