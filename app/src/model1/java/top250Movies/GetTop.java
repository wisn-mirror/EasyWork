package top250Movies;

import com.laiyifen.library.mvp.BaseModel;
import com.laiyifen.library.mvp.BasePresenter;
import com.laiyifen.library.mvp.BaseView;

import top250Movies.bean.Movies;
import io.reactivex.Observable;

/**
 * Created by Wisn on 2018/4/6 下午9:31.
 */

public interface GetTop {
    interface Model extends BaseModel {
        Observable<Movies> getTopMovies(int start, int count);
    }

    interface View extends BaseView {
        void refreshView(Movies movies);
        void loadMore(Movies movies);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getTopMovies( int count);
        public abstract void loadMore( int count);
    }
}
