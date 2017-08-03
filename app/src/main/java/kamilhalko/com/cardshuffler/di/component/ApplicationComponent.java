package kamilhalko.com.cardshuffler.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import kamilhalko.com.cardshuffler.CardShuffler;
import kamilhalko.com.cardshuffler.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(CardShuffler app);
    Context getContext();
}
