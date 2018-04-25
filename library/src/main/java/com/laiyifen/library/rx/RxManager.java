package com.library.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Wisn on 2018/4/2 下午1:29.
 */

public class RxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addObserver(DisposableObserver observer) {
        if (observer != null) {
            compositeDisposable.add(observer);
        }
    }

    public void clear() {
        compositeDisposable.dispose();
    }
}
