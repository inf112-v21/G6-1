package inf112.skeleton.app.card;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.Random;


public class CardDeck {
    public ArrayList<Card> cardDeck= new ArrayList<>();
    public Random rand = new Random();
    public CardDeck(){
        renderCardDeck();
    }

    /**
     * Creates a sorted card deck ,with all 84 cards with correct priority
     */
    private void renderCardDeck() {
        int priority = 490;
        for(int i = 0; i<18; i++){
            cardDeck.add(new MoveOne(priority, Action.MOVE_ONE));
            priority += 10;
        }
        priority = 670;
        for(int i = 0; i<12; i++){
            cardDeck.add(new MoveTwo(priority, Action.MOVE_TWO));
            priority += 10;
        }
        priority = 790;
        for(int i = 0; i<6; i++){
            cardDeck.add(new MoveThree(priority, Action.MOVE_THREE));
            priority += 10;
        }
        priority = 70;
        for(int i = 0; i<18; i++){
            cardDeck.add(new RotateLeft(priority, Action.ROTATE_LEFT));
            priority += 10;
        }
        priority = 80;
        for(int i = 0; i<18; i++){
            cardDeck.add(new RotateRight(priority, Action.ROTATE_RIGHT));
            priority += 10;
        }
        priority = 10;
        for(int i = 0; i<6; i++){
            cardDeck.add(new UTurn(priority, Action.U_TURN));
            priority += 10;
        }
        priority = 430;
        for(int i = 0; i<6; i++) {
            cardDeck.add(new BackUp(priority, Action.BACK_UP));
            priority += 10;
        }
    }

    /**
     * Returns a random number between 0-84
     * @return int
     */
    public int random(){
        return rand.nextInt(83);
    }



    /**
     * @return enough cards for one player one round "Shuffled"
     */
    public ArrayList<Card> dealNineCards() {
        ArrayList<Card> cardDeckOneRound = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            cardDeckOneRound.add(cardDeck.get(random()));
        }
        return cardDeckOneRound;
    }
}
