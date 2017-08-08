package kamilhalko.com.cardshuffler.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardsResponse {
    @SerializedName("deck_id")
    private String deckId;
    private boolean success;
    private List<Card> cards;
    private int remaining;

    public String getDeckId() {
        return deckId;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getRemaining() {
        return remaining;
    }
}
