package inf112.skeleton.app.cards;

import inf112.skeleton.app.shared.Direction;

public class CardMoveOne extends Card {
    @Override
    public int priority() {
        return 0;
    }

    @Override
    public String cardType() {
        switch (Direction.EAST) {
        }
        return null;
    }

    @Override
    public Direction direction() {
        return null;
    }
}
