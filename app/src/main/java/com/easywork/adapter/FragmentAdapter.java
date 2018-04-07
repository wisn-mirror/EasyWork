package com.easywork.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.easywork.fragment.FragmentFactory;

import java.util.List;

/**
 * Created by Wisn on 2018/4/7 下午9:10.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private final List<String> titles;

    public FragmentAdapter(FragmentManager fm) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return titles.get(position);
    }
}
