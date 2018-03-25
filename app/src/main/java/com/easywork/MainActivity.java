package com.easywork;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.easywork.adapter.MainAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout  tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainAdapter=new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        for(int i=0;i< mainAdapter.getCount();i++){
           tabLayout.addTab(tabLayout.newTab());
       }
       tabLayout.setupWithViewPager(viewPager,true);
    }
}
