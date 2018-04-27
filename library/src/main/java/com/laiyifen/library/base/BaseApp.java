package com.laiyifen.library.base;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Wisn on 2018/4/19 上午11:03.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) { // TODO Auto-generated method stub
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }
}
