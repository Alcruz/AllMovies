package io.alcruz.allmovies.common.models;

public class MovieDetail {
    private String mOverview;
    private Integer mId;
    private String mTitle;

    private String mPosterPath;
    private String mBackdropPath;
    private Double mPopularity;
    private boolean mIsFavorite;

    public MovieDetail(Integer id, String title, String overview, String posterPath, String backdropPath, Double popularity) {
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

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }
}
