package io.alcruz.allmovies.movies.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import io.alcruz.allmovies.common.models.MovieItem;

public class MovieItemParcelableWrapper implements Parcelable {
    public static final Creator<MovieItemParcelableWrapper> CREATOR = new Creator<MovieItemParcelableWrapper>() {
        @Override
        public MovieItemParcelableWrapper createFromParcel(Parcel in) {
            return new MovieItemParcelableWrapper(in);
        }

        @Override
        public MovieItemParcelableWrapper[] newArray(int size) {
            return new MovieItemParcelableWrapper[size];
        }
    };
    private final MovieItem mMovieItem;

    public MovieItemParcelableWrapper(@NonNull MovieItem movieItem) {
        mMovieItem = movieItem;
    }

    private MovieItemParcelableWrapper(Parcel in) {
        Integer id = in.readInt();
        String posterPath = in.readString();
        String title = in.readString();
        boolean isFavorite = in.readByte() != 0;
        mMovieItem = new MovieItem(id, title, posterPath);
        mMovieItem.setFavorite(isFavorite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mMovieItem.getId());
        parcel.writeString(mMovieItem.getPosterPath());
        parcel.writeString(mMovieItem.getTitle());
        parcel.writeByte((byte) (mMovieItem.isFavorite() ? 1 : 0));
    }

    public MovieItem getMovie() {
        return mMovieItem;
    }
}
