package kamilhalko.com.cardshuffler.views.welcome;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.cardshuffler.ViewModel;
import kamilhalko.com.cardshuffler.data.DataManager;

public class WelcomeViewModel extends ViewModel {
    private PublishSubject<Boolean> isError = PublishSubject.create();
    private PublishSubject<Integer> chosenValue = PublishSubject.create();

    public WelcomeViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void setValue(int value) {
        if (value > 0) {
            chosenValue.onNext(value);
        } else {
            isError.onNext(true);
        }
    }

    public PublishSubject<Boolean> getIsError() {
        return isError;
    }

    public PublishSubject<Integer> getChosenValue() {
        return chosenValue;
    }
}
