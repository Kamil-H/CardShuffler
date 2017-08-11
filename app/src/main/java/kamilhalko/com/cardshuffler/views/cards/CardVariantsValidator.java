package kamilhalko.com.cardshuffler.views.cards;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kamilhalko.com.cardshuffler.data.models.Card;

public class CardVariantsValidator {

    public List<VariantType> validate(List<Card> cards) {
        List<VariantType> variantTypes = new ArrayList<>();
        if (isColour(cards)) {
            variantTypes.add(VariantType.COLOUR);
        }
        if (areStairs(cards)) {
            variantTypes.add(VariantType.STAIRS);
        }
        if (isFigure(cards)) {
            variantTypes.add(VariantType.FIGURE);
        }
        if (isPair(cards)) {
            variantTypes.add(VariantType.PAIR);
        }
        return variantTypes;
    }

    private boolean isColour(List<Card> cards) {
        Map<String, Integer> map = new HashMap<>();
        for (Card card : cards) {
            Integer count = map.get(card.getSuit());
            map.put(card.getSuit(), count != null ? count + 1 : 1);
        }
        for (Integer integer : map.values()) {
            if (integer >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean areStairs(List<Card> cards) {
        int[] values = new int[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            values[i] = mapCardToIntValue(cards.get(i));
        }
        int numOfAscValues = 1, numOfDscValues = 1;
        for (int i = 0; i < values.length; i++) {
            if (i < values.length -1 && numOfDscValues < 3) {
                numOfDscValues = values[i] > values[i + 1] ? numOfDscValues + 1 : 1;
            }
            if (i > 0 && numOfAscValues < 3) {
                numOfAscValues = values[i] > values[i - 1] ? numOfAscValues + 1 : 1;
            }
        }
        return numOfAscValues >= 3 || numOfDscValues >= 3;
    }

    private boolean isFigure(List<Card> cards) {
        int figureCount = 0;
        for (Card card : cards) {
            if (isCardFigure(card)) {
                figureCount++;
            }
        }
        return figureCount >= 3;
    }

    private boolean isPair(List<Card> cards) {
        Map<String, Integer> map = new HashMap<>();
        for (Card card : cards) {
            Integer count = map.get(card.getValue());
            map.put(card.getValue(), count != null ? count + 1 : 1);
        }
        for (Integer integer : map.values()) {
            if (integer >= 3) {
                return true;
            }
        }
        return false;
    }

    private int mapCardToIntValue(Card card) {
        if (!isCardFigure(card)) {
            return Integer.parseInt(card.getValue());
        } else {
            switch (card.getValue()) {
                case "JACK":
                    return 11;
                case "QUEEN":
                    return 12;
                case "KING":
                    return 13;
                case "ACE":
                    return 1;
                default:
                    return 0;
            }
        }
    }

    private boolean isCardFigure(Card card) {
        return !TextUtils.isDigitsOnly(card.getValue());
    }

    public enum VariantType {
        COLOUR,
        STAIRS,
        FIGURE,
        PAIR;

        public String getDescription() {
            switch (this) {
                case COLOUR:
                    return "Color!";
                case STAIRS:
                    return "Stairs!";
                case FIGURE:
                    return "Figure!";
                case PAIR:
                    return "Pair!";
                default:
                    return null;
            }
        }
    }
}
