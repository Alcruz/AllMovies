package io.alcruz.allmovies.movies.domain.usecases;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.alcruz.allmovies.common.UseCase;
import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.movies.discover.MoviesDataSource;

public class GetMovieDetail extends UseCase<GetMovieDetail.RequestValues, GetMovieDetail.ResponseValue> {
    private final MoviesDataSource mMoviesDataSource;
    private final EventBus mEventBus;

    @Inject
    public GetMovieDetail(@NonNull MoviesDataSource moviesDataSource, @NonNull EventBus eventBus) {
        mMoviesDataSource = moviesDataSource;
        mEventBus = eventBus;
    }

    @Override
    protected void executeUseCase(@NonNull RequestValues requestValues) {
        mEventBus.post(ResponseValue.create(mMoviesDataSource.getMovie(requestValues.getId())));
    }

    @AutoValue
    public static abstract class RequestValues implements UseCase.RequestValues {
        public static GetMovieDetail.RequestValues create(int id) {
            return new AutoValue_GetMovieDetail_RequestValues(id);
        }

        abstract int getId();
    }

    @AutoValue
    public static abstract class ResponseValue implements UseCase.ResponseValue {
        public static ResponseValue create(MovieDetail movie) {
            return new AutoValue_GetMovieDetail_ResponseValue(movie);
        }

        public abstract MovieDetail getMovie();
    }
}
