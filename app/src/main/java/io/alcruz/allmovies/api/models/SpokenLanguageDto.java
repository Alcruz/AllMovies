package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

class SpokenLanguageDto {
    @SerializedName("iso_639_1")
    public String iso6391;
    @SerializedName("name")
    public String name;
}
