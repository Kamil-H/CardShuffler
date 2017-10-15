package kamilhalko.com.cardshuffler.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import kamilhalko.com.cardshuffler.data.models.CardsResponse;
import kamilhalko.com.cardshuffler.data.models.Deck;
import kamilhalko.com.cardshuffler.data.network.ApiCallback;
import kamilhalko.com.cardshuffler.data.network.DeckCardsApi;
import kamilhalko.com.cardshuffler.data.network.Resource;
import kamilhalko.com.cardshuffler.data.network.error.ApiError;
import retrofit2.Call;
import retrofit2.Retrofit;

@Singleton
public class DataManagerImpl implements DataManager {
    private Retrofit retrofit;

    @Inject
    public DataManagerImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<Resource<Deck>> getDecks(final int count) {
        return Observable.create(e -> {
            e.onNext(Resource.loading());
            DeckCardsApi deckCardsApi = retrofit.create(DeckCardsApi.class);
            Call<Deck> call = deckCardsApi.getDecks(count);
            call.enqueue(new ApiCallback<Deck>() {
                @Override
                public void onSuccess(Deck deck) {
                    e.onNext(Resource.success(deck));
                }

                @Override
                public void onError(ApiError apiError) {
                    e.onNext(Resource.error(apiError));
                }
            });
        });
    }

    @Override
    public Observable<Resource<CardsResponse>> getCards(final String deckId) {
        return Observable.create(e -> {
            e.onNext(Resource.loading());
            DeckCardsApi deckCardsApi = retrofit.create(DeckCardsApi.class);
            Call<CardsResponse> call = deckCardsApi.getCards(deckId);
            call.enqueue(new ApiCallback<CardsResponse>() {
                @Override
                public void onSuccess(CardsResponse cardsResponse) {
                    e.onNext(Resource.success(cardsResponse));
                }

                @Override
                public void onError(ApiError apiError) {
                    e.onNext(Resource.error(apiError));
                }
            });
        });
    }

    @Override
    public Observable<Resource<Deck>> shuffleCard(final String deckId) {
        return Observable.create(e -> {
            e.onNext(Resource.loading());
            DeckCardsApi deckCardsApi = retrofit.create(DeckCardsApi.class);
            Call<Deck> call = deckCardsApi.shuffleCard(deckId);
            call.enqueue(new ApiCallback<Deck>() {
                @Override
                public void onSuccess(Deck deck) {
                    e.onNext(Resource.success(deck));
                }

                @Override
                public void onError(ApiError apiError) {
                    e.onNext(Resource.error(apiError));
                }
            });
        });
    }
}
