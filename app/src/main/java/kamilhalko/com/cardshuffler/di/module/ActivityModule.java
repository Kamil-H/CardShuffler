package kamilhalko.com.cardshuffler.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.cardshuffler.data.DataManager;
import kamilhalko.com.cardshuffler.views.cards.CardVariantsValidator;
import kamilhalko.com.cardshuffler.views.cards.CardsViewModel;
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
    CardVariantsValidator providesCardVariantsValidator() {
        return new CardVariantsValidator();
    }

    @Provides
    WelcomeViewModel provideWelcomeViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new WelcomeViewModel(dataManager, compositeDisposable);
    }

    @Provides
    CardsViewModel provideCardsViewModel(DataManager dataManager, CompositeDisposable compositeDisposable, CardVariantsValidator cardVariantsValidator) {
        return new CardsViewModel(dataManager, compositeDisposable, cardVariantsValidator);
    }
}
