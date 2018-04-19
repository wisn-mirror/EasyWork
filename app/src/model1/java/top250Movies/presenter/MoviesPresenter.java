package top250Movies.presenter;

import com.library.network.NetWorkCodeException;
import com.library.network.RequestManager;
import com.library.rx.RxManager;
import com.library.rx.RxObservableListener;

import top250Movies.GetTop;
import top250Movies.bean.Movies;

/**
 * Created by Wisn on 2018/4/6 下午9:49.
 */

public class MoviesPresenter extends GetTop.Presenter {
    private int start=0;
    @Override
    public void getTopMovies(final int count) {
        getRxManager().addObserver(RequestManager.loadOnlyNetWork(mModel.getTopMovies(start, count),
                new RxObservableListener<Movies>(mView) {
                    @Override
                    public void onNext(Movies result) {
                        start=count*1;
                        mView.refreshView(result);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onError(NetWorkCodeException.ResponseThrowable e) {
                        super.onError(e);
                    }
                }));
    }

    @Override
    public void loadMore(final int count) {
        getRxManager().addObserver(RequestManager.loadOnlyNetWork(mModel.getTopMovies(start, count),
                new RxObservableListener<Movies>(mView) {
                    @Override
                    public void onNext(Movies result) {
                        start=start+count;
                        mView.loadMore(result);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onError(NetWorkCodeException.ResponseThrowable e) {
                        super.onError(e);
                    }
                }));
    }
}
