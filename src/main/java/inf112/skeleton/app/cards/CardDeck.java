package inf112.skeleton.app.cards;

import java.util.ArrayList;

public class CardDeck<cardDeck> {
    public ArrayList<Card> cardDeck;
    public CardFactory cardFactory = new CardFactory();
    //TODO refactor to better than a million for loops.
    //TODO kan denne rendres en gang og brukes gjennom hele spillet??
    public  CardDeck(){
    renderCardDeck();
    }

    private void renderCardDeck() {
        int priority = 490;
        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardMoveOne(priority));
            priority += 10;
        }
        priority = 670;
        for(int i = 0; i<12; i++){
            cardDeck.add(cardFactory.createCardMoveTwo(priority));
            priority += 10;
        }
        priority = 790;
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardMoveThree(priority));
            priority += 10;
        }
        priority = 70;
        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardRotateLeft(priority));
            priority += 10;
        }
        priority = 80;
        for(int i = 0; i<18; i++){
            cardDeck.add(cardFactory.createCardRotateRight(priority));
            priority += 10;
        }
        priority = 10;
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardUturn(priority));
            priority += 10;
        }
        priority = 430;
        for(int i = 0; i<6; i++){
            cardDeck.add(cardFactory.createCardBackUp(priority));
            priority += 10;
        }
    }


}
