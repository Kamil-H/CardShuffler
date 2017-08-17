package kamilhalko.com.cardshuffler.data.models;

public class Card {
    private String image;
    private String value;
    private String suit;
    private String code;

    public Card(String image, String value, String suit, String code) {
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCode() {
        return code;
    }
}
