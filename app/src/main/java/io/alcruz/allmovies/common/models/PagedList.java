package io.alcruz.allmovies.common.models;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Lists;

import java.util.List;

@AutoValue
public abstract class PagedList<Result> {
    public static <T> Builder<T> builder() {
        return new AutoValue_PagedList.Builder<T>();
    }

    public static <T> PagedList<T> empty() {
        return new AutoValue_PagedList.Builder<T>()
                .page(0)
                .totalPages(0)
                .totalResults(0)
                .results(Lists.<T>newArrayList())
                .build();
    }

    public abstract Integer getPage();

    public abstract Integer getTotalPages();

    public abstract Integer getTotalResults();

    public abstract List<Result> getResults();

    @AutoValue.Builder
    public abstract static class Builder<Result> {
        public abstract Builder<Result> page(Integer page);

        public abstract Builder<Result> totalPages(Integer totalPages);

        public abstract Builder<Result> totalResults(Integer totalResults);

        public abstract Builder<Result> results(List<Result> results);

        public abstract PagedList<Result> build();
    }
}
