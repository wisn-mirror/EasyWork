package com.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.library.mvp.BaseModel;
import com.library.mvp.BasePresenter;
import com.library.utils.ObjectGetByClassUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wisn on 2018/4/2 下午1:37.
 */

public abstract class ABaseActivity<T extends BaseModel, E extends BasePresenter> extends AppCompatActivity {
    public T mModel;
    public E mPresenter;
    private boolean isFrist = true;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mModel = ObjectGetByClassUtils.getClass(this, 0);
        mPresenter = ObjectGetByClassUtils.getClass(this, 1);
        if (mModel != null && mPresenter != null) {
            mPresenter.setMV(mModel, this);
        }
        initView(this);
    }

    public abstract int getLayoutId();

    public abstract void initView(Activity activity);

    public abstract void initData(Context context);


    @Override
    protected void onStart() {
        super.onStart();
        if (isFrist) {
            initData(this);
            isFrist = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        if (bind != null) bind.unbind();
    }
}
