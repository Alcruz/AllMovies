package io.alcruz.allmovies.movies.domain.usecases;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.alcruz.allmovies.common.MoviesDataSource;
import io.alcruz.allmovies.common.UseCase;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetMovies extends UseCase<GetMovies.RequestValues, GetMovies.ResponseValue> {
    private final MoviesDataSource mMoviesDataSource;
    private final EventBus mEventBus;

    @Inject
    public GetMovies(@NonNull MoviesDataSource moviesRepository, EventBus eventBus) {
        mMoviesDataSource = checkNotNull(moviesRepository, "moviesRepository cannot be null!");
        mEventBus = eventBus;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mEventBus.post(ResponseValue.create(mMoviesDataSource.getMovies(requestValues.getPage())));
    }

    @AutoValue
    public static abstract class RequestValues implements UseCase.RequestValues {
        public static GetMovies.RequestValues firstPage() {
            return forPage(1);
        }

        public static GetMovies.RequestValues forPage(Integer page) {
            return new AutoValue_GetMovies_RequestValues(page);
        }

        public abstract Integer getPage();
    }

    @AutoValue
    public static abstract class ResponseValue implements UseCase.ResponseValue {
        public static GetMovies.ResponseValue create(PagedList<MovieItem> movies) {
            return new AutoValue_GetMovies_ResponseValue(movies);
        }

        public abstract PagedList<MovieItem> getAllMovies();
    }
}
