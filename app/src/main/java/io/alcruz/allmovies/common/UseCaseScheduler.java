package io.alcruz.allmovies.common;

public interface UseCaseScheduler {
    void execute(Runnable runnable);
}