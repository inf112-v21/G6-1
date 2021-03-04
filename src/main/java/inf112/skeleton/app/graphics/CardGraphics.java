package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class CardGraphics  extends ScreenAdapter {


    public HashMap<Action, Texture> getCardTexture() {
        HashMap<Action, Texture> cardTexture = new HashMap<>();
        cardTexture.put(Action.MOVE_ONE, new Texture("Cards/Move1.png"));
        cardTexture.put(Action.MOVE_TWO, new Texture("Cards/Move2.png"));
        cardTexture.put(Action.MOVE_THREE, new Texture("Cards/Move3.png"));
        cardTexture.put(Action.ROTATE_LEFT, new Texture("Cards/RotateLeft.png"));
        cardTexture.put(Action.ROTATE_RIGHT, new Texture("Cards/RotateRight.png"));
        cardTexture.put(Action.BACK_UP, new Texture("Cards/BackUp.png"));
        cardTexture.put(Action.U_TURN, new Texture("Cards/U-turn.png"));
        return cardTexture;
    }
    public ArrayList<Sprite> createCardSprite(Player player){

        ArrayList<Sprite> cardSprite;
        cardSprite = new ArrayList<>(Arrays.asList(
                new Sprite(getCardTexture().get(player.playerDeck.get(0).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(1).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(2).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(3).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(4).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(5).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(6).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(7).action)),
                new Sprite(getCardTexture().get(player.playerDeck.get(8).action))));

        return cardSprite;
    }


}
