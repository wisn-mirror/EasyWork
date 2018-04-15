package com.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Wisn on 2018/4/15 下午8:14.
 */

public class ToastUtils {
    public static void show(Context context, String mes){
        Toast.makeText(context,mes,Toast.LENGTH_SHORT).show();
    }
}
