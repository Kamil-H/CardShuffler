package kamilhalko.com.cardshuffler.di.component;

import dagger.Component;
import kamilhalko.com.cardshuffler.di.PerActivity;
import kamilhalko.com.cardshuffler.di.module.ActivityModule;
import kamilhalko.com.cardshuffler.views.welcome.WelcomeFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(WelcomeFragment fragment);
}
