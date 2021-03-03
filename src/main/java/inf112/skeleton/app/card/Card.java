package inf112.skeleton.app.card;
import inf112.skeleton.app.shared.Action;
public abstract class Card {
  public  int priority;
  public   Action action;


  public Card(int priority, Action action) {
    this.priority = priority;
    this.action = action;
    }
}
