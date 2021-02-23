package inf112.skeleton.app.cards;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
public abstract class Card {
  public  int priority;
  public   Action action;
  public   Direction direction;

  public Card(int priority, Action action) {
    this.priority = priority;
    this.action = action;
    }
}
