package io.alcruz.allmovies.movies.discover;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.alcruz.allmovies.common.UseCaseHandler;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.common.models.PagedList;
import io.alcruz.allmovies.movies.domain.usecases.GetMovies;

import static com.google.common.base.Preconditions.checkNotNull;

public class MoviesPresenter implements MoviesContract.Presenter {
    private final GetMovies mGetMovies;
    private final EventBus mEventBus;
    private MoviesContract.View mView;
    private UseCaseHandler mUseCaseHandler;
    private PagedList<MovieItem> mLastResult;

    @Inject
    public MoviesPresenter(@NonNull GetMovies getMovies, @NonNull UseCaseHandler useCaseHandler, @NonNull EventBus eventBus) {
        mGetMovies = checkNotNull(getMovies, "getMovies cannot be null");
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mEventBus = checkNotNull(eventBus, "EventBus cannot be null");
    }

    @Override
    public void start(@NonNull MoviesContract.View view) {
        mEventBus.register(this);
        mView = checkNotNull(view, "view cannot be null");
        loadMovies(true);
    }

    @Override
    public void loadMoreMovies() {
        if (mLastResult == null) {
            loadMovies(false);
            return;
        }

        if (mLastResult.getTotalPages() - mLastResult.getPage() == 0) {
            mView.setLoadingMoreIndicator(false);
            return;
        }

        mUseCaseHandler.execute(mGetMovies, GetMovies.RequestValues.forPage(mLastResult.getPage() + 1));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onGetMoviesSuccess(GetMovies.ResponseValue response) {
        mLastResult = response.getAllMovies();

        if (mView.isLoadingIndicatorShown()) {
            mView.setLoadingIndicator(false);
        }

        if (mView.isLoadingMoreIndicatorShown()) {
            mView.setLoadingMoreIndicator(false);
        }

        processTasks(mLastResult);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onGetMoviesError() {
        mView.showLoadingTasksError();
    }

    private void loadMovies(boolean showLoadingIndicator) {
        if (showLoadingIndicator) {
            mView.setLoadingIndicator(true);
        }
        mUseCaseHandler.execute(mGetMovies, GetMovies.RequestValues.firstPage());
    }

    private void processTasks(PagedList<MovieItem> movies) {
        mView.showMovies(movies.getResults());
    }

    public void stop() {
        mEventBus.unregister(this);
    }
}
