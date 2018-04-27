package com.easywork;

import android.app.Application;
import android.os.Environment;

import com.laiyifen.library.config.LibConfig;

/**
 * Created by Wisn on 2018/4/8 下午4:36.
 */

public class App extends Application {
    public static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        startService(new Intent(this, ServiceAAA.class));
        initConfig();
    }

    public void initConfig() {
        String STORAGE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AAA/";
        String URL_CACHE = STORAGE_DIR + "url/cache/";
        LibConfig.URL_CACHE = URL_CACHE;
        LibConfig.CONTEXT = this;
    }
}
