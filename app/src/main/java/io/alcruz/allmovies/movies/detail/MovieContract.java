package io.alcruz.allmovies.movies.detail;

import io.alcruz.allmovies.common.BasePresenter;
import io.alcruz.allmovies.common.BaseView;
import io.alcruz.allmovies.common.models.MovieDetail;

public class MovieContract {
    public interface View extends BaseView {
        void showMovie(MovieDetail movie);

        void setLoadingIndicator(boolean flag);

        void isMovieFavorite(boolean flag);
    }

    public interface Presenter extends BasePresenter<View> {
        void loadMovie(int movieId);

        void toggleFavorite();
    }
}
