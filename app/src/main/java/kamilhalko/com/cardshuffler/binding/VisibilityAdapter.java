package kamilhalko.com.cardshuffler.binding;

import android.databinding.BindingAdapter;
import android.view.View;

public class VisibilityAdapter {
    @BindingAdapter("isVisible")
    public static void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}
