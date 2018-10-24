package io.alcruz.allmovies.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.alcruz.allmovies.data.MovieDatabase;

@Module
class DatabaseModule {
    @Singleton
    @Provides
    static MovieDatabase providesMoviesDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                MovieDatabase.class, "Movies.db")
                .build();
    }
}
