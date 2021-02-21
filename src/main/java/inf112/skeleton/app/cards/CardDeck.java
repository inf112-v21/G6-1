package inf112.skeleton.app.cards;

import java.util.ArrayList;

public class CardDeck<cardDeck> {
    public ArrayList<Card> cardDeck;
    public CardFactory cardFactory = new CardFactory();
    //TODO Priority er aneledes enn hva vi trodde.... legge inn her.
    public  CardDeck(){
    renderCardDeck();
    }

    private void renderCardDeck() {

        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardMoveOne());
        }
    }


}
