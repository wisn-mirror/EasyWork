package com.easywork.dialog;

import android.widget.Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easywork.R;

import java.util.List;

import top250Movies.bean.Movies;

/**
 * Created by Wisn on 2018/4/16 上午11:43.
 */

public class DialogAdapter2 extends BaseMultiItemQuickAdapter<Movies.SubjectsBean,BaseViewHolder>/*implements  BaseQuickAdapter.OnItemClickListener , BaseQuickAdapter.OnItemChildClickListener*/{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DialogAdapter2(List<Movies.SubjectsBean> data) {
        super(data);
        addItemType(Movies.SubjectsBean.BadMovie, R.layout.topmovies_badmovies);

    }
   /* public DialogAdapter(int layoutResId, @Nullable List<Movies.SubjectsBean> data) {
        super(layoutResId, data);
    }*/

    @Override
    protected void convert(BaseViewHolder helper, Movies.SubjectsBean item) {
            helper.addOnClickListener(R.id.ll_root_shangjia);
            helper.addOnClickListener(R.id.tv_shangjia_name);
//            helper.addOnClickListener(R.id.ll_root_shangjia_text);
//            helper.addOnClickListener(R.id.img_seleect_all);
    }
/*
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        LogUtils.d(position+" aaa");
        ToastUtils.show(mContext,"aaaaaa");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        LogUtils.d(position+" bbb");

        ToastUtils.show(mContext,"bbb");
    }*/
}
