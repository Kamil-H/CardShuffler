package kamilhalko.com.cardshuffler.views.welcome;

import android.content.Context;

import kamilhalko.com.cardshuffler.R;

public enum ErrorType {
    ZERO_CHOSEN;

    public String getText(Context context) {
        switch (this) {
            case ZERO_CHOSEN:
                return context.getString(R.string.WelcomeErrorType_different_number);
            default:
                return context.getString(R.string.WelcomeErrorType_unexpected_error);
        }
    }
}
