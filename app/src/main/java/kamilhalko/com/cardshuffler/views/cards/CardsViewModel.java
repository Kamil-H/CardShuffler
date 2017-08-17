package kamilhalko.com.cardshuffler.views.cards;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.List;

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
    private ObservableField<List<CardVariantsValidator.VariantType>> variantTypes = new ObservableField<>();
    private ObservableBoolean isRemaining = new ObservableBoolean();
    private PublishSubject<Resource<CardsResponse>> cards = PublishSubject.create();
    private PublishSubject<ApiError> error = PublishSubject.create();
    private String deckId;
    private CardVariantsValidator cardVariantsValidator;

    @Inject
    public CardsViewModel(DataManager dataManager, CompositeDisposable compositeDisposable, CardVariantsValidator cardVariantsValidator) {
        super(dataManager, compositeDisposable);
        this.cardVariantsValidator = cardVariantsValidator;
    }

    public void downloadDecks(int count) {
        getCompositeDisposable().add(getDataManager().getDecks(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Resource<Deck>, ObservableSource<Resource<CardsResponse>>>() {
                    @Override
                    public ObservableSource<Resource<CardsResponse>> apply(@NonNull Resource<Deck> deckResource) throws Exception {
                        return onDeckResource(deckResource);
                    }
                })
                .subscribe(new Consumer<Resource<CardsResponse>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Resource<CardsResponse> cardsResponse) throws Exception {
                        onCardsResponse(cardsResponse);
                    }
                }));
    }

    public void drawCards() {
        getCompositeDisposable().add(getDataManager().getCards(deckId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Resource<CardsResponse>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull Resource<CardsResponse> cardsResponseResource) throws Exception {
                    onCardsResponse(cardsResponseResource);
                }
            }));
    }

    public void reshuffleCards() {
        getCompositeDisposable().add(getDataManager().shuffleCard(deckId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Resource<Deck>, ObservableSource<Resource<CardsResponse>>>() {
                    @Override
                    public ObservableSource<Resource<CardsResponse>> apply(@NonNull Resource<Deck> deckResource) throws Exception {
                        return onDeckResource(deckResource);
                    }
                })
                .subscribe(new Consumer<Resource<CardsResponse>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Resource<CardsResponse> cardsResponse) throws Exception {
                        onCardsResponse(cardsResponse);
                    }
                }));
    }

    private Observable<Resource<CardsResponse>> onDeckResource(Resource<Deck> deckResource) {
        resource.set(deckResource);
        switch (deckResource.status) {
            case SUCCESS:
                deckId = deckResource.data.getDeckId();
                return getDataManager().getCards(deckId);
            case ERROR:
                error.onNext(deckResource.apiError);
                return Observable.empty();
        }
        return Observable.empty();
    }

    private void onCardsResponse(Resource<CardsResponse> cardsResponseResource) {
        resource.set(cardsResponseResource);
        switch (cardsResponseResource.status) {
            case SUCCESS:
                isRemaining.set(cardsResponseResource.data.getRemaining() >= 5);
                cards.onNext(cardsResponseResource);
                variantTypes.set(cardVariantsValidator.validate(cardsResponseResource.data.getCards()));
                break;
            case ERROR:
                error.onNext(cardsResponseResource.apiError);
                break;
        }
    }

    public ObservableBoolean getIsRemaining() {
        return isRemaining;
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

    public ObservableField<List<CardVariantsValidator.VariantType>> getVariantTypes() {
        return variantTypes;
    }
}
