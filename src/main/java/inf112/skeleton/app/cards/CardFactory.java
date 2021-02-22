package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;

public class CardFactory extends Sprite {
    public Texture cardMoveOneTexture = new Texture("Cards/Move1.png");
    public Texture cardMoveTwoTexture = new Texture("Cards/Move2.png");
    public Texture cardMoveThreeTexture = new Texture("Cards/Move3.png");
    public Texture cardRotateLeftTexture = new Texture("Cards/RotateLeft.png");
    public Texture cardRotateRightTexture = new Texture("Cards/RotateRight.png");
    public Texture cardUturnTexture = new Texture("Cards/U-turn.png");
    public Texture cardBackUp = new Texture("Cards/BackUp.png");
    // TODO card skal ikke ha en direction. De skal handle ut i fra player sin direction, dvs player å få direction
    // Card or CardMoveX???
    public CardMoveOne createCardMoveOne(int priority) {
        return new CardMoveOne(new Sprite(cardMoveOneTexture), priority, Action.MOVE_ONE);
    }

    public Card createCardMoveTwo(int priority) {
        return new CardMoveTwo((new Sprite(cardMoveTwoTexture)),priority, Action.MOVE_TWO);
    }

    public Card createCardMoveThree(int priority) {
        return new CardMoveThree((new Sprite(cardMoveThreeTexture)), priority, Action.MOVE_THREE);
    }

    public Card createCardRotateLeft(int priority) {
        return new CardRotateLeft((new Sprite(cardRotateLeftTexture)), priority, Action.ROTATE_LEFT);
    }

    public Card createCardRotateRight(int priority) {
        return new CardRotateRight((new Sprite(cardRotateRightTexture)), priority, Action.ROTATE_RIGHT);
    }

    public Card createCardUturn(int priority) {
        return new CardUturn((new Sprite(cardUturnTexture)), priority, Action.U_TURN);
    }

    public Card createCardBackUp(int priority) {
        return new CardBackUp((new Sprite(cardBackUp)), priority, Action.BACK_UP);
    }

}