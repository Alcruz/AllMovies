package io.alcruz.allmovies.movies.detail;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.alcruz.allmovies.common.UseCaseHandler;
import io.alcruz.allmovies.common.models.MovieDetail;
import io.alcruz.allmovies.movies.domain.usecases.AddMovieToFavorite;
import io.alcruz.allmovies.movies.domain.usecases.GetMovieDetail;
import io.alcruz.allmovies.movies.domain.usecases.RemoveMovieFromFavorite;


public class MoviePresenter implements MovieContract.Presenter {
    private final EventBus mEventBus;
    private final GetMovieDetail mGetMovieDetail;
    private final UseCaseHandler mUseCaseHandler;
    private final AddMovieToFavorite mAddMovieToFavorite;
    private final RemoveMovieFromFavorite mRemoveMovieFromFavorite;
    private MovieContract.View mView;
    private MovieDetail mCurrentMovie;

    @Inject
    public MoviePresenter(@NonNull EventBus eventBus,
                          @NonNull GetMovieDetail getMovieDetail,
                          @NonNull AddMovieToFavorite addMovieToFavorite,
                          @NonNull RemoveMovieFromFavorite removeMovieFromFavorite,
                          @NonNull UseCaseHandler useCaseHandler) {
        mEventBus = eventBus;
        mGetMovieDetail = getMovieDetail;
        mUseCaseHandler = useCaseHandler;
        mAddMovieToFavorite = addMovieToFavorite;
        mRemoveMovieFromFavorite = removeMovieFromFavorite;
    }

    public void start(MovieContract.View view) {
        mView = view;
        mEventBus.register(this);
    }

    public void stop() {
        mEventBus.unregister(this);
    }

    @Override
    public void loadMovie(int movieId) {
        mView.setLoadingIndicator(true);
        GetMovieDetail.RequestValues requestValues = GetMovieDetail.RequestValues.create(movieId);
        mUseCaseHandler.execute(mGetMovieDetail, requestValues);
    }

    @Override
    public void toggleFavorite() {
        if (!mCurrentMovie.isFavorite()) {
            mUseCaseHandler.execute(mAddMovieToFavorite, AddMovieToFavorite.RequestValues.create(mCurrentMovie));
        } else {
            mUseCaseHandler.execute(mRemoveMovieFromFavorite, RemoveMovieFromFavorite.RequestValues.create(mCurrentMovie));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccess(GetMovieDetail.ResponseValue responseValue) {
        mView.setLoadingIndicator(false);
        mCurrentMovie = responseValue.getMovie();
        mView.showMovie(responseValue.getMovie());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccess(AddMovieToFavorite.ResponseValue responseValue) {
        mView.isMovieFavorite(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccess(RemoveMovieFromFavorite.ResponseValue responseValue) {
        mView.isMovieFavorite(false);
    }
}
