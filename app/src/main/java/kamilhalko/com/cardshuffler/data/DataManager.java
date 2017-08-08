package kamilhalko.com.cardshuffler.data;

import io.reactivex.Observable;
import kamilhalko.com.cardshuffler.data.models.CardsResponse;
import kamilhalko.com.cardshuffler.data.models.Deck;
import kamilhalko.com.cardshuffler.data.network.Resource;

public interface DataManager {
    Observable<Resource<Deck>> getDecks(int count);
    Observable<Resource<CardsResponse>> getCards(String deckId);
    Observable<Resource<Deck>> shuffleCard(String deckId);
}
