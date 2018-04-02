package com.easywork.fragment;



import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by mac on 2018/3/25.
 */

public class FragmentFactory {

    private HashMap<String, Fragment> hashMap;
    static FragmentFactory factory;
    ArrayList<String> titles=new ArrayList<>();
    public FragmentFactory() {
        hashMap = new HashMap<>();
        init();
    }

    public static FragmentFactory getInstance() {
        if (factory == null) {
            synchronized (FragmentFactory.class) {
                factory = new FragmentFactory();
            }
        }
        return factory;
    }
    public void init(){
        titles.add("Api");
    }

    public List<String> getTitles() {
        return titles;
    }


    public Fragment getFragment(String key) {
        Fragment fragment = hashMap.get(key);
        if (fragment == null) {
            fragment = newFragment(key);
        }
        return fragment;
    }

    private Fragment newFragment(String key) {
        Fragment  fragment=null;
        switch (key) {
            case "Api":
//                fragment=new ApiFragment();
                break;
        }
//        hashMap.put(key, new ApiFragment());
        return fragment;
    }

}
