package io.alcruz.allmovies.movies.detail;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import io.alcruz.allmovies.movies.di.MovieFragmentSubcomponent;

@Module(subcomponents = {MovieFragmentSubcomponent.class})
public abstract class MovieFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MovieFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMovieFragment(MovieFragmentSubcomponent.Builder builder);
}
