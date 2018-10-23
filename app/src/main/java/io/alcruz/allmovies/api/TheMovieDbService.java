package io.alcruz.allmovies.api;

import java.util.Map;

import io.alcruz.allmovies.api.models.MovieDetailReponse;
import io.alcruz.allmovies.api.models.MovieItemResponse;
import io.alcruz.allmovies.api.models.PagedResultResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TheMovieDbService {
    @GET("discover/movie")
    Call<PagedResultResponse<MovieItemResponse>> getMovies(@QueryMap Map<String, Object> queryParams);

    @GET("movie/{movieId}")
    Call<MovieDetailReponse> getMovie(@Path("movieId") Integer movieId);
}
