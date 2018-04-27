package top250Movies.model;

import com.laiyifen.library.network.RetrofitManager;

import top250Movies.GetTop;
import top250Movies.api.ApiUrl;
import top250Movies.api.MoviesApi;
import top250Movies.bean.Movies;
import io.reactivex.Observable;

/**
 * Created by Wisn on 2018/4/6 下午9:43.
 */

public class MoviesModel implements GetTop.Model {
    @Override
    public Observable<Movies> getTopMovies (int start, int count) {
        return RetrofitManager.getApiService(MoviesApi.class).getMovies(ApiUrl.getMoviesTop,start,count);
    }
}
