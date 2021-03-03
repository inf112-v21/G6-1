package inf112.skeleton.app.player;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.card.Card;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.card.CardMoveLogic;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * This class is to be used to create an human players.
 * This class keeps track of everything concerning a human player.
 * It also contain methods for matching players sprite in graphics class to the player.
 */

public class HumanPlayer extends Player implements InputProcessor {


    public HumanPlayer(Direction direction, String name, String piece) {
        super(direction, name, piece);
    }

    private float mouseClickXCoordinate;
    private float mouseClickYCoordinate;
    private final CardMoveLogic cardLogic;
    private final Vector3 mouseClickPosition;
    {
        cardLogic = new CardMoveLogic();
        mouseClickPosition = new Vector3();
    }

    @Override
    public float setPlayerStartXPosition(float playerStartXPosition){
        this.playerCurrentXPosition = playerStartXPosition;
        return this.playerCurrentXPosition;
    }

    @Override
    public float setPlayerStartYPosition(float playerStartYPosition){
        this.playerCurrentYPosition = playerStartYPosition;
        return playerCurrentYPosition;
    }

    @Override
    public void updatePlayerXPosition(float newXPosition){
        this.playerCurrentXPosition = newXPosition;
    }

    @Override
    public void updatePlayerYPosition(float newYPosition){
        this.playerCurrentYPosition = newYPosition;
    }

    @Override
    public float getPlayerXPosition() {
        return this.playerCurrentXPosition;
    }

    @Override
    public float getPlayerYPosition() {
        return this.playerCurrentYPosition;
    }

    @Override
    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(playerCurrentXPosition),normalizedCoordinates(playerCurrentYPosition));
        return cell!= null;
    }

    @Override
    public boolean canPlayerMove(float xDirection, float yDirection) {
        return !(xDirection < 0 || xDirection > 3300 || yDirection < 0 || yDirection > 3900);
    }

    @Override
    public int normalizedCoordinates(float unNormalizedValue) {
        return (int) unNormalizedValue/300;
    }

    @Override
    public void setPlayerDirection(Card card){
       float newPlayerDirection = this.direction.getDirection() + card.action.getAction();
       if(newPlayerDirection > 270) newPlayerDirection = newPlayerDirection - 360;
       if(newPlayerDirection < 0) newPlayerDirection = 270;
       if(newPlayerDirection == 0) this.direction = Direction.NORTH;
       else if (newPlayerDirection == 90) this.direction = Direction.EAST;
       else if (newPlayerDirection == 180) this.direction = Direction.SOUTH;
       else if (newPlayerDirection == 270) this.direction = Direction.WEST;
    }

    @Override
    public void updatePlayerLocation(Card card) {
        float cardAction = card.action.getAction();
        if (cardLogic.moveTypeCard(card)) {
            if (this.direction == Direction.NORTH && canPlayerMove(getPlayerXPosition(), getPlayerYPosition() + cardAction)) {
                updatePlayerYPosition(getPlayerYPosition() + cardAction);
            } else if (this.direction == Direction.SOUTH && canPlayerMove(getPlayerXPosition(), getPlayerYPosition() - cardAction)) {
                updatePlayerYPosition(getPlayerYPosition() - cardAction);
            } else if (this.direction == Direction.EAST && canPlayerMove(getPlayerXPosition()+cardAction, getPlayerYPosition())) {
                updatePlayerXPosition(getPlayerXPosition() + cardAction);
            } else if (this.direction == Direction.WEST && canPlayerMove(getPlayerXPosition()-cardAction, getPlayerYPosition())) {
                updatePlayerXPosition(getPlayerXPosition() - cardAction);
            }
        } else if (!cardLogic.moveTypeCard(card)) {
            setPlayerDirection(card);
        }
    }

    /**
     * This method saves mouse click coordinates (coordinates on the window that is pushed) to a vector 3 object.
     * Translated them to map coordinates and saves them to class variables in HumanPlayer.
     * Map coordinates will always match the map regardless of the window size of OS
     * This method has to be called in the render function to get the camera else it will provide a NPE
     * @param camera OrthographicCamera created in graphics
     */
    public void setMouseClickCoordinates(OrthographicCamera camera){
        if (Gdx.input.isTouched()) {
            mouseClickPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mouseClickPosition);
        }
        if(Gdx.input.justTouched()){
            mouseClickXCoordinate = mouseClickPosition.x;
            mouseClickYCoordinate = mouseClickPosition.y;
        }
    }

    //TODO dummy method to do rounds without priority used only for development.
    // Erlend will DELETE BEFORE DELIVERY
    public void round(){
        if(dummyPlayerDeck == 4 && chosenCards.size() == 5){
            updatePlayerLocation(chosenCards.get(0));
            updatePlayerLocation(chosenCards.get(1));
            updatePlayerLocation(chosenCards.get(2));
            updatePlayerLocation(chosenCards.get(3));
            updatePlayerLocation(chosenCards.get(4));
            dummyPlayerDeck = 9;
            chosenCards = new ArrayList<>();
            playerDeck = new ArrayList<>();
            playerDeck = cardLogic.playerDeck();
            cardCoordinates = cardLogic.cardOnHandCoordinates();
        }
    }

    /**
     * Create a click-box around the cards the player is dealt.
     * When the card on the screen is clicked with the mouse.
     * The card sprite moves to the programming slots on the board
     * The card is added to players chosenCard list.
     * The dummyPlayerDeck get one less
     */
    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        float x  = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >5555 && x < 6005 && y >= 3090 && y <= 3740){
            chosenCards.add(playerDeck.get(0));
            dummyPlayerDeck -=1;
            cardLogic.updateCardPosition(0,1,this);
        }
        else if(x >6080 && x < 6535 && y >= 3090 && y <= 3740){
            chosenCards.add(playerDeck.get(1));
            dummyPlayerDeck -= 1;
            cardLogic.updateCardPosition(2,3,this);
        }
        else if(x >6605 && x < 7060 && y >= 3090 && y <= 3740){
            chosenCards.add(playerDeck.get(2));
            dummyPlayerDeck -= 1;
            cardLogic.updateCardPosition(4,5,this);
        }
        else if(x >5555 && x < 6005 && y >= 2370 && y <= 3020){
            chosenCards.add(playerDeck.get(3));
            dummyPlayerDeck -=1;
            cardLogic.updateCardPosition(6,7,this);
        }
        else if(x >6080 && x < 6535 && y >= 2370 && y <= 3020){
            chosenCards.add(playerDeck.get(4));
            dummyPlayerDeck -=1;
            cardLogic.updateCardPosition(8,9,this);
        }
        else if(x >6605 && x < 7060 && y >= 2370 && y <= 3020){
            chosenCards.add(playerDeck.get(5));
            dummyPlayerDeck -= 1 ;
            cardLogic.updateCardPosition(10,11,this);
        }
        else if(x >5555 && x < 6005 && y >= 1640 && y <= 2290){
            chosenCards.add(playerDeck.get(6));
            dummyPlayerDeck -= 1 ;
            cardLogic.updateCardPosition(12,13,this);
        }
        else if(x >6080 && x < 6535 && y >= 1640 && y <= 2290){
            chosenCards.add(playerDeck.get(7));
            dummyPlayerDeck -= 1 ;
            cardLogic.updateCardPosition(14,15,this);
        }
        else if(x >6605 && x < 7060 && y >= 1640 && y <= 2290){
            chosenCards.add(playerDeck.get(8));
            dummyPlayerDeck -= 1 ;
            cardLogic.updateCardPosition(16,17,this);
        }
        return false;
    }
    @Override
    public boolean touchDragged(int i, int i1, int i2) {return false;}
    @Override
    public boolean mouseMoved(int i, int i1) {return false;}
    @Override
    public boolean scrolled(int i) {return false;}
    @Override
    public boolean keyDown(int keyPressed) {return false;}
    @Override
    public boolean keyUp(int keyPressed) {return false;}
    @Override
    public boolean keyTyped(char c) {return false;}
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {return false;}

}