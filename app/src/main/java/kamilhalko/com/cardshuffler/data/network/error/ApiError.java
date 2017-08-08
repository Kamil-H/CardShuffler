package kamilhalko.com.cardshuffler.data.network.error;

import retrofit2.Response;

public class ApiError {
    private ErrorType errorType;
    private Response response;
    private Throwable throwable;

    public ApiError(ErrorType errorType, Throwable throwable) {
        this.errorType = errorType;
        this.throwable = throwable;
    }

    public ApiError(ErrorType errorType, Response response) {
        this.errorType = errorType;
        this.response = response;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Response getResponse() {
        return response;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
