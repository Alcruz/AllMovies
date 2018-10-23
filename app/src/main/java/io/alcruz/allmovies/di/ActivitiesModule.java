package io.alcruz.allmovies.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.alcruz.allmovies.MainActivity;
import io.alcruz.allmovies.movies.detail.MovieActivity;
import io.alcruz.allmovies.movies.detail.MovieFragmentModule;
import io.alcruz.allmovies.movies.di.MoviesFragmentModule;

@Module
abstract class ActivitiesModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MoviesFragmentModule.class})
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MovieFragmentModule.class})
    abstract MovieActivity movieActivity();
}
