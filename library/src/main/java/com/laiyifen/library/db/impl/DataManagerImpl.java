package com.laiyifen.library.db.impl;

import com.laiyifen.library.db.helper.DBHelper;
import com.laiyifen.library.db.helper.HttpHelper;
import com.laiyifen.library.db.helper.SharePreferenceHelper;
import com.laiyifen.library.network.RequestBuilder;

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


}
