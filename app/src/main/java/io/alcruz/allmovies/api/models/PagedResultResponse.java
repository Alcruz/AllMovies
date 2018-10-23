package io.alcruz.allmovies.api.models;

import com.google.gson.annotations.SerializedName;

public class PagedResultResponse<R> {
    @SerializedName("id")
    public Integer id;

    @SerializedName("page")
    public Integer page;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;

    @SerializedName("results")
    public R[] results;
}
