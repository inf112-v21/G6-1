package inf112.skeleton.app.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.card.Card;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.shared.Color;
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


    public HumanPlayer(Direction direction, int id, Color color) {
        super(direction, id, color);
    }

    private float mouseClickXCoordinate;
    private float mouseClickYCoordinate;
    private final CardMoveLogic cardMoveLogic;
    private final Vector3 mouseClickPosition;
    {
        cardMoveLogic = new CardMoveLogic();
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
    public boolean canPlayerMoveX(float xDirection, float yDirection) {
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

// TODO comment
    public float movePlayerAsFarAsPossible(float position){
        if(direction == Direction.NORTH && !canPlayerMoveX(getPlayerXPosition(),position)) return 3900;
        else if(direction == Direction.SOUTH && !canPlayerMoveX(getPlayerXPosition(),position) ) return 0;
        else if(direction == Direction.WEST && !canPlayerMoveX(position, getPlayerYPosition()) ) return 0;
        else if(direction == Direction.EAST && !canPlayerMoveX(position, getPlayerYPosition()) ) return 3300;
        return position;
    }
// TODO update comments
    @Override
    public void updatePlayerLocation(Card card) {
        float cardAction = card.action.getAction();

        if (cardMoveLogic.moveTypeCard(card)) {
            if(this.direction == Direction.NORTH){
                updatePlayerYPosition(movePlayerAsFarAsPossible(getPlayerYPosition()+ cardAction));
            }
            else if (this.direction == Direction.SOUTH){
                updatePlayerYPosition(movePlayerAsFarAsPossible(getPlayerYPosition() - cardAction));
            }
             else if (this.direction == Direction.EAST) {
                updatePlayerXPosition(movePlayerAsFarAsPossible(getPlayerXPosition() + cardAction));

            } else if (this.direction == Direction.WEST) {
                updatePlayerXPosition(movePlayerAsFarAsPossible(getPlayerXPosition() - cardAction));
            }
        } else if (!cardMoveLogic.moveTypeCard(card)) {
            setPlayerDirection(card);
        }
    }

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
            playerDeck = cardMoveLogic.playerDeck();
            cardCoordinates = cardMoveLogic.resetCardCoordinates();
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

    /**
     * Create a click-box around the cards the player is dealt.
     * When the card on the screen is clicked with the mouse moveCardWhenClicked is called.
     */
    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        float x  = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >5555 && x < 6005 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(0,0,1, this);
        }
        else if(x >6080 && x < 6535 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(1,2,3, this);
        }
        else if(x >6605 && x < 7060 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(2,4,5,this);
        }
        else if(x >5555 && x < 6005 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(3,6,7,this);
        }
        else if(x >6080 && x < 6535 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(4,8,9,this);
        }
        else if(x >6605 && x < 7060 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(5,10,11,this);
        }
        else if(x >5555 && x < 6005 && y >= 1640 && y <= 2290){
            cardMoveLogic.moveCardWhenClicked(6,12,13,this);
        }
        else if(x >6080 && x < 6535 && y >= 1640 && y <= 2290){
            cardMoveLogic.moveCardWhenClicked(7,14,15,this);
        }
        else if(x >6605 && x < 7060 && y >= 1640 && y <= 2290){
            cardMoveLogic.moveCardWhenClicked(8,16,17,this);
        }

        if (chosenCards.size() == 5) {
            // TODO tell game you@re done choosing cards
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
