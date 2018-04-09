package com.easywork;

import android.app.Application;
import android.app.Service;
import android.content.Intent;

/**
 * Created by Wisn on 2018/4/8 下午4:36.
 */

public class App extends Application {
    public static App app;
    public static App getInstance(){
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        startService(new Intent(this, ServiceAAA.class));
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(true){
                                    Thread.sleep(10000);
                                    System.out.println("aaaaaa");
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }).start();*/
    }
}
