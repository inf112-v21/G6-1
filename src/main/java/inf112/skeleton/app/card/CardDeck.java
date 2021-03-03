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
        int cardPriority = 490;
        for(int moveOneCard = 0; moveOneCard < 18; moveOneCard++){
            cardDeck.add(new MoveOne(cardPriority, Action.MOVE_ONE));
            cardPriority += 10;
        }
        cardPriority = 670;
        for(int i = 0; i<12; i++){
            cardDeck.add(new MoveTwo(cardPriority, Action.MOVE_TWO));
            cardPriority += 10;
        }
        cardPriority = 790;
        for(int i = 0; i<6; i++){
            cardDeck.add(new MoveThree(cardPriority, Action.MOVE_THREE));
            cardPriority += 10;
        }
        cardPriority = 70;
        for(int i = 0; i<18; i++){
            cardDeck.add(new RotateLeft(cardPriority, Action.ROTATE_LEFT));
            cardPriority += 10;
        }
        cardPriority = 80;
        for(int i = 0; i<18; i++){
            cardDeck.add(new RotateRight(cardPriority, Action.ROTATE_RIGHT));
            cardPriority += 10;
        }
        cardPriority = 10;
        for(int i = 0; i<6; i++){
            cardDeck.add(new UTurn(cardPriority, Action.U_TURN));
            cardPriority += 10;
        }
        cardPriority = 430;
        for(int i = 0; i<6; i++) {
            cardDeck.add(new BackUp(cardPriority, Action.BACK_UP));
            cardPriority += 10;
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
