package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

public class CardMoveRight extends Card {
    public CardMoveRight(Sprite sprite, int priority, Action action, Direction direction) {
        super(sprite,priority, action, direction);
    }
}
