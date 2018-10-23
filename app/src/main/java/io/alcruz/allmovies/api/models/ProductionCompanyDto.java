package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanyDto {
    @SerializedName("id")
    public Integer id;
    @SerializedName("logo_path")
    public String logoPath;
    @SerializedName("name")
    public String name;
    @SerializedName("origin_country")
    public String originCountry;
}
