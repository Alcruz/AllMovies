package io.alcruz.allmovies.movies.discover;

import javax.inject.Inject;

import io.alcruz.allmovies.api.MoviesWebService;
import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;
import io.alcruz.allmovies.data.MoviesRepository;

public class MoviesDataSource implements io.alcruz.allmovies.common.MoviesDataSource {
    private final MoviesWebService mMoviesWebService;
    private final MoviesRepository mMoviesRepository;

    @Inject
    public MoviesDataSource(MoviesWebService moviesWebService, MoviesRepository moviesRepository) {
        mMoviesWebService = moviesWebService;
        mMoviesRepository = moviesRepository;
    }

    @Override
    public PagedList<MovieItem> getMovies(Integer page) {
        return mMoviesRepository.getMovies(page);
    }

    @Override
    public MovieDetail getMovie(int movieId) {
        MovieDetail movieDetail = mMoviesRepository.getMovie(movieId);
        if (movieDetail != null) {
            return movieDetail;
        }
        return mMoviesWebService.getMovie(movieId);
    }
}
