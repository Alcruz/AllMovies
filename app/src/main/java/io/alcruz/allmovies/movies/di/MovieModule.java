package io.alcruz.allmovies.movies.di;

import dagger.Binds;
import dagger.Module;
import io.alcruz.allmovies.common.FavoriteMoviesDataSource;
import io.alcruz.allmovies.data.MoviesRepository;
import io.alcruz.allmovies.movies.detail.MovieContract;
import io.alcruz.allmovies.movies.detail.MoviePresenter;

@Module
abstract class MovieModule {
    @Binds
    abstract MovieContract.Presenter bindMoviePresenter(MoviePresenter presenter);

    @Binds
    abstract FavoriteMoviesDataSource bindFeatureMovieDataSource(MoviesRepository presenter);
}
