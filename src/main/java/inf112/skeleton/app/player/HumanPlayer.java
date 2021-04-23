package inf112.skeleton.app.player;

import java.util.Arrays;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.card.Card;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.BoardItems.*;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.graphics.TileLayers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * This class is to be used to create an human players.
 * This class keeps track of everything concerning a human player.
 * It also contain methods for matching players sprite in graphics class to the player
 */

public class HumanPlayer extends Player {


    public HumanPlayer(Direction direction, int id, Color color) {
        super(direction, id, color);
    }


    private float mouseClickXCoordinate;
    private float mouseClickYCoordinate;
    public final Hole hole = new Hole();
    public final Laser laser = new Laser();
    public final Walls walls = new Walls();
    private final CardMoveLogic cardMoveLogic;
    public final BoardItems boardItems = new BoardItems();
    private final Vector3 mouseClickPosition;
    {
        cardMoveLogic = new CardMoveLogic();
        mouseClickPosition = new Vector3();
    }


    @Override
    public int getPlayerHealth(){
        return this.healthToken;
    }


    @Override
    public int getPlayerDamageTaken(){
        return this.damageTaken;
    }


    @Override
    public void dealDamageToPlayer(){
        this.damageTaken++;
        if (this.damageTaken >= 10){
            takePlayerLife();
        }
    }


    @Override
    public void setNewPlayerCheckpointLocation(float xPosition, float yPosition){
        this.playerCheckpointPositionX = xPosition;
        this.playerCheckpointPositionY = yPosition;

    }


    @Override
    public void takePlayerLife() {
        this.healthToken -- ;
        this.damageTaken = 0;
        this.updatePlayerXPosition(this.playerCheckpointPositionX);
        this.updatePlayerYPosition(this.playerCheckpointPositionY);
    }


    @Override
    public boolean isPlayerAlive(){
        return this.healthToken <= 0;
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
    public float getPlayerXPosition() { return this.playerCurrentXPosition; }


    @Override
    public float getPlayerYPosition() { return this.playerCurrentYPosition; }


    @Override
    public boolean hasPlayerVisitedAllFlags(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell flagTile = flagLayer.getCell(normalizedCoordinates(playerCurrentXPosition),
                                      normalizedCoordinates(playerCurrentYPosition));
        if(flagTile!=null && !this.flagsToVisit.isEmpty() && this.flagsToVisit.get(0) == flagTile.getTile().getId()){
            this.flagsToVisit.remove(0);
        }
        return this.flagsToVisit.isEmpty();
    }


    @Override
    public boolean isPlayerOnBoard(float xDirection, float yDirection) {
        return !(xDirection < 0 || xDirection > 3300 || yDirection < 0 || yDirection > 3900);
    }


    @Override
    public int normalizedCoordinates(float unNormalizedValue) {
        return (int) unNormalizedValue/300;
    }


    @Override
    public void setPlayerDirection(int moveDegree){
       int newPlayerDirection = this.direction.getDirectionDegree() + moveDegree;
       if(newPlayerDirection > 270) newPlayerDirection = newPlayerDirection - 360;
       if(newPlayerDirection < 0) newPlayerDirection = 270;

       switch (newPlayerDirection){
           case 0:
               this.direction = Direction.NORTH;
               break;
           case 90:
               this.direction = Direction.EAST;
               break;
           case 180:
               this.direction = Direction.SOUTH;
               break;
           case 270:
               this.direction = Direction.WEST;

       }
    }


    @Override
    public void doPlayerMove(Card card, TileLayers tileLayers){
        if (cardMoveLogic.moveTypeCard(card)) {
            playerMoveHandler(card,tileLayers.wall);
        }else if (!cardMoveLogic.moveTypeCard(card)) {
            setPlayerDirection((int)card.action.getAction());
        }
    }


    @Override
    public void playerMoveHandler(Card moveCard, TiledMapTileLayer wall){
        int collidedWithWall;
        float checkXPosition;
        float checkYPosition;
        float moveCardAction = moveCard.action.getAction();
        ArrayList<Float> nextCoordinatesToCheck;

        if(moveCard.action == Action.BACK_UP){
            moveCardAction = moveCardAction *(-1);
        }

        for(float movement = 0; movement <= moveCardAction; movement+=300){

            nextCoordinatesToCheck = getCoordinatesToCheck(movement, moveCard);
            checkXPosition = nextCoordinatesToCheck.get(0);
            checkYPosition = nextCoordinatesToCheck.get(1);
            collidedWithWall= walls.hasPlayerCollidedWithWall(wall,this,
                    normalizedCoordinates(checkXPosition), normalizedCoordinates(checkYPosition), moveCard);

            if(!isPlayerOnBoard(checkXPosition,checkYPosition)){
                break;
            }
            else if(collidedWithWall == 0){
                wallCollisionHandler(checkXPosition,checkYPosition);
                break;
            }else if(collidedWithWall == 1){
                wallCollisionHandler(this.getPlayerXPosition(), this.getPlayerYPosition());

                break;
            }else{
                setPlayerNewLocation(checkXPosition, checkYPosition);
            }
        }
    }


    @Override
    public ArrayList<Float> getCoordinatesToCheck(float amountOfMovement, Card card){
        ArrayList<Float> coordinatesToCheck = new ArrayList<>();
        float newPosition = 0;
        float checkXPosition = this.getPlayerXPosition();
        float checkYPosition = this.getPlayerYPosition();

        if (amountOfMovement != 0) {
            newPosition =  300 * this.direction.getMoveDirection();
            if(card.action == Action.BACK_UP){

                newPosition = -newPosition;

            }
        }
        if(walls.getPlayerXYDirection(this) == 'x'){
            checkXPosition = newPosition + this.getPlayerXPosition();

        }else if (walls.getPlayerXYDirection(this) == 'y'){
            checkYPosition = newPosition + this.getPlayerYPosition();
        }
        coordinatesToCheck.add(checkXPosition);
        coordinatesToCheck.add(checkYPosition);

        return coordinatesToCheck;
    }


    @Override
    public void wallCollisionHandler(float afterCollisionX, float afterCollisionY){
        updatePlayerXPosition(afterCollisionX);
        updatePlayerYPosition(afterCollisionY);
        System.out.println("Player hit a wall! old damage " + this.damageTaken);
        dealDamageToPlayer();
        System.out.println("new damage " + this.damageTaken);
    }


    @Override
    public void setPlayerNewLocation(float xPosition, float yPosition) {
        updatePlayerXPosition(xPosition);
        updatePlayerYPosition(yPosition);

    }


    @Override
    public void resetPlayerForNewRound(Player player){
        player.movedCards = new ArrayList<>();
        player.chosenCards = new ArrayList<>();
        player.playerDeck = new ArrayList<>();
        player.ready = false;
        player.playerDeck = cardMoveLogic.playerDeck();
        player.cardCoordinates = cardMoveLogic.resetCardCoordinates();
    }


    @Override
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


    @Override
    public void newHumanPlayer (int id, Color color, float playerStartXPosition,float playerStartYPosition){
        this.id = id;
        this.color = color;
        this.ready = false;
        this.healthToken = 3;
        this.damageTaken = 0;
        this.direction = Direction.NORTH;
        this.playerCurrentXPosition = 0;
        this.playerCurrentYPosition = 0;
        this.playerCheckpointPositionX = playerStartXPosition;
        this.playerCheckpointPositionY = playerStartYPosition;
        this.movedCards = new ArrayList<>();
        this.chosenCards  =  new ArrayList<>();
        this.playerDeck = cardMoveLogic.playerDeck();
        this.flagsToVisit = new ArrayList<>(Arrays.asList(55,63,71));
        this.cardCoordinates = new ArrayList<>(
                Arrays.asList(5555f, 3090f,
                        6080f, 3090f,
                        6605f, 3090f,
                        5555f, 2370f,
                        6080f, 2370f,
                        6605f, 2370f,
                        5555f, 1640f,
                        6080f, 1640f,
                        6605f, 1640f));
    }


    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        float x  = mouseClickXCoordinate;
        float y = mouseClickYCoordinate;

        if (x >5555 && x < 6005 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(0, this);
        } else if(x >6080 && x < 6535 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(1, this);
        } else if(x >6605 && x < 7060 && y >= 3090 && y <= 3740){
            cardMoveLogic.moveCardWhenClicked(2,this);
        } else if(x >5555 && x < 6005 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(3,this);
        } else if(x >6080 && x < 6535 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(4,this);
        } else if(x >6605 && x < 7060 && y >= 2370 && y <= 3020){
            cardMoveLogic.moveCardWhenClicked(5,this);
        } else if(x >5555 && x < 6005 && y >= 1640 && y <= 2260){
            cardMoveLogic.moveCardWhenClicked(6,this);
        } else if(x >6080 && x < 6535 && y >= 1640 && y <= 2260){
            cardMoveLogic.moveCardWhenClicked(7,this);
        } else if(x >6605 && x < 7060 && y >= 1640 && y <= 2260){
            cardMoveLogic.moveCardWhenClicked(8,this);
        } else if(x >6590 && x < 7070 && y <= 825 && y >= 520) {
            cardMoveLogic.resetCard(this);
        } else if (this.chosenCards.size() >= 5 && x >6590 && x < 7070 && y <= 1155 && y >= 850) {
            this.ready = true;
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
    public boolean keyDown(int keyPressed) {
        if(keyPressed == Input.Keys.UP) updatePlayerYPosition(getPlayerYPosition()+300);
        if(keyPressed == Input.Keys.DOWN) updatePlayerYPosition(getPlayerYPosition()-300);
        if(keyPressed == Input.Keys.RIGHT) updatePlayerXPosition(getPlayerXPosition()+300);
        if(keyPressed == Input.Keys.LEFT) updatePlayerXPosition(getPlayerXPosition()-300);
        return false;}
    @Override
    public boolean keyUp(int keyPressed) { return false;}
    @Override
    public boolean keyTyped(char c) {return false;}
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {return false;}


}
