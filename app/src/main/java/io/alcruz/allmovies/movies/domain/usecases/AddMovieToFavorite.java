package io.alcruz.allmovies.movies.domain.usecases;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.alcruz.allmovies.common.FavoriteMoviesDataSource;
import io.alcruz.allmovies.common.UseCase;
import io.alcruz.allmovies.common.models.MovieDetail;

public class AddMovieToFavorite extends UseCase<AddMovieToFavorite.RequestValues, AddMovieToFavorite.ResponseValue> {
    private final FavoriteMoviesDataSource mMoviesDataSource;
    private final EventBus mEventBus;

    @Inject
    public AddMovieToFavorite(@NonNull FavoriteMoviesDataSource moviesDataSource, @NonNull EventBus eventBus) {
        mMoviesDataSource = moviesDataSource;
        mEventBus = eventBus;
    }

    @Override
    protected void executeUseCase(@NonNull RequestValues requestValues) {
        mMoviesDataSource.addMovieToFavorite(requestValues.getMovieDetail());
        mEventBus.post(new AutoValue_AddMovieToFavorite_ResponseValue());
    }

    @AutoValue
    public static abstract class RequestValues implements UseCase.RequestValues {
        public static RequestValues create(MovieDetail movieDetail) {
            return new AutoValue_AddMovieToFavorite_RequestValues(movieDetail);
        }

        abstract MovieDetail getMovieDetail();
    }

    @AutoValue
    public static abstract class ResponseValue implements UseCase.ResponseValue {
    }
}
