package io.alcruz.allmovies.movies.di;

import dagger.Binds;
import dagger.Module;
import io.alcruz.allmovies.movies.discover.MoviesContract;
import io.alcruz.allmovies.movies.discover.MoviesPresenter;

@Module
abstract class MoviesModule {
    @Binds
    abstract MoviesContract.Presenter bindPresenter(MoviesPresenter presenter);
}
