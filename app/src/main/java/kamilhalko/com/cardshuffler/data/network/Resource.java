package kamilhalko.com.cardshuffler.data.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import kamilhalko.com.cardshuffler.data.network.error.ApiError;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final ApiError apiError;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable ApiError apiError) {
        this.status = status;
        this.data = data;
        this.apiError = apiError;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(ApiError apiError) {
        return new Resource<>(Status.ERROR, null, apiError);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }
}

