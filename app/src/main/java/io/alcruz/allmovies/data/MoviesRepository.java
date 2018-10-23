package io.alcruz.allmovies.data;

import android.support.annotation.NonNull;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

import io.alcruz.allmovies.common.FavoriteMoviesDataSource;
import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;
import io.alcruz.allmovies.data.models.Movie;

import static com.google.common.base.Preconditions.checkNotNull;

public class MoviesRepository implements FavoriteMoviesDataSource {
    private static final int PAGE_SIZE = 30;
    private final MovieDatabase mMovieDatabase;

    @Inject
    public MoviesRepository(MovieDatabase movieDatabase) {
        mMovieDatabase = movieDatabase;
    }

    @Override
    public void addMovieToFavorite(MovieDetail movieDetail) {
        checkNotNull(movieDetail, "movieDetail cannot be null");
        Movie movie = createMovie(movieDetail);
        mMovieDatabase.taskDao().insert(movie);
    }

    @Override
    public void removeMovieFromFavorite(MovieDetail movieDetail) {
        checkNotNull(movieDetail, "movieDetail cannot be null");
        Movie movie = createMovie(movieDetail);
        mMovieDatabase.taskDao().delete(movie);
    }

    @Override
    public PagedList<MovieItem> getMovies(Integer page) {
        checkNotNull(page, "page cannot be null");

        List<Movie> movies = mMovieDatabase.taskDao().getMovies(page, PAGE_SIZE);
        int totalMovies = mMovieDatabase.taskDao().totalMovies();

        return PagedList.<MovieItem>builder()
                .page(page)
                .totalResults(totalMovies)
                .totalPages((int) Math.ceil((1.0 * totalMovies) / (1.0 * PAGE_SIZE)))
                .results(Lists.transform(movies, new Function<Movie, MovieItem>() {
                    @Override
                    public MovieItem apply(@NonNull Movie input) {
                        MovieItem movieItem = new MovieItem(input.getId(), input.getTitle(), input.getPosterPath());
                        movieItem.setFavorite(true);
                        return movieItem;
                    }
                }))
                .build();
    }

    @Override
    public MovieDetail getMovie(int movieId) {
        Movie movie = mMovieDatabase.taskDao().getMovies(movieId);
        if (movie != null) {
            MovieDetail movieDetail = new MovieDetail(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getOverview(),
                    movie.getPosterPath(),
                    movie.getBackdropPath(),
                    movie.getPopularity()
            );
            movieDetail.setFavorite(true);
            return movieDetail;
        }
        return null;
    }

    @NonNull
    private Movie createMovie(MovieDetail movieDetail) {
        return new Movie(
                movieDetail.getId(),
                movieDetail.getTitle(),
                movieDetail.getOverview(),
                movieDetail.getPosterPath(),
                movieDetail.getBackdropPath(),
                movieDetail.getPopularity()
        );
    }
}
