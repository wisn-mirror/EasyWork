package com.laiyifen.library.rx;

import android.text.TextUtils;

import com.laiyifen.library.mvp.BaseView;
import com.laiyifen.library.network.NetWorkCodeException;

/**
 * Created by Wisn on 2018/4/2 下午5:02.
 */

public abstract class RxObservableListener<T> implements ObservableListener<T> {

    private BaseView mView;
    private String mErrorMsg;

    protected RxObservableListener(BaseView view){
        this.mView = view;
    }

    protected RxObservableListener(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onError(NetWorkCodeException.ResponseThrowable e) {
        if (mView == null) {
            return;
        } if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.onError(mErrorMsg);
        }else {
            mView.onError(e.message);
        }
    }

}
