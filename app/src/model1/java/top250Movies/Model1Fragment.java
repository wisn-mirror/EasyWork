package top250Movies;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.easywork.R;
import com.library.refreshrv.PullToRefreshRecyclerView;

import base.BaseFragment;
import butterknife.BindView;
import top250Movies.bean.Movies;
import top250Movies.model.MoviesModel;
import top250Movies.presenter.MoviesPresenter;

/**
 * Created by Wisn on 2018/4/6 下午9:23.
 */

public class Model1Fragment extends BaseFragment<MoviesModel, MoviesPresenter> implements GetTop.View{
    @BindView(R.id.pulltorefreshRecycleView)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    public void init(View view) {
        moviesAdapter = new MoviesAdapter(null);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        pullToRefreshRecyclerView=view.findViewById(R.id.pulltorefreshRecycleView);
        pullToRefreshRecyclerView.setLayoutManager(layoutManager);
        pullToRefreshRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.topmovies_fragment;
    }

    @Override
    public void requestData() {
        ((MoviesPresenter)mPresenter).getTopMovies(0,200);
    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void refreshView(Movies movies) {
        if(movies!=null&&movies.subjects!=null){
            moviesAdapter.setNewData(movies.subjects);
        }
    }
}
