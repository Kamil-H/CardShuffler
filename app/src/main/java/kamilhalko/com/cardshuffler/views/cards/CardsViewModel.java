package kamilhalko.com.cardshuffler.views.cards;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kamilhalko.com.cardshuffler.base.ViewModel;
import kamilhalko.com.cardshuffler.data.DataManager;
import kamilhalko.com.cardshuffler.data.models.CardsResponse;
import kamilhalko.com.cardshuffler.data.models.Deck;
import kamilhalko.com.cardshuffler.data.network.Resource;

public class CardsViewModel extends ViewModel {
    private PublishSubject<Resource<Deck>> subject = PublishSubject.create();

    @Inject
    public CardsViewModel(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    public void downloadDecks(int count) {
        getCompositeDisposable().add(getDataManager().getDecks(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Resource<Deck>, ObservableSource<Resource<CardsResponse>>>() {
                    @Override
                    public ObservableSource<Resource<CardsResponse>> apply(@NonNull Resource<Deck> deckResource) throws Exception {
                        if (deckResource.data != null) {
                            return getDataManager().getCards(deckResource.data.getDeckId());
                        }
                        return Observable.empty();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<Resource<CardsResponse>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Resource<CardsResponse> cardsResponse) throws Exception {
                        Log.i("CardsViewModel", new Gson().toJson(cardsResponse));
                    }
                }));
    }

    public PublishSubject<Resource<Deck>> getSubject() {
        return subject;
    }
}
