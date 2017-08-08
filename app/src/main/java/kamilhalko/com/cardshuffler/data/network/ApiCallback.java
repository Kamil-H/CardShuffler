package kamilhalko.com.cardshuffler.data.network;

import kamilhalko.com.cardshuffler.data.network.error.ApiError;
import kamilhalko.com.cardshuffler.data.network.error.ErrorType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onError(new ApiError(ErrorType.SERVER_ERROR, response));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(new ApiError(ErrorType.INTERNET_CONNECTION, t));
    }

    public abstract void onSuccess(T t);
    public abstract void onError(ApiError apiError);
}
