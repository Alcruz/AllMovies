package io.alcruz.allmovies.movies.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.alcruz.allmovies.R;
import io.alcruz.allmovies.common.models.MovieDetail;

public class MovieFragment extends Fragment implements MovieContract.View {
    public static final String MOVIE_ID_ARGUMENT = "movie_id_argument";

    @BindView(R.id.text_view_overview_text)
    TextView mOverviewTextView;
    @BindView(R.id.text_view_title)
    TextView mTitleTextView;
    @BindView(R.id.text_view_rating)
    TextView mRatingTextView;
    @BindView(R.id.image_view_backdrop)
    ImageView mBackdropImageView;
    @BindView(R.id.progress_rating)
    ProgressBar mRatingProgressBar;
    @BindView(R.id.progress)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.container)
    View mContainer;
    @BindView(R.id.image_fav_movie)
    ImageView mFavImageButton;

    @Inject
    MovieContract.Presenter mPresenter;

    @Inject
    public MovieFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View viewRoot = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, viewRoot);

        mFavImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.toggleFavorite();
            }
        });

        return viewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        int movieId = getArguments().getInt(MOVIE_ID_ARGUMENT);
        mPresenter.start(this);
        mPresenter.loadMovie(movieId);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showMovie(MovieDetail movie) {
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .into(mBackdropImageView);
        mTitleTextView.setText(movie.getTitle());
        mOverviewTextView.setText(movie.getOverview());
        mRatingTextView.setText(String.valueOf(Math.round(movie.getPopularity())));
        mRatingProgressBar.setProgress((int) Math.round(movie.getPopularity()));
        isMovieFavorite(movie.isFavorite());
    }

    @Override
    public void setLoadingIndicator(boolean flag) {
        if (flag) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
            mContainer.setVisibility(View.GONE);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mContainer.setVisibility(View.VISIBLE);
        }
    }

    public void isMovieFavorite(boolean flag) {
        if (flag) {
            mFavImageButton.setImageDrawable(getContext().getDrawable(R.drawable.ic_round_favorite_pink_48dp));
        } else {
            mFavImageButton.setImageDrawable(getContext().getDrawable(R.drawable.ic_round_favorite_white_48dp));
        }
    }
}
