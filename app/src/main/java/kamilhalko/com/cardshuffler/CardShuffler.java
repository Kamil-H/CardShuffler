package kamilhalko.com.cardshuffler;

import android.app.Application;

import kamilhalko.com.cardshuffler.di.component.ApplicationComponent;
import kamilhalko.com.cardshuffler.di.component.DaggerApplicationComponent;
import kamilhalko.com.cardshuffler.di.module.ApplicationModule;

public class CardShuffler extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
