package io.alcruz.allmovies.movies.di;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.alcruz.allmovies.common.MoviesDataSource;
import io.alcruz.allmovies.movies.detail.MovieFragment;

@Subcomponent(modules = MovieModule.class)
public interface MovieFragmentSubcomponent extends AndroidInjector<MovieFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieFragment> {
        @BindsInstance
        public abstract MovieFragmentSubcomponent.Builder dataSource(MoviesDataSource dataSource);
    }
}
