package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Random;
// TODO huske hvordan man reffererer til super/subklasser
public class CardDeal {
    private Random rand = new Random();
    private ArrayList<CardDeck> cardDeck = new ArrayList<CardDeck>();
    public ArrayList<CardDeck> dealtCards;

    public int random(){
        return rand.nextInt(84);
    }

    public CardDeal() {
        for(int i = 0; i < 9; i++){
            dealtCards.add(cardDeck.get(random()));
        }
    }
}
