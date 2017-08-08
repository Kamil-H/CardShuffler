package kamilhalko.com.cardshuffler.data.models;

import com.google.gson.annotations.SerializedName;

public class Deck {
    @SerializedName("deck_id")
    private String deckId;
    private boolean success;
    private boolean shuffled;
    private int remaining;

    public String getDeckId() {
        return deckId;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public int getRemaining() {
        return remaining;
    }
}
