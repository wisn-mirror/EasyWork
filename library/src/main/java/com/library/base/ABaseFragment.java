package com.library.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.mvp.BaseModel;
import com.library.mvp.BasePresenter;
import com.library.utils.ObjectGetByClassUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wisn on 2018/4/2 下午3:03.
 */

public abstract class ABaseFragment<T extends BaseModel, E extends BasePresenter> extends Fragment implements FragmentUserVisibleController.UserVisibleCallback {
    private boolean isInit; // 是否可以开始加载数据
    private boolean isCreated;
    public T mModel;
    public E mPresenter;
    private FragmentUserVisibleController userVisibleController;
    public View mainView;
    private Unbinder bind;

    public ABaseFragment() {
        userVisibleController=new FragmentUserVisibleController(this,this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated=true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(getLayoutId(),container,false);
        bind = ButterKnife.bind(mainView);
        mModel = ObjectGetByClassUtils.getClass(this, 0);
        mPresenter = ObjectGetByClassUtils.getClass(this, 1);
        if (mModel != null && mPresenter != null) {
            mPresenter.setMV(mModel, this);
        }
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInit = true;
        userVisibleController.activityCreated();
        init(mainView);
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleController.resume();
        if (getUserVisibleHint()) {
            if (isInit && !isCreated) {
                isInit = false;// 加载数据完成
                requestData();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleController.pause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null) mPresenter.onDestroy();
        if(bind!=null) bind.unbind();
    }

    public abstract void init(View view);

    public abstract int getLayoutId();

    public abstract void requestData();

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleController.setWaitingShowToUser(waitingShowToUser);

    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {

    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }
}
