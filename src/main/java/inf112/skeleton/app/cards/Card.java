package inf112.skeleton.app.cards;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
abstract class Card extends Sprite {
  public  int priority;
  public   Action action;
  public   Direction direction;

  public Card(Sprite sprite,int priority, Action action, Direction direction) {
    super(sprite);
    this.priority = priority;
    this.action = action;
    this.direction = direction;
    }
}
