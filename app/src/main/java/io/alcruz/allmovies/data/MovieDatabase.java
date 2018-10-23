package io.alcruz.allmovies.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.alcruz.allmovies.data.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao taskDao();
}
