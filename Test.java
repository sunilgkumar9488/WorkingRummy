import java.util.*;

class Test {

    public static void main(String[] args) {
//        Deck d = new Deck(1, 0);
//        d.shuffle();
//        System.out.println(d.toString());
//        Card currentCard = d.draw();
//        Card[] hand = new Card[7];
//        for (int i = 0; i < 7; i++) {
//            hand[i] = d.draw();
//        }

      //  Poker pokerGame = new Poker(0);

        List<Card> best13 = new ArrayList<>();
        best13.add(new Card("Q", Card.Suit.SPADES));
        best13.add(new Card("A", Card.Suit.HEARTS));
        best13.add(new Card("A", Card.Suit.SPADES));
        best13.add(new Card("A", Card.Suit.DIAMONDS));
        best13.add(new Card("10", Card.Suit.SPADES));
      //  best13.add(new Card("0",Card.Suit.JOKER));
       // best13.add(new Card("4",Card.Suit.SPADES));
        best13.add(new Card("4", Card.Suit.HEARTS));
        best13.add(new Card("9", Card.Suit.SPADES));
        Card joker=new Card("4",Card.Suit.DIAMONDS);
        Rummy rummy=new Rummy(best13,joker);
        //System.out.println(pokerGame.evaluate(bestFive));

    }

}