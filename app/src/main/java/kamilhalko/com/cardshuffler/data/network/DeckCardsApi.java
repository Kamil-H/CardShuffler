package kamilhalko.com.cardshuffler.data.network;

import kamilhalko.com.cardshuffler.data.models.CardsResponse;
import kamilhalko.com.cardshuffler.data.models.Deck;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeckCardsApi {
    @GET("new/shuffle")
    Call<Deck> getDecks(@Query("deck_count") int count);

    @GET("{deck_id}/draw/?count=5")
    Call<CardsResponse> getCards(@Path("deck_id") String deckId);

    @GET("{deck_id}/shuffle")
    Call<Deck> shuffleCard(@Path("deck_id") String deckId);
}
