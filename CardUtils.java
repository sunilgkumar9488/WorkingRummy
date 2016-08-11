import java.util.Collections;
import java.util.*;


public class CardUtils {

    public static List<Card> removeJoker(List<Card> cards) {
        List<Card> retVal = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() != Card.Suit.JOKER)
                retVal.add(card);
        }
        return retVal;
    }

    public static boolean areSameColor(List<Card> cards) {
        cards = removeJoker(cards);
        Card.Colors firstCardColor = cards.get(0).getColor();
        for (Card card : cards) {
            if (!(card.getColor() == firstCardColor)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areSameSuite(List<Card> cards) {
        cards = removeJoker(cards);
        Card.Suit firstCardSuite = cards.get(0).getSuit();
        for (Card card : cards) {
            if (!(card.getSuit() == firstCardSuite)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areSequential(List<Card> cards) {
        int numOfJokers = 0;
        List<Integer> values = getFaceValues(cards);
        Collections.sort(values);
        if (values.get(0) == -1)
            numOfJokers++;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i + 1) == -1)
                numOfJokers++;
            if (values.get(i + 1) - values.get(i) != 1)
                if (numOfJokers > 0)
                    numOfJokers--;
                else
                    return false;
        }
        return true;
    }

    public static List<Integer> getFaceValues(List<Card> cards) {
        List<Integer> values = new ArrayList<Integer>();
        for (Card card : cards) {
            values.add(card.getFaceValue());
        }
        return values;
    }

    public static List<Card.Suit> getSuitValues(List<Card> cards) {
        List<Card.Suit> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getSuit());
        }
        return values;
    }

    public static List<Card> sortCardsOnSuite(Hand hand) {
        List<Card> sortedList = new ArrayList<>();

        List<List<Card>> AllList = new ArrayList<>();

        final int SUITECOUNT = 4;

        for (int i = 0; i <= SUITECOUNT; i++) {
            AllList.add(new ArrayList<Card>());
        }

        for (Card card : hand.hand) {
            if (card.getSuit() == Card.Suit.SPADES) {
                AllList.get(0).add(card);
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                AllList.get(1).add(card);
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                AllList.get(2).add(card);
            } else if (card.getSuit() == Card.Suit.CLUBS) {
                AllList.get(3).add(card);
            } else if (card.getSuit() == Card.Suit.JOKER) {
                AllList.get(4).add(card);
            }
        }

        for (int i = 0; i <= SUITECOUNT; i++) {

            //System.out.println((AllList.get(i)));
            System.out.println(sortCards(AllList.get(i)));
            sortedList.addAll(sortCards(AllList.get(i)));
        }


        return sortedList;
    }

    public static List<Card> sortCards(List<Card> suite) {
        List<Card> sortList = new ArrayList<Card>();
        sortList.addAll(suite);

        Collections.sort(sortList, new Comparator<Card>() {
            public int compare(Card a, Card b) {
                return a.getFaceValue() >= b.getFaceValue() ? 1 : 0;
            }

        });

        return sortList;
    }

    public static int distinctFaces(List<Card> cards) {
        Set<Integer> faces = new HashSet<Integer>();
        for (Card card : cards) {
            faces.add(card.getFaceValue());
        }
        return faces.size();
    }

    public static int distinctSuits(List<Card> cards) {
        Set<Card.Suit> suits = new HashSet<Card.Suit>();
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
        return suits.size();
    }

    public static boolean containsFace(List<Card> cards, int faceValue) {
        for (Card card : cards) {
            if (card.getFaceValue() == faceValue)
                return true;
        }
        return false;
    }

}
