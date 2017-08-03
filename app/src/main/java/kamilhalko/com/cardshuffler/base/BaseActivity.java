package kamilhalko.com.cardshuffler.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kamilhalko.com.cardshuffler.CardShuffler;
import kamilhalko.com.cardshuffler.di.component.ActivityComponent;
import kamilhalko.com.cardshuffler.di.component.DaggerActivityComponent;
import kamilhalko.com.cardshuffler.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((CardShuffler) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
