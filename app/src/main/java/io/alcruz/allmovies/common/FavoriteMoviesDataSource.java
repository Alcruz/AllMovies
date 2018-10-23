package io.alcruz.allmovies.common;

import io.alcruz.allmovies.common.models.MovieDetail;

public interface FavoriteMoviesDataSource extends MoviesDataSource {
    void addMovieToFavorite(MovieDetail movieDetail);

    void removeMovieFromFavorite(MovieDetail movieDetail);
}
