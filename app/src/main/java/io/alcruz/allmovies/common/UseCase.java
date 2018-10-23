package io.alcruz.allmovies.common;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {
    private Q mRequestValues;

    void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public interface RequestValues {
    }

    public interface ResponseValue {
    }
}