package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

public class CardMoveOne extends Card {

    public CardMoveOne(Sprite sprite,int priority, Action action, Direction direction) {
        super(sprite,priority, action, direction);

    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
