package kamilhalko.com.cardshuffler.views.welcome;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.cardshuffler.base.ViewModel;
import kamilhalko.com.cardshuffler.data.DataManager;

public class WelcomeViewModel extends ViewModel {
    private PublishSubject<ErrorType> error = PublishSubject.create();
    private PublishSubject<Integer> count = PublishSubject.create();

    @Inject
    public WelcomeViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void setValue(int value) {
        if (value > 0) {
            count.onNext(value);
        } else {
            error.onNext(ErrorType.ZERO_CHOSEN);
        }
    }

    public PublishSubject<ErrorType> getError() {
        return error;
    }

    public PublishSubject<Integer> getCount() {
        return count;
    }
}
