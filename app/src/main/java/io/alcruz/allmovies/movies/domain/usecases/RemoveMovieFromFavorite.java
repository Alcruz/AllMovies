package io.alcruz.allmovies.movies.domain.usecases;

import com.google.auto.value.AutoValue;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.alcruz.allmovies.common.FavoriteMoviesDataSource;
import io.alcruz.allmovies.common.UseCase;
import io.alcruz.allmovies.common.models.MovieDetail;

public class RemoveMovieFromFavorite extends UseCase<RemoveMovieFromFavorite.RequestValues, RemoveMovieFromFavorite.ResponseValue> {
    private final FavoriteMoviesDataSource mMoviesDataSource;
    private final EventBus mEventBus;

    @Inject
    public RemoveMovieFromFavorite(EventBus eventBus, FavoriteMoviesDataSource moviesDataSource) {
        mMoviesDataSource = moviesDataSource;
        mEventBus = eventBus;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mMoviesDataSource.removeMovieFromFavorite(requestValues.getMovieDetail());
        mEventBus.post(new AutoValue_RemoveMovieFromFavorite_ResponseValue());
    }

    @AutoValue
    public static abstract class RequestValues implements UseCase.RequestValues {
        public static RemoveMovieFromFavorite.RequestValues create(MovieDetail movieDetail) {
            return new AutoValue_RemoveMovieFromFavorite_RequestValues(movieDetail);
        }

        abstract MovieDetail getMovieDetail();
    }

    @AutoValue
    public static abstract class ResponseValue implements UseCase.ResponseValue {
    }
}
