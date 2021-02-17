package inf112.skeleton.app.cards;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
abstract class Card {
  private final int priority;
  private final Action action;
  private final Direction direction;

  public Card(int priority, Action action, Direction direction) {
    this.priority = priority;
    this.action = action;
    this.direction = direction;
    }


}
