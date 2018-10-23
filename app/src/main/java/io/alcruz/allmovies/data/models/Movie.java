package io.alcruz.allmovies.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Movie {
    @PrimaryKey
    private Integer mId;
    private String mOverview;
    private String mTitle;

    private String mPosterPath;
    private String mBackdropPath;
    private Double mPopularity;

    public Movie(Integer id, String title, String overview, String posterPath, String backdropPath, Double popularity) {
        mId = id;
        mTitle = title;
        mOverview = overview;
        mPosterPath = posterPath;
        mBackdropPath = backdropPath;
        mPopularity = popularity;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public Double getPopularity() {
        return mPopularity;
    }
}
