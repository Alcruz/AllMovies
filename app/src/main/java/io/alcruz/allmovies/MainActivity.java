package io.alcruz.allmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.alcruz.allmovies.api.MoviesWebService;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.data.MoviesRepository;
import io.alcruz.allmovies.movies.detail.MovieActivity;
import io.alcruz.allmovies.movies.detail.MovieItemParcelableWrapper;
import io.alcruz.allmovies.movies.di.MoviesFragmentSubcomponent;
import io.alcruz.allmovies.movies.discover.MoviesFragment;
import io.alcruz.allmovies.utils.ActivityUtils;
import io.alcruz.allmovies.utils.ItemSelectedEvent;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    MoviesWebService mMoviesWebService;
    @Inject
    MoviesRepository mMoviesRepository;
    @Inject
    MoviesFragmentSubcomponent.Builder mMoviesFragmentBuilder;

    @Inject
    EventBus mEventBus;
    private MoviesFragment mMoviesFragment;
    private MoviesFragment mFavoriteMoviesFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startHomeFragment();
                    return true;
                case R.id.navigation_favorite_movies:
                    startFavoriteFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            startHomeFragment();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemSelected(@NonNull ItemSelectedEvent<MovieItem> event) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MovieActivity.MOVIE_ITEM_EXTRA, new MovieItemParcelableWrapper(event.getMessage()));
        startActivity(intent);
    }

    private void startHomeFragment() {
        if (mMoviesFragment == null) {
            mMoviesFragment = new MoviesFragment();
            mMoviesFragment.setRetainInstance(true);
            mMoviesFragmentBuilder.seedInstance(mMoviesFragment);
            mMoviesFragmentBuilder
                    .dataSource(mMoviesWebService)
                    .build().inject(mMoviesFragment);
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment != mMoviesFragment) {
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMoviesFragment, R.id.contentFrame);
        }
    }

    private void startFavoriteFragment() {
        if (mFavoriteMoviesFragment == null) {
            mFavoriteMoviesFragment = new MoviesFragment();
            mFavoriteMoviesFragment.setRetainInstance(true);
            mMoviesFragmentBuilder.seedInstance(mFavoriteMoviesFragment);
            mMoviesFragmentBuilder.dataSource(mMoviesRepository).build().inject(mFavoriteMoviesFragment);
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment != mFavoriteMoviesFragment) {
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mFavoriteMoviesFragment, R.id.contentFrame);
        }
    }
}
