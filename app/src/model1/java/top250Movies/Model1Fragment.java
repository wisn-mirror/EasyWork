package top250Movies;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.easywork.R;
import com.laiyifen.library.refreshrv.PullToRefreshRecyclerView;
import com.laiyifen.library.utils.LogUtils;

import base.BaseFragment;
import butterknife.BindView;
import top250Movies.bean.Movies;
import top250Movies.model.MoviesModel;
import top250Movies.presenter.MoviesPresenter;

/**
 * Created by Wisn on 2018/4/6 下午9:23.
 */

public class Model1Fragment extends BaseFragment<MoviesModel, MoviesPresenter> implements GetTop.View, PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener {
    @BindView(R.id.pulltorefreshRecycleView)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    public void init(View view) {
        moviesAdapter = new MoviesAdapter(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pullToRefreshRecyclerView = view.findViewById(R.id.pulltorefreshRecycleView);
        pullToRefreshRecyclerView.setLayoutManager(layoutManager);
        pullToRefreshRecyclerView.setAdapter(moviesAdapter);
        pullToRefreshRecyclerView.setPullRefreshEnabled(true);
        pullToRefreshRecyclerView.setLoadMoreEnabled(true);
        pullToRefreshRecyclerView.setRefreshAndLoadMoreListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.topmovies_fragment;
    }

    @Override
    public void requestData() {
        LogUtils.d("requestData");
        ((MoviesPresenter) mPresenter).getTopMovies(30);
    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void refreshView(Movies movies) {
        LogUtils.d("refreshView");
        if (movies != null && movies.subjects != null) {
            moviesAdapter.setNewData(movies.subjects);
        }
        pullToRefreshRecyclerView.refreshComplete();
    }

    @Override
    public void loadMore(Movies movies) {
        LogUtils.d("loadMore");
        if (movies != null && movies.subjects != null) {
            moviesAdapter.addData(movies.subjects);
        }
        pullToRefreshRecyclerView.loadMoreComplete();
    }

    @Override
    public void onRecyclerViewRefresh() {
        LogUtils.d("onRecyclerViewRefresh");

        ((MoviesPresenter) mPresenter).getTopMovies(30);
    }

    @Override
    public void onRecyclerViewLoadMore() {
        LogUtils.d("onRecyclerViewLoadMore");

        ((MoviesPresenter) mPresenter).loadMore(10);
    }
}
