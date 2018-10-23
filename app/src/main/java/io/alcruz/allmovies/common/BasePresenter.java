package io.alcruz.allmovies.common;

public interface BasePresenter<T extends BaseView> {
    void start(T view);

    void stop();
}
