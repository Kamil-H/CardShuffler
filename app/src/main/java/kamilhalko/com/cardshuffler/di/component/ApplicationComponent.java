package kamilhalko.com.cardshuffler.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import kamilhalko.com.cardshuffler.data.DataManager;
import kamilhalko.com.cardshuffler.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();
    DataManager getDataManager();
}
