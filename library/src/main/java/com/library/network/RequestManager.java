package com.library.network;

import com.library.rx.RxObservableListener;
import com.library.rx.RxSchedulers;
import com.library.rx.RxSubscriber;
import com.library.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

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
                        e.printStackTrace();
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
}
