package io.alcruz.allmovies.api;

import android.support.annotation.NonNull;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.alcruz.allmovies.api.models.MovieDetailReponse;
import io.alcruz.allmovies.api.models.MovieItemResponse;
import io.alcruz.allmovies.api.models.PagedResultResponse;
import io.alcruz.allmovies.common.MoviesDataSource;
import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class MoviesWebService implements MoviesDataSource {
    private final TheMovieDbService mMovieDbService;

    @Inject
    MoviesWebService(TheMovieDbService movieDbService) {
        mMovieDbService = movieDbService;
    }

    @Override
    public PagedList<MovieItem> getMovies(Integer page) {
        checkNotNull(page, "page cannot be null");
        try {
            Map<String, Object> params = ImmutableMap.<String, Object>builder()
                    .put("sort_by", "popularity.desc")
                    .put("include_adult", "false")
                    .put("include_video", "false")
                    .put("page", page)
                    .put("language", "en-US")
                    .build();

            Response<PagedResultResponse<MovieItemResponse>> response =
                    mMovieDbService.getMovies(params).execute();

            PagedResultResponse<MovieItemResponse> pagedResult = response.body();

            return PagedList.<MovieItem>builder()
                    .page(pagedResult.page)
                    .totalResults(pagedResult.totalResults)
                    .totalPages(pagedResult.totalPages)
                    .results(Lists.transform(Lists.newArrayList(pagedResult.results), new Function<MovieItemResponse, MovieItem>() {
                        @Override
                        public MovieItem apply(@NonNull MovieItemResponse input) {
                            return new MovieItem(input.id, input.title, input.posterPath);
                        }
                    }))
                    .build();

        } catch (IOException e) {
            return PagedList.empty();
        }
    }

    @Override
    public MovieDetail getMovie(int movieId) {
        try {
            Response<MovieDetailReponse> response = mMovieDbService.getMovie(movieId).execute();
            return response.body().toModel();
        } catch (IOException ex) {
            return null;
        }
    }
}
