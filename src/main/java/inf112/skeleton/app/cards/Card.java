package inf112.skeleton.app.cards;
import inf112.skeleton.app.shared.Action;
public abstract class Card {
  public  int priority;
  public   Action action;


  public Card(int priority, Action action) {
    this.priority = priority;
    this.action = action;
    }
}
