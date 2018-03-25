package com.easywork.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.easywork.fragment.FragmentFactory;

import java.util.List;

/**
 * Created by mac on 2018/3/25.
 */

public class MainAdapter extends FragmentPagerAdapter {


    private final List<String> titles;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        titles = FragmentFactory.getInstance().getTitles();
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance().getFragment(titles.get(position));
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
       return titles.get(position);
    }
}
