package io.alcruz.allmovies.common;

import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;

public interface MoviesDataSource {
    PagedList<MovieItem> getMovies(Integer page);

    MovieDetail getMovie(int id);
}
