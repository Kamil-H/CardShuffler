package kamilhalko.com.cardshuffler.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import kamilhalko.com.cardshuffler.di.component.ActivityComponent;

public abstract class BaseFragment extends Fragment {
    protected View view;
    private BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }
    }

    protected BaseActivity getBaseActivity() {
        return activity;
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) {
            return activity.getActivityComponent();
        }
        return null;
    }
}
