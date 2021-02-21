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
    public CardMoveOne createCardMoveOne() {
        return new CardMoveOne(new Sprite(cardMoveOneTexture), 1, Action.MOVE_ONE);
    }

    public Card createCardMoveTwo() {
        return new CardMoveTwo((new Sprite(cardMoveTwoTexture)), 1, Action.MOVE_TWO);
    }

    public Card createCardMoveThree() {
        return new CardMoveThree((new Sprite(cardMoveThreeTexture)), 1, Action.MOVE_THREE);
    }

    public Card createCardRotateLeft() {
        return new CardRotateLeft((new Sprite(cardRotateLeftTexture)), 1, Action.ROTATE_LEFT);
    }

    public Card createCardRotateRight() {
        return new CardRotateRight((new Sprite(cardRotateRightTexture)), 1, Action.ROTATE_RIGHT);
    }

    public Card createCardUturn() {
        return new CardUturn((new Sprite(cardUturnTexture)), 1, Action.U_TURN);
    }

    public Card createCardBackUp() {
        return new CardBackUp((new Sprite(cardBackUp)), 1, Action.BACK_UP);
    }

}