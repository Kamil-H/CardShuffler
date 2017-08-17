package kamilhalko.com.cardshuffler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import kamilhalko.com.cardshuffler.data.models.Card;
import kamilhalko.com.cardshuffler.views.cards.CardVariantsValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardVariantsValidatorTest {

    private List<CardVariantsValidator.VariantType> testValidator(List<Card> cards) {
        CardVariantsValidator cardVariantsValidator = new CardVariantsValidator();
        return cardVariantsValidator.validate(cards);
    }

    @Test
    public void testColorVariant() {
        List<Card> cardsTrue = new ArrayList<>();
        List<Card> cardsFalse = new ArrayList<>();

        cardsTrue.add(new Card("", "2", "HEARTS", ""));
        cardsTrue.add(new Card("", "3", "CLUBS", ""));
        cardsTrue.add(new Card("", "8", "CLUBS", ""));
        cardsTrue.add(new Card("", "KING", "CLUBS", ""));
        cardsTrue.add(new Card("", "QUEEN", "HEARTS", ""));

        cardsFalse.add(new Card("", "2", "HEARTS", ""));
        cardsFalse.add(new Card("", "3", "CLUBS", ""));
        cardsFalse.add(new Card("", "8", "DIAMONDS", ""));
        cardsFalse.add(new Card("", "KING", "CLUBS", ""));
        cardsFalse.add(new Card("", "QUEEN", "SPADES", ""));

        assertTrue("Color not found on list", testValidator(cardsTrue).contains(CardVariantsValidator.VariantType.COLOR));
        assertFalse("Color found on list", testValidator(cardsFalse).contains(CardVariantsValidator.VariantType.COLOR));
    }

    @Test
    public void testStairsVariant() {
        List<Card> cardsTrue = new ArrayList<>();
        List<Card> cardsFalse = new ArrayList<>();

        cardsTrue.add(new Card("", "2", "HEARTS", ""));
        cardsTrue.add(new Card("", "3", "CLUBS", ""));
        cardsTrue.add(new Card("", "8", "CLUBS", ""));
        cardsTrue.add(new Card("", "KING", "CLUBS", ""));
        cardsTrue.add(new Card("", "QUEEN", "HEARTS", ""));

        cardsFalse.add(new Card("", "2", "HEARTS", ""));
        cardsFalse.add(new Card("", "ACE", "CLUBS", ""));
        cardsFalse.add(new Card("", "8", "DIAMONDS", ""));
        cardsFalse.add(new Card("", "7", "CLUBS", ""));
        cardsFalse.add(new Card("", "QUEEN", "SPADES", ""));

        assertTrue("Stairs not found on list", testValidator(cardsTrue).contains(CardVariantsValidator.VariantType.STAIRS));
        assertFalse("Stairs found on list", testValidator(cardsFalse).contains(CardVariantsValidator.VariantType.STAIRS));
    }

    @Test
    public void testFigureVariant() {
        List<Card> cardsTrue = new ArrayList<>();
        List<Card> cardsFalse = new ArrayList<>();

        cardsTrue.add(new Card("", "ACE", "HEARTS", ""));
        cardsTrue.add(new Card("", "2", "CLUBS", ""));
        cardsTrue.add(new Card("", "KING", "CLUBS", ""));
        cardsTrue.add(new Card("", "5", "CLUBS", ""));
        cardsTrue.add(new Card("", "JACK", "HEARTS", ""));

        cardsFalse.add(new Card("", "2", "HEARTS", ""));
        cardsFalse.add(new Card("", "3", "CLUBS", ""));
        cardsFalse.add(new Card("", "8", "DIAMONDS", ""));
        cardsFalse.add(new Card("", "KING", "CLUBS", ""));
        cardsFalse.add(new Card("", "QUEEN", "SPADES", ""));

        assertTrue("Figure not found on list", testValidator(cardsTrue).contains(CardVariantsValidator.VariantType.FIGURE));
        assertFalse("Figure found on list", testValidator(cardsFalse).contains(CardVariantsValidator.VariantType.FIGURE));
    }

    @Test
    public void testTwinsVariant() {
        List<Card> cardsTrue = new ArrayList<>();
        List<Card> cardsFalse = new ArrayList<>();

        cardsTrue.add(new Card("", "2", "HEARTS", ""));
        cardsTrue.add(new Card("", "3", "CLUBS", ""));
        cardsTrue.add(new Card("", "2", "CLUBS", ""));
        cardsTrue.add(new Card("", "KING", "CLUBS", ""));
        cardsTrue.add(new Card("", "2", "HEARTS", ""));

        cardsFalse.add(new Card("", "2", "HEARTS", ""));
        cardsFalse.add(new Card("", "3", "CLUBS", ""));
        cardsFalse.add(new Card("", "8", "DIAMONDS", ""));
        cardsFalse.add(new Card("", "KING", "CLUBS", ""));
        cardsFalse.add(new Card("", "QUEEN", "SPADES", ""));

        assertTrue("Twins not found on list", testValidator(cardsTrue).contains(CardVariantsValidator.VariantType.TWINS));
        assertFalse("Twins found on list", testValidator(cardsFalse).contains(CardVariantsValidator.VariantType.TWINS));
    }
}