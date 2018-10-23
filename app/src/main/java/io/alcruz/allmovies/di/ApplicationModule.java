package io.alcruz.allmovies.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import io.alcruz.allmovies.UseCaseThreadPoolScheduler;
import io.alcruz.allmovies.common.UseCaseScheduler;

@Module
abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);

    @Binds
    abstract UseCaseScheduler bindUseCaseScheduler(UseCaseThreadPoolScheduler useCaseScheduler);
}