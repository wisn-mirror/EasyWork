package com.laiyifen.library.refreshrv.refreshview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Wisn on 2018/4/2 下午4:09.
 */

public abstract class BaseLoadMoreView  extends LinearLayout {

    /***
     * 加载更多分为3个状态
     */
    //正在加载
    public final static int STATE_LOADING = 0;
    //加载完成
    public final static int STATE_COMPLETE = 1;
    //没有数据
    public final static int STATE_NODATA= 2;

    //初始化状态
    public int mState = STATE_COMPLETE;

    public View mContainer;


    public BaseLoadMoreView(Context context) {
        super(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lp);
        initView(context);
    }

    /**
     * 状态设置
     */
    public abstract void initView(Context context);

    /**
     * 状态设置
     */
    public abstract void setState(int state);

    /**
     * 获取状态
     */
    public int getState() {
        return mState;
    }

    /**
     * 销毁页面对象和动画，防止内存泄漏
     */
    public abstract void destroy();
}