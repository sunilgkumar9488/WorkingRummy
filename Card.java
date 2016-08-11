import java.util.HashMap;
import java.util.Map;

class Card {

    private static Map<String, Integer> NumericalValue = null;

    protected static enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS, JOKER
    }

    protected static enum Colors {
        BLACK, RED, JOKER
    }

    public static void setAceValue(int value) {
        NumericalValue.put("A", value);
    }


    private String face;
    private Colors color;
    private Suit suit;

    private static void createMap() {
        NumericalValue = new HashMap<>();
        NumericalValue.put("2", 2);
        NumericalValue.put("3", 3);
        NumericalValue.put("4", 4);
        NumericalValue.put("5", 5);
        NumericalValue.put("6", 6);
        NumericalValue.put("7", 7);
        NumericalValue.put("8", 8);
        NumericalValue.put("9", 9);
        NumericalValue.put("10", 10);
        NumericalValue.put("J", 11);
        NumericalValue.put("Q", 12);
        NumericalValue.put("K", 13);
        NumericalValue.put("A", 14);
        NumericalValue.put("JOKER", 0);
    }

    /*
     * 	Assessor functions
     *
     */
    protected int getFaceValue() {
        return NumericalValue.get(this.face);
    }

    protected String getFace() {
        return this.face;
    }

    protected Colors getColor() {
        return this.color;
    }

    protected Suit getSuit() {
        return this.suit;
    }


    public Card(String face, Suit suite) {
        if (NumericalValue == null) {
            createMap();
        }
        this.face = face.toUpperCase();
        this.suit = suite;
    }

    public String toString() {
        if (!face.equalsIgnoreCase("JOKER"))
            return getFace() + " of " + getSuit();
        else
            return "JOKER";
    }

}