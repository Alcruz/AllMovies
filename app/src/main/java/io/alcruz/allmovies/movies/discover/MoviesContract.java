package io.alcruz.allmovies.movies.discover;

import java.util.List;

import io.alcruz.allmovies.common.BasePresenter;
import io.alcruz.allmovies.common.BaseView;
import io.alcruz.allmovies.common.models.MovieItem;

public class MoviesContract {
    interface View extends BaseView {
        boolean isLoadingIndicatorShown();

        void setLoadingIndicator(boolean flag);

        void showLoadingTasksError();

        void showMovies(List<MovieItem> movies);

        void setLoadingMoreIndicator(boolean flag);

        boolean isLoadingMoreIndicatorShown();
    }

    public interface Presenter extends BasePresenter<View> {
        void loadMoreMovies();
    }
}
