package kamilhalko.com.cardshuffler.views.cards;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.data.models.Card;

public class CardVariantsValidator {

    public List<VariantType> validate(List<Card> cards) {
        List<VariantType> variantTypes = new ArrayList<>();
        if (isColor(cards)) {
            variantTypes.add(VariantType.COLOR);
        }
        if (areStairs(cards)) {
            variantTypes.add(VariantType.STAIRS);
        }
        if (isFigure(cards)) {
            variantTypes.add(VariantType.FIGURE);
        }
        if (areTwins(cards)) {
            variantTypes.add(VariantType.TWINS);
        }
        return variantTypes;
    }

    private boolean isColor(List<Card> cards) {
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

    private boolean areTwins(List<Card> cards) {
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
        return !card.getValue().matches("[2-9]|10");
    }

    public enum VariantType {
        COLOR,
        STAIRS,
        FIGURE,
        TWINS;

        public String getDescription(Context context) {
            switch (this) {
                case COLOR:
                    return context.getString(R.string.VariantType_color);
                case STAIRS:
                    return context.getString(R.string.VariantType_stairs);
                case FIGURE:
                    return context.getString(R.string.VariantType_figure);
                case TWINS:
                    return context.getString(R.string.VariantType_twins);
                default:
                    return null;
            }
        }
    }
}
