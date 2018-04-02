package com.easywork.fragment.api;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.easywork.R;
import com.easywork.enty.HomeApiBean;
import com.easywork.fragment.BaseFragment;
import com.easywork.network.NetWork;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac on 2018/3/25.
 * https://api.douban.com/v2/movie/top250?start=0&count=10
 */

public class ApiFragment extends BaseFragment {
    private static final String TAG="ApiFragment";
    @BindView(R.id.test)
     Button test;
    @BindView(R.id.textview)
    TextView textView;
    private boolean select=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api,container,false);
        ButterKnife.bind(this,view);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("wisn://lyf-app.com/recipe"));
                startActivity(intent);
//                WebView.loadUrl("artist://first/enter");

            }
        });
//        textView.setSelected();
        Drawable drawable =  getResources().getDrawable(R.drawable.select_image_text_drawable);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 3, drawable.getIntrinsicHeight() / 3);
        textView.setCompoundDrawables(drawable,null, null, null);
        textView.setCompoundDrawablePadding(20);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select=!select;
                textView.setSelected(select);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NetWork.getHomeApi()
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeApiBean>() {
                    @Override
                    public void accept(HomeApiBean s) throws Exception {
//                        test.setText(s);
                        Log.e(TAG,"result:"+s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,"error:"+throwable);

                    }
                });
    }
}