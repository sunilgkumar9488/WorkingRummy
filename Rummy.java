import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.min;

class Rummy extends Game{

    HashMap<Card.Suit,ArrayList<Integer>> SuitCards = new HashMap<>();
    HashMap<Integer,ArrayList<Card.Suit>> faceValues = new HashMap<>();
    String rankDiff[];
    private  List<Card> handCards;
    private  List<Card> currProcessing;
    private  List<Card> sets;
    private int numOfJokers=0;
    private int needed=0;

    public static List<Integer> getFaceValues(List<Card> cards) {
        List<Integer> values = new ArrayList<Integer>();
        for (Card card : cards) {
            values.add(card.getFaceValue());
        }
        return values;
    }

    public Rummy(List<Card> cards,Card jokerCard)
    {
        int jokerFaceValue=jokerCard.getFaceValue();
        this.handCards=cards;
       // this.currProcessing=cards;
        this.numOfJokers=removeJokers(jokerFaceValue);
        this.currProcessing=this.handCards;
        classifyCards();
        findingSeq();
      //  findingSets();
       // System.out.println(sets);
        System.out.println(currProcessing);
    }

    public void classifyCards() {
        for(Card c:this.currProcessing){
            ArrayList<Integer> cards;
            if(SuitCards.get(c.getSuit())==null){
                cards = new ArrayList<Integer>();
                cards.add(c.getFaceValue());
            }
            else{
                cards = SuitCards.get(c.getSuit());
                cards.add(c.getFaceValue());
            }
            SuitCards.put(c.getSuit(),cards);
        }
    }



    private Card.Suit findFreqSuit(){
        int maxSize=-1;
        Card.Suit freqSuit=Card.Suit.JOKER;
        for(Card.Suit s: SuitCards.keySet()){
            int curr = SuitCards.get(s).size();
            if(maxSize<curr){
                maxSize = curr;
                freqSuit = s;
            }
        }
        return freqSuit;
    }





    public  int removeJokers(int jokerFaceValue){
        int numOfJokers = 0;
        //List<Card.Suit> suits = CardUtils.getSuitValues(this.handCards);
        List<Integer> faceValues=CardUtils.getFaceValues(this.handCards);

        for (int i = 0; i < faceValues.size(); i++)
        {
            if(faceValues.get(i)== 0 || faceValues.get(i)==jokerFaceValue)
            {
                numOfJokers++;
                this.handCards.remove(i);
                faceValues.remove(i);
                i--;
            }
        }
       System.out.println("jokers "+ numOfJokers);
        return numOfJokers;

    }

    private void sortCards() {

    }

    private void findingSeq() {

            Card.Suit key = findFreqSuit();
            ArrayList<Integer> faceValues = SuitCards.get(key);
            String rankDifference="";
           Collections.sort(faceValues);
            for (int i=1;i<faceValues.size();i++)
            {
                rankDifference+= faceValues.get(i)-faceValues.get(i-1);
            }
            //System.out.println(rankDifference);
            if(rankDifference.contains("11"))
            {
                System.out.println(faceValues);
                removeSeqCards(key,faceValues);
//                findingSets();
            }
//            else   if(rankDifference.contains("11"))
//            {
//                System.out.println(faceValues);
//                removeSeqCards(key,faceValues);
//            }
            else    if("12 21".contains(rankDifference) || (rankDifference.contains("2") && faceValues.size()==2))
                {
//                    if(this.numOfJokers>=1)
//                    {
//                        System.out.println(faceValues+""+"1 Joker");
//                        this.numOfJokers--;
//                    }

                        System.out.println(faceValues+""+"need 1 card");needed++;
                        removeSeqCards(key,faceValues);
                }
                else if(rankDifference.contains("2")){
                System.out.println(faceValues+""+"need 1 card");
                faceValues.remove(faceValues.size()-1);
                removeSeqCards(key,faceValues);
                needed++;
            }
        }


    private int findFreqFace(){
        int maxSize=-1;
        int freqFace=0;
        for(Integer s: faceValues.keySet()){
            int curr = faceValues.get(s).size();
            if(maxSize<curr){
                maxSize = curr;
                freqFace = s;
            }
        }
        return freqFace;
    }

    private void findingSets() {
        for (Card c : this.currProcessing) {
            ArrayList<Card.Suit> cards;
            if (faceValues.containsKey(c.getFaceValue())) {
                cards = faceValues.get(c.getFaceValue());
                if (cards.contains(c.getSuit())) ;
                else cards.add(c.getSuit());
            } else {
                cards = new ArrayList<Card.Suit>();
                cards.add(c.getSuit());
            }
            faceValues.put(c.getFaceValue(), cards);
        }
        for (Map.Entry<Integer, ArrayList<Card.Suit>> entry : faceValues.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Card.Suit> suitValues = entry.getValue();
            if (suitValues.size() >= 3) {
                System.out.println(suitValues + " " + key);
                removeCardsOfSameValue(key, suitValues);
                //faceValues.remove(key);

            }
            if (suitValues.size() == 2) {
                if (this.numOfJokers > 0) {
                    numOfJokers--;
                    System.out.println(suitValues + " " + key);
                    removeCardsOfSameValue(key, suitValues);
                    //faceValues.remove(key);
                }
                // else
            }

        }
    }


    private void removeCardsOfSameValue(int faceValue,ArrayList<Card.Suit> suites){
      //  System.out.println(this.currProcessing.size());
        for(int i=0;i<this.currProcessing.size();i++)
        {       //System.out.print(this.currProcessing.get(i).getFaceValue()+" "+this.currProcessing.get(i).getSuit());
                if( this.currProcessing.get(i).getFaceValue()==faceValue && suites.contains(this.currProcessing.get(i).getSuit()) )
                {
                   // if(this.currProcessing.get(i).toString()!="")
                           sets.add(this.currProcessing.get(i));
                    suites.remove(suites.indexOf(this.currProcessing.get(i).getSuit()));
                    this.currProcessing.remove(i);
                    i--;
                }
        }
       // System.out.println(this.currProcessing);
    }


    private void removeSeqCards(Card.Suit suit,ArrayList<Integer> faceValues){
        for(int i=0;i<this.currProcessing.size();i++){
            if(this.currProcessing.get(i).getSuit()==suit && faceValues.contains(this.currProcessing.get(i).getFaceValue())){
                faceValues.remove(faceValues.indexOf(this.currProcessing.get(i).getFaceValue()));
                this.currProcessing.remove(i);
                i--;
            }
        }
    }

}
//
//    int evaluateRummy(List<Card> cards) {
//        int neededCards=0;
//        return neededCards;
//    }
