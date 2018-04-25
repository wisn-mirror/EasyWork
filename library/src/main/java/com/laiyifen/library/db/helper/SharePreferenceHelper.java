package com.library.db.helper;

/**
 * Created by Wisn on 2018/4/20 下午2:25.
 */

public interface SharePreferenceHelper {
    <T> T queryByNameAndKey(String name,String key,Class<T> clazz);
    <T> T queryByKey(String key,Class<T> clazz);
}
