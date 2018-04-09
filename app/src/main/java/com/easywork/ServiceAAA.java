package com.easywork;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Wisn on 2018/4/8 下午4:50.
 */

public class ServiceAAA extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启用前台服务，主要是startForeground()
        Notification notification = new Notification(R.drawable.icon_disconnect, "用电脑时间过长了！白痴！"
                , System.currentTimeMillis());
        //设置通知默认效果
        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        startForeground(1, notification);

        return super.onStartCommand(intent, flags, startId);
    }
}
