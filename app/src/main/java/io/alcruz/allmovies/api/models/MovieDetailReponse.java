package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.alcruz.allmovies.common.models.MovieDetail;

public class MovieDetailReponse {
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    public Object belongsToCollection;
    @SerializedName("budget")
    public Integer budget;
    @SerializedName("genres")
    public List<GenreDto> genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public Integer id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("production_companies")
    public List<ProductionCompanyDto> productionCompanies = null;
    @SerializedName("production_countries")
    public List<ProductionCompanyDto> productionCountries = null;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public Integer revenue;
    @SerializedName("runtime")
    public Integer runtime;
    @SerializedName("spoken_languages")
    public List<SpokenLanguageDto> spokenLanguages = null;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;

    public MovieDetail toModel() {
        return new MovieDetail(id, title, overview, posterPath, backdropPath, popularity);
    }
}
