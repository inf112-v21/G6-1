package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;

public class CardDeck<cardDeck> extends Sprite {
    public ArrayList<Card> cardDeck;
    public ArrayList<Card> fullDeck;
    //public CardFactory cardFactory = new CardFactory();
    //TODO refactor to better than a million for loops.
    //TODO kan denne rendres en gang og brukes gjennom hele spillet??
    public Texture cardMoveOneTexture = new Texture("Cards/Move1.png");
    public Texture cardMoveTwoTexture = new Texture("Cards/Move2.png");
    public Texture cardMoveThreeTexture = new Texture("Cards/Move3.png");
    public Texture cardRotateLeftTexture = new Texture("Cards/RotateLeft.png");
    public Texture cardRotateRightTexture = new Texture("Cards/RotateRight.png");
    public Texture cardUturnTexture = new Texture("Cards/U-turn.png");
    public Texture cardBackUp = new Texture("Cards/BackUp.png");

    public  CardDeck(ArrayList<Card> cardDeck){
       renderCardDeck();
       this.cardDeck = cardDeck;
    }

    private void renderCardDeck() {
        int priority = 490;
        for(int i = 0; i<18; i++){
            cardDeck.add(new CardMoveOne(new Sprite(cardMoveOneTexture), priority, Action.MOVE_ONE));
            priority += 10;
        }
        priority = 670;
        for(int i = 0; i<12; i++){
            cardDeck.add(new CardMoveTwo((new Sprite(cardMoveTwoTexture)),priority, Action.MOVE_TWO));
            priority += 10;
        }
        priority = 790;
        for(int i = 0; i<6; i++){
            cardDeck.add(new CardMoveThree((new Sprite(cardMoveThreeTexture)), priority, Action.MOVE_THREE));
            priority += 10;
        }
        priority = 70;
        for(int i = 0; i<18; i++){
            cardDeck.add(new CardRotateLeft((new Sprite(cardRotateLeftTexture)), priority, Action.ROTATE_LEFT));
            priority += 10;
        }
        priority = 80;
        for(int i = 0; i<18; i++){
            cardDeck.add(new CardRotateRight((new Sprite(cardRotateRightTexture)), priority, Action.ROTATE_RIGHT));
            priority += 10;
        }
        priority = 10;
        for(int i = 0; i<6; i++){
            cardDeck.add(new CardUturn((new Sprite(cardUturnTexture)), priority, Action.U_TURN));
            priority += 10;
        }
        priority = 430;
        for(int i = 0; i<6; i++){
            cardDeck.add(new CardBackUp((new Sprite(cardBackUp)), priority, Action.BACK_UP));
            priority += 10;
        }
    }


}
