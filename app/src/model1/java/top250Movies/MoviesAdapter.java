package top250Movies;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easywork.R;

import java.util.List;

import top250Movies.bean.Movies;

/**
 * Created by Wisn on 2018/4/7 下午3:45.
 */

public class MoviesAdapter extends BaseMultiItemQuickAdapter<Movies.SubjectsBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MoviesAdapter(List<Movies.SubjectsBean> data) {
        super(data);
        addItemType(Movies.SubjectsBean.BadMovie, R.layout.topmovies_badmovies);
        addItemType(Movies.SubjectsBean.NotBadMovie, R.layout.topmovies_notbadmovies);
        addItemType(Movies.SubjectsBean.GoodMovie, R.layout.topmovies_goodmovies);
    }

    @Override
    protected void convert(BaseViewHolder helper, Movies.SubjectsBean item) {

    }

}
