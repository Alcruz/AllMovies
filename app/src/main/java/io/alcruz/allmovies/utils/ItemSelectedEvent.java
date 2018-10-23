package io.alcruz.allmovies.utils;

public class ItemSelectedEvent<T> {
    private final T mMessage;

    public ItemSelectedEvent(T message) {
        mMessage = message;
    }

    public T getMessage() {
        return mMessage;
    }
}
