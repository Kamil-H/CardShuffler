package kamilhalko.com.cardshuffler.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.cardshuffler.data.DataManager;
import kamilhalko.com.cardshuffler.data.DataManagerImpl;
import kamilhalko.com.cardshuffler.di.PerActivity;
import kamilhalko.com.cardshuffler.views.welcome.WelcomeViewModel;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    DataManager provideDataManager() {
        return new DataManagerImpl();
    }

    @Provides
    @PerActivity
    WelcomeViewModel provideWelcomeViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new WelcomeViewModel(dataManager, compositeDisposable);
    }
}
