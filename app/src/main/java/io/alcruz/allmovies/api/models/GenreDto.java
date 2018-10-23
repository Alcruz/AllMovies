package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

public class GenreDto {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
}
