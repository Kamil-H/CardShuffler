package kamilhalko.com.cardshuffler.data.network.error;

import android.content.Context;

import kamilhalko.com.cardshuffler.R;

public enum ErrorType {
    INTERNET_CONNECTION,
    SERVER_ERROR;

    public String getMessage(Context context) {
        switch (this) {
            case INTERNET_CONNECTION:
                return context.getString(R.string.ErrorType_no_internet);
            case SERVER_ERROR:
                return context.getString(R.string.ErrorType_server_error);
            default:
                return null;
        }
    }
}
