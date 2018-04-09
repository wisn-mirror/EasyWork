package com.easywork;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.easywork.adapter.FragmentAdapter;
import com.library.utils.LogUtils;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    FragmentAdapter mainAdapter;



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Activity activity) {
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        Button button=findViewById(R.id.button);
        mainAdapter=new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        for(int i=0;i< mainAdapter.getCount();i++){
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager,true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("生成");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapUtils.drawableBitMap(MainActivity.this,
                                "http://cdn.oudianyun.com/lyf/prod/back-cms/1522668473597_1020_5619.jpg",
                                "http://m.laiyifen.com/applyToTuike.html?shareCode=44a17b943e044cf29b875dd2e6c8c610");
                        String url = BitmapUtils.saveBitmapFile(MainActivity.this, bitmap);
                        LogUtils.d( "成功保存图片" + url);
                    }
                }).start();
            }
        });

    }

    @Override
    public void initData(Context context) {
//        showToast(" path:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        LogUtils.d(" path:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
//        showToast(" path:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        LogUtils.d(" path:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isForeground(this)){
            showToast("后台运行");
        }else{
            showToast("未后台运行");
        }
    }
}
