package io.alcruz.allmovies.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.alcruz.allmovies.data.models.Movie;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM Movie LIMIT :pageSize OFFSET :page*:pageSize - :pageSize")
    List<Movie> getMovies(Integer page, Integer pageSize);

    @Query("SELECT * FROM Movie WHERE mId = :movieId LIMIT 1")
    Movie getMovies(int movieId);

    @Query("SELECT COUNT(*) FROM Movie")
    int totalMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
