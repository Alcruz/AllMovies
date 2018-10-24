package io.alcruz.allmovies.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import io.alcruz.allmovies.UseCaseThreadPoolScheduler;
import io.alcruz.allmovies.common.UseCaseScheduler;

@Module
abstract class ApplicationModule {
    @Singleton
    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Binds
    abstract UseCaseScheduler bindUseCaseScheduler(UseCaseThreadPoolScheduler useCaseScheduler);
}