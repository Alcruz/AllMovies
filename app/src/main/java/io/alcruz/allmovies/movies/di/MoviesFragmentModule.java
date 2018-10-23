package io.alcruz.allmovies.movies.di;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import io.alcruz.allmovies.movies.discover.MoviesFragment;

@Module(subcomponents = {MoviesFragmentSubcomponent.class})
public abstract class MoviesFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MoviesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMoviesFragment(MoviesFragmentSubcomponent.Builder builder);
}
