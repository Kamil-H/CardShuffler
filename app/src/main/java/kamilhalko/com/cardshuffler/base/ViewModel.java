package kamilhalko.com.cardshuffler.base;

import io.reactivex.disposables.CompositeDisposable;
import kamilhalko.com.cardshuffler.data.DataManager;

public abstract class ViewModel {
    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;

    public ViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    public void onDetach() {
        compositeDisposable.dispose();
    }

    protected DataManager getDataManager() {
        return dataManager;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
