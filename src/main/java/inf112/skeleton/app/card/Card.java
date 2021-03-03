package inf112.skeleton.app.card;
import inf112.skeleton.app.shared.Action;

public abstract class Card implements Comparable<Card> {
  public int priority;
  public Action action;

  public Card(int priority, Action action) {
    this.priority = priority;
    this.action = action;
    }

  @Override
  public int compareTo(Card o) {
    if (o == null) return 1;
    return priority - o.priority;
  }
}
