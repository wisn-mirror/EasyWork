package top250Movies.api;

import top250Movies.bean.Movies;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Wisn on 2018/4/7 下午3:26.
 */

public interface MoviesApi {

    @GET
    Observable<Movies> getMovies(@Url String url , @Query("start")  int start, @Query("start") int count);
}
