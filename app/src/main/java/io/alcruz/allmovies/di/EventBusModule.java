package io.alcruz.allmovies.di;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class EventBusModule {
    @Singleton
    @Provides
    static EventBus providesEventBus() {
        return EventBus.getDefault();
    }
}
