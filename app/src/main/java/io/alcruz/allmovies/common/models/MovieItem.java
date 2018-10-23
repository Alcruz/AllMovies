package io.alcruz.allmovies.common.models;

public class MovieItem {
    private String mPosterPath;
    private Integer mId;
    private String mTitle;
    private boolean mIsFavorite;

    public MovieItem(Integer id, String title, String posterPath) {
        mId = id;
        mTitle = title;
        mPosterPath = posterPath;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }
}
