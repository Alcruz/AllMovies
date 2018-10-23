package io.alcruz.allmovies.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.alcruz.allmovies.App;


@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivitiesModule.class, DatabaseModule.class, ApplicationModule.class, NetModule.class, EventBusModule.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent.Builder netModule(NetModule netModule);

        AppComponent build();
    }
}
