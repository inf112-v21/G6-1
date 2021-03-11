package inf112.skeleton.app.graphics;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.shared.Action;
import java.util.ArrayList;
import java.util.HashMap;



public class CardGraphics {

    /**
     * Creates a hashmap of textures for all nine types of cards.
     * This makes it possible to reuse the card textures during the game
     * instead of creating a new texture everytime a player gets a new card
     * @return hash map key is the action type value is a card texture
     */
    public HashMap<Action, Texture> createCardTexture() {
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

    /**
     * Creates a list of nine sprites to be used as the sprites needed for each players cards.
     * A sprite needs a texture to be created, but since this texture will be updated before it is
     * shown to the player. These sprites get a random texture (doesn't matter which)
     * @return List of nine sprite objects
     */
    public ArrayList<Sprite> createCardSprite(){
        ArrayList<Sprite> cardSprite = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            cardSprite.add( new Sprite(new Texture("Cards/U-turn.png")));
        }
        return cardSprite;
    }
}
