package inf112.skeleton.app.cards;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.Random;

//TODO Split this class again after figuring out why it dosent work

public class CardDeck {
    public ArrayList<Card> cardDeck= new ArrayList<Card>();
    public Random rand = new Random();
    String playerName;
    public CardDeck(){
        renderCardDeck();

    }

    /**
     * Render a sorted card deck ,with all cards and correct priority
     */
    // TODO refactor priority descending or ascending
    private void renderCardDeck() {
        int priority = 490;
        for(int i = 0; i<18; i++){
            cardDeck.add(new moveOne(priority, Action.MOVE_ONE));
            priority += 10;
        }
        priority = 670;
        for(int i = 0; i<12; i++){
            cardDeck.add(new moveTwo(priority, Action.MOVE_TWO));
            priority += 10;
        }
        priority = 790;
        for(int i = 0; i<6; i++){
            cardDeck.add(new moveThree(priority, Action.MOVE_THREE));
            priority += 10;
        }
        priority = 70;
        for(int i = 0; i<18; i++){
            cardDeck.add(new rotateLeft(priority, Action.ROTATE_LEFT));
            priority += 10;
        }
        priority = 80;
        for(int i = 0; i<18; i++){
            cardDeck.add(new rotateRight(priority, Action.ROTATE_RIGHT));
            priority += 10;
        }
        priority = 10;
        for(int i = 0; i<6; i++){
            cardDeck.add(new uTurn(priority, Action.U_TURN));
            priority += 10;
        }
        priority = 430;
        for(int i = 0; i<6; i++) {
            cardDeck.add(new backUp(priority, Action.BACK_UP));
            priority += 10;
        }
    }

    /**
     * For Development, will be removed
     * @return one card from deck.
     */
    public Card getCard(){
        return cardDeck.get(0);
    }

    //TODO Create uniform random function
    public int random(){
        return rand.nextInt(83);
    }

    // Development TO be deleted
    public ArrayList<Card> getDeck(){
        ArrayList<Card>deck = new ArrayList<Card>();
        deck = cardDeck;
        return deck;
    }
    /**
     * @return enough cards for one round "Shuffled"
     */
    public ArrayList<Card> CardDeal() {
        ArrayList<Card> cardDeckOneRound = new ArrayList<Card>();
        for(int i = 0; i < 9; i++){
            cardDeckOneRound.add(cardDeck.get(random()));
        }
        return cardDeckOneRound;
    }
}
