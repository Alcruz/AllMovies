package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

public class MovieItemResponse {
    @SerializedName("id")
    public Integer id;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("overview")
    public String overview;

    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("genre_ids")
    public Integer[] genreIds = null;

    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("title")
    public String title;
    @SerializedName("backdrop_path")
    public Object backdropPath;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("vote_count")
    public Integer voteCount;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Double voteAverage;
}
