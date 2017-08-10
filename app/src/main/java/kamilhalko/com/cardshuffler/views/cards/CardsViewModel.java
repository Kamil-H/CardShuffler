package kamilhalko.com.cardshuffler.views.cards;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

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
import kamilhalko.com.cardshuffler.data.network.error.ApiError;

public class CardsViewModel extends ViewModel {
    private ObservableField<Resource> resource = new ObservableField<>();
    private PublishSubject<Resource<CardsResponse>> cards = PublishSubject.create();
    private PublishSubject<ApiError> error = PublishSubject.create();

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
                        resource.set(deckResource);
                        resource.notifyChange();
                        switch (deckResource.status) {
                            case SUCCESS:
                                return getDataManager().getCards(deckResource.data.getDeckId());
                            case ERROR:
                                error.onNext(deckResource.apiError);
                                return Observable.empty();
                        }
                        return Observable.empty();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<Resource<CardsResponse>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Resource<CardsResponse> cardsResponse) throws Exception {
                        resource.set(cardsResponse);
                        resource.notifyChange();
                        switch (cardsResponse.status) {
                            case SUCCESS:
                                cards.onNext(cardsResponse);
                                break;
                            case ERROR:
                                error.onNext(cardsResponse.apiError);
                                break;
                        }
                    }
                }));
    }

    public PublishSubject<ApiError> getError() {
        return error;
    }

    public PublishSubject<Resource<CardsResponse>> getCards() {
        return cards;
    }

    public ObservableField<Resource> getResource() {
        return resource;
    }
}
