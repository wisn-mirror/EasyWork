package com.library.db;

import com.library.db.impl.DataManagerImpl;
import com.library.network.RequestBuilder;
import com.library.network.RequestManager;
import com.library.utils.SharePreferenceUtils;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Wisn on 2018/4/20 下午2:31.
 */

public class DataManager extends DataManagerImpl{


    private DataManagerImpl dataManagerStub;
    private static DataManager dataManager;


    public enum DataType {
        RETROFIT, REALM, SHAREPREFERENCE
    }

    private DataManager() {

    }

    public static DataManager getInstance(DataType type) {
        if (dataManager == null) {
            synchronized (SharePreferenceUtils.class) {
                if (dataManager == null) {
                    dataManager = new DataManager();
                }
            }
        }

        dataManager.getManagerStub(type);

        return dataManager;
    }

    private DataManagerImpl getManagerStub(DataType type) {
        switch (type) {
            case RETROFIT:
                dataManagerStub = new RequestManager();
                break;
            case REALM:
//                dataManagerStub = PlutoFileCache.getInstance();
                break;
            case SHAREPREFERENCE:
                dataManagerStub = SharePreferenceUtils.getInstance();
                break;
            default:
        }

        return dataManagerStub;
    }

    /**
     * SharePreferenceUtils查询
     *
     * @param name  SharePreferenceUtils的保存文件
     * @param key
     * @param claZZ
     * @param <T>
     * @return
     */
    @Override
    public <T> T queryByNameAndKey(String name, String key, Class<T> claZZ) {
        return dataManagerStub.queryByNameAndKey(name, key, claZZ);
    }

    @Override
    public <T> T queryByKey(String key, Class<T> clazz) {
        return dataManagerStub.queryByKey(key, clazz);
    }

    @Override
    public <T> DisposableObserver<ResponseBody> httpRequest(RequestBuilder<T> requestBuilder) {
        return dataManagerStub.httpRequest(requestBuilder);
    }
}
