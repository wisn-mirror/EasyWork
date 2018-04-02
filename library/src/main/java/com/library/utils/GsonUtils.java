package com.library.utils;

import com.google.gson.Gson;
import com.library.config.LibConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Wisn on 2018/4/2 下午6:16.
 */

public class GsonUtils {
    static Gson gson = new Gson();

    /**
     * 转化为object
     *
     * @param reader        json数据
     * @param tranformClass 要转化的Class
     * @param <T>
     *
     * @return
     */
    public static <T> T fromJsonObject(String reader, Class tranformClass) {
        Type type = new ParameterizedTypeImpl(LibConfig.MClASS, new Class[]{tranformClass});

        return gson.fromJson(reader, type);
    }

    /**
     * 转化为列表
     *
     * @param reader    json数据
     * @param listClass 要转化的Class
     * @param <T>
     *
     * @return
     */
    public static <T> T fromJsonArray(String reader, Class listClass) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{listClass});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(LibConfig.MClASS, new Type[]{listType});
        return gson.fromJson(reader, type);
    }

    public static <T> String getJson(T t) {
        return gson.toJson(t);
    }

    static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
