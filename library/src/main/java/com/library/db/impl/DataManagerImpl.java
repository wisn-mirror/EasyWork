package com.library.db.impl;

import com.library.db.helper.DBHelper;
import com.library.db.helper.HttpHelper;
import com.library.db.helper.SharePreferenceHelper;
import com.library.network.RequestBuilder;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Wisn on 2018/4/20 下午2:29.
 */

public  class DataManagerImpl  implements SharePreferenceHelper,HttpHelper,DBHelper {
    @Override
    public <T> T queryByNameAndKey(String name, String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T queryByKey(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> DisposableObserver<ResponseBody> httpRequest(RequestBuilder<T> requestBuilder) {
        return null;
    }
}
