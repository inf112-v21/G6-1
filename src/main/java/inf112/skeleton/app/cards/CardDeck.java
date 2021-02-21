package inf112.skeleton.app.cards;

import java.util.ArrayList;

public class CardDeck<cardDeck> {
    public ArrayList<Card> cardDeck;
    public CardFactory cardFactory = new CardFactory();
    //TODO Priority er aneledes enn hva vi trodde.... legge inn her.
    //TODO refactor to better than a million for loops (while?)
    //TODO kan denne rendres en gang og brukes gjennom hele spillet??
    public  CardDeck(){
    renderCardDeck();
    }

    private void renderCardDeck() {

        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardMoveOne());
        }
        for(int i = 0; i<12; i++){
            cardDeck.add(cardFactory.createCardMoveTwo());
        }
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardMoveThree());
        }
        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardRotateLeft());
        }
        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardRotateRight());
        }
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardUturn());
        }
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardBackUp());
        }
    }


}
