package io.alcruz.allmovies.movies.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.alcruz.allmovies.R;
import io.alcruz.allmovies.api.MoviesWebService;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.data.MoviesRepository;
import io.alcruz.allmovies.movies.di.MovieFragmentSubcomponent;
import io.alcruz.allmovies.utils.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class MovieActivity extends DaggerAppCompatActivity {
    public static final String MOVIE_ITEM_EXTRA = "MOVIE_ITEM_EXTRA";

    @Inject
    MovieFragmentSubcomponent.Builder mMovieFragmentBuilder;
    @Inject
    MoviesWebService moviesWebService;
    @Inject
    MoviesRepository moviesRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);

        MovieFragment fragment = new MovieFragment();
        MovieItemParcelableWrapper parcelable = getIntent().getParcelableExtra(MOVIE_ITEM_EXTRA);
        checkNotNull(parcelable, "INTENT MOVIE_ITEM_PARAM Cannot be null");
        MovieItem movieItem = parcelable.getMovie();

        if (movieItem.isFavorite()) {
            mMovieFragmentBuilder.dataSource(moviesRepository);
        } else {
            mMovieFragmentBuilder.dataSource(moviesWebService);
        }

        Bundle arguments = new Bundle();
        arguments.putInt(MovieFragment.MOVIE_ID_ARGUMENT, movieItem.getId());
        fragment.setArguments(arguments);

        mMovieFragmentBuilder.seedInstance(fragment);
        mMovieFragmentBuilder.build().inject(fragment);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
    }
}
