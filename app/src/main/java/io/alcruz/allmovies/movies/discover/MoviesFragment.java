package io.alcruz.allmovies.movies.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.alcruz.allmovies.R;
import io.alcruz.allmovies.common.models.MovieItem;
import io.alcruz.allmovies.utils.ItemSelectedEvent;
import io.alcruz.allmovies.utils.ScrollChildSwipeRefreshLayout;

public class MoviesFragment extends Fragment implements MoviesContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(android.R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    MoviesContract.Presenter mPresenter;
    @Inject
    EventBus mEventBus;

    private MoviesAdapter mMoviesAdapter;

    @Inject
    public MoviesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_movies, container, false);
        ButterKnife.bind(this, root);

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        mSwipeRefreshLayout.setScrollUpChild(mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadMoreMovies();
            }
        });

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMoviesAdapter = new MoviesAdapter(this.mEventBus, Lists.<MovieItem>newArrayList());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mMoviesAdapter);
        mPresenter.start(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public boolean isLoadingIndicatorShown() {
        return mProgressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void setLoadingIndicator(boolean showIndicator) {
        if (showIndicator) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingTasksError() {
    }

    @Override
    public void showMovies(List<MovieItem> movies) {
        mMoviesAdapter.prependMovies(movies);
    }

    @Override
    public void setLoadingMoreIndicator(boolean flag) {
        mSwipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public boolean isLoadingMoreIndicatorShown() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    public static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
        private final EventBus mEventBus;
        private List<MovieItem> mMovies;

        MoviesAdapter(EventBus eventBus, List<MovieItem> movies) {
            mEventBus = eventBus;
            mMovies = movies;
        }

        void prependMovies(List<MovieItem> movies) {
            movies = Lists.newArrayList(movies);
            movies.addAll(mMovies);
            mMovies = movies;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View rootView = inflater.inflate(R.layout.item_movie, viewGroup, false);
            return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder viewHolder, int position) {
            viewHolder.bindData(mMovies.get(position));
        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.text_view_title)
            TextView mTitleTextView;
            @BindView(R.id.image_thumbnail)
            ImageView mThumbnailImageView;

            private MovieItem mMovieItem;
            private View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MoviesAdapter.this.mEventBus.post(new ItemSelectedEvent<>(mMovieItem));
                }
            };

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(onClickListener);
            }

            void bindData(MovieItem movieItem) {
                mMovieItem = movieItem;
                mTitleTextView.setText(movieItem.getTitle());
                if (movieItem.getPosterPath() != null &&
                        !TextUtils.isEmpty(movieItem.getPosterPath())) {
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500/" + movieItem.getPosterPath())
                            .error(R.drawable.ic_baseline_error_outline_white)
                            .fit()
                            .into(mThumbnailImageView);
                } else {
                    mThumbnailImageView.setImageResource(R.drawable.ic_baseline_error_outline_white);
                }
            }
        }
    }
}
