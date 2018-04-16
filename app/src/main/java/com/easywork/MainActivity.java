package com.easywork;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.easywork.adapter.FragmentAdapter;
import com.easywork.dialog.CustomViewAnyPositionDialog;
import com.easywork.dialog.CustomViewDialog;
import com.easywork.dialog.DialogAdapter;
import com.easywork.dialog.NormalFragmentDialog;
import com.library.utils.LogUtils;

import java.security.GeneralSecurityException;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import top250Movies.bean.Movies;

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
        DialogAdapter   moviesAdapter = new DialogAdapter(Movies.getData());
      RecyclerView recyclerView =findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        } );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("生成");
//                NormalFragmentDialog.getInstance().show(getFragmentManager(),"aaa");
//                CustomViewDialog.getInstance(true).show(getFragmentManager(),"bbb");
                CustomViewAnyPositionDialog.getInstance().show(getSupportFragmentManager(),"ccc");
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapUtils.drawableBitMap(MainActivity.this,
                                "http://cdn.oudianyun.com/lyf/prod/back-cms/1522668473597_1020_5619.jpg",
                                "http://m.laiyifen.com/applyToTuike.html?shareCode=44a17b943e044cf29b875dd2e6c8c610");
                        String url = BitmapUtils.saveBitmapFile(MainActivity.this, bitmap);
                        LogUtils.d( "成功保存图片" + url);
                    }
                }).start();*/
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
