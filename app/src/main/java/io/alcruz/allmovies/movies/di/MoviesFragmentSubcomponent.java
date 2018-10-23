package io.alcruz.allmovies.movies.di;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.alcruz.allmovies.common.MoviesDataSource;
import io.alcruz.allmovies.movies.discover.MoviesFragment;

@Subcomponent(modules = {MoviesModule.class})
public interface MoviesFragmentSubcomponent extends AndroidInjector<MoviesFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MoviesFragment> {
        @BindsInstance
        public abstract MoviesFragmentSubcomponent.Builder dataSource(MoviesDataSource dataSource);
    }
}
