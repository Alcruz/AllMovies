package io.alcruz.allmovies;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.alcruz.allmovies.di.DaggerAppComponent;
import io.alcruz.allmovies.di.NetModule;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                // TODO: THIS SHOULD COME FROM A SETTING FILE
                .netModule(new NetModule("https://api.themoviedb.org/3/", "dcad9abec1825c92ec958a6aecbfd762"))
                .build();
    }
}
