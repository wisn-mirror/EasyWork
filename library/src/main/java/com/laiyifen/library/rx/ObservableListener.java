package com.laiyifen.library.rx;

import com.laiyifen.library.network.NetWorkCodeException;

/**
 * Created by Wisn on 2018/4/2 下午4:50.
 */

public interface ObservableListener<T> {
    void onNext(T result);
    void onComplete();
    void onError(NetWorkCodeException.ResponseThrowable  responseThrowable);
}
