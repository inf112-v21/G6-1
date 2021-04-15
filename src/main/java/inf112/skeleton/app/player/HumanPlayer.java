package inf112.skeleton.app.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.BoardItems.*;
import inf112.skeleton.app.card.Card;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.graphics.TileLayers;
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
    public TileLayers tileLayers;

    public final Laser laser = new Laser();
    public final Conveyor conveyor = new Conveyor();
    public final Gear gear = new Gear();
    public final Hole hole = new Hole();
    public final Walls walls = new Walls();
    private float mouseClickXCoordinate;
    private float mouseClickYCoordinate;

    private final CardMoveLogic cardMoveLogic;
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

    //TODO Hossein will check if he needs this before delivery
    public void setId(int id) {
        this.id = id;
    }

    //TODO Hossein will check if he needs this before delivery
    public int getId() {
        return this.id;
    }


    @Override
    public void takePlayerLife() {
        this.healthToken -- ;
        this.damageTaken = 0;
        this.updatePlayerXPosition(0);
        this.updatePlayerYPosition(0);
        System.out.println("Player "+ this.color + " lost a life and has now " + this.healthToken
                + " lives and " + this.damageTaken + " damage");

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
    public boolean keepPlayerOnBoard(float xDirection, float yDirection) {
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
    public float movePlayerAsFarAsPossible(float position, Direction moveDirection){
        if(moveDirection == Direction.NORTH && !keepPlayerOnBoard(getPlayerXPosition(),position)) return 3900;
        else if(moveDirection == Direction.SOUTH && !keepPlayerOnBoard(getPlayerXPosition(),position) ) return 0;
        else if(moveDirection == Direction.WEST && !keepPlayerOnBoard(position, getPlayerYPosition()) ) return 0;
        else if(moveDirection == Direction.EAST && !keepPlayerOnBoard(position, getPlayerYPosition()) ) return 3300;
        return position;
    }


    @Override
    public void updatePlayerLocation(Card card) {
        float cardAction = card.action.getAction();
        if (cardMoveLogic.moveTypeCard(card)) {
            if(this.direction == Direction.NORTH){
                updatePlayerYPosition(movePlayerAsFarAsPossible(getPlayerYPosition()+ cardAction, this.direction ));
            }
            else if (this.direction == Direction.SOUTH){
                updatePlayerYPosition(movePlayerAsFarAsPossible(getPlayerYPosition() - cardAction, this.direction));
            }
            else if (this.direction == Direction.EAST) {
                updatePlayerXPosition(movePlayerAsFarAsPossible(getPlayerXPosition() + cardAction, this.direction));

            } else if (this.direction == Direction.WEST) {
                updatePlayerXPosition(movePlayerAsFarAsPossible(getPlayerXPosition() - cardAction, this.direction));
            }
        }else if (!cardMoveLogic.moveTypeCard(card)) {
            setPlayerDirection((int)card.action.getAction());
        }
    }





    public void newUpdatePlayerLocation(Card card, TileLayers layer){
        if (cardMoveLogic.moveTypeCard(card)) {
            //System.out.println("moveCard");
            movePlayer(card,layer.wall);
        }else if (!cardMoveLogic.moveTypeCard(card)) {
            setPlayerDirection((int)card.action.getAction());
        }
    }

/**
    public void movePlayer(TiledMapTileLayer wall, Card card){
        float cardAction = card.action.getAction();
        System.out.println("cardAction " +cardAction);
        for(float movement = 300; movement <= cardAction; movement+=300){
            float nextPosition =  300 * this.direction.getMoveDirection();

            if ((this.direction == Direction.WEST || this.direction == Direction.EAST)){
                System.out.println("Old X position " + getPlayerXPosition());
                setNewPlayerLocation(getPlayerXPosition() + nextPosition, getPlayerYPosition());
                System.out.println("New X position " + getPlayerXPosition());

            }else if((this.direction == Direction.NORTH || this.direction == Direction.SOUTH)){
                setNewPlayerLocation(getPlayerXPosition() , getPlayerYPosition()+ nextPosition);
            }else{
                setNewPlayerLocation(getPlayerXPosition(),getPlayerYPosition());
            }
        }
    }
 */
    public void setNewPlayerLocation(float newPosition){
        if(walls.getPlayerXYDirection(this) == Direction.NORTH.getXyDirection()) {
            updatePlayerYPosition(this.getPlayerYPosition() + newPosition);
            updatePlayerXPosition(this.getPlayerXPosition());
        }else{
            updatePlayerYPosition(this.getPlayerYPosition());
            updatePlayerXPosition(this.getPlayerXPosition() + newPosition);
        }
    }

    public void testMove(Card card, TiledMapTileLayer wall){

    }
    /**
     * skrive denne om til move player void use update player moveIfPossible?
     * sjekke alle pos fra der spilleren går også flytte etterpå
     * @param wall

     * @return
     */
    /**
    public void movePlayer(Card card, TiledMapTileLayer wall){
        System.out.println("");
        // vet at det er et move kort
        float cardAction = card.action.getAction(); // finn action aka hvor langt vi skal flytte
        float nextPosition = 0; // instansierer nesteposisjon
        float checkPlayerXPosition = 0;
        float checkPlayerYPosition = 0;
        System.out.println("Card action " + cardAction);
        for(float movement = 0; movement <= cardAction; movement+=300){ // fra og med der vi står til der vi skal

            float checkPosition =  movement * this.direction.getMoveDirection(); // legger til neste pos og Sjekker om vi skal flytte i posetiv x eller y retning

            // fra Her og ut  må vi vite hvilken pos som skal endre seg
            if(walls.getPlayerXYDirection(this) == 'x'){
                System.out.println("Player direction " + walls.getPlayerXYDirection(this));
                 checkPlayerXPosition = checkPosition + this.getPlayerXPosition();
                 checkPlayerYPosition = this.getPlayerYPosition();
            }else{
                 System.out.println("Player direction " + walls.getPlayerXYDirection(this));
                 checkPlayerYPosition = checkPosition + this.getPlayerYPosition();
                 checkPlayerXPosition = this.getPlayerXPosition();
            }
            System.out.println("Check player position  x: " + checkPlayerXPosition +" y "+ checkPlayerYPosition);
            int normXPos = normalizedCoordinates(checkPlayerXPosition); // Normaliserte koordinater som skal sjekkes
            int normYPos = normalizedCoordinates(checkPlayerYPosition);
            boolean isPlayerOnBoard = keepPlayerOnBoard(checkPlayerXPosition, checkPlayerYPosition);
            System.out.println("Is player on board? " + isPlayerOnBoard);
            String hasPlayerCollidedWithWall= walls.hasCollidedWithWall(wall,this, normXPos, normYPos);
            System.out.println("Has player collided with wall? " +hasPlayerCollidedWithWall +"\n");
            if(!isPlayerOnBoard) break;
            if(isPlayerOnBoard && hasPlayerCollidedWithWall != "NO"){
                if(hasPlayerCollidedWithWall == "move"){
                    break;
                }else if(hasPlayerCollidedWithWall == "stop" && (this.getPlayerXPosition() != checkPlayerXPosition || this.getPlayerYPosition() != checkPlayerYPosition)){
                    if(walls.getPlayerXYDirection(this) == 'x'){
                        System.out.println("player collided X  old X " +checkPlayerXPosition);
                        checkPlayerXPosition = checkPlayerXPosition - (300* this.direction.getMoveDirection());
                        System.out.println("player collided X  new X " +checkPlayerXPosition);
                    }else{
                        System.out.println("player collided y  old y " +checkPlayerYPosition);
                        checkPlayerYPosition = checkPlayerYPosition - (300* this.direction.getMoveDirection());
                        System.out.println("player collided y  new y " +checkPlayerYPosition);
                    }
                    break;
                }
            }
        }
        //setNewPlayerLocation(nextPosition* this.direction.getMoveDirection());
        System.out.println(" new x " + checkPlayerXPosition + " new y " + checkPlayerYPosition + "\n");
        setPlayerNewPosition(checkPlayerXPosition, checkPlayerYPosition);
    }*/
    public void movePlayer(Card card, TiledMapTileLayer wall){

        float cardAction = card.action.getAction();
        float checkPlayerXPosition = 0;
        float checkPlayerYPosition = 0;
        float checkPosition = 0 ;
        System.out.println("Card action " + cardAction);
        for(float movement = 0; movement <= cardAction; movement+=300){

            System.out.println(" movement " + movement);
            System.out.println("Current x pos " + this.getPlayerXPosition());
            System.out.println("Current y pos" + this.getPlayerYPosition());
            if (movement != 0) {
                 checkPosition =  300 * this.direction.getMoveDirection();
            }

            if(walls.getPlayerXYDirection(this) == 'x'){
                checkPlayerXPosition = checkPosition + this.getPlayerXPosition();
                checkPlayerYPosition = this.getPlayerYPosition();
            }else if (walls.getPlayerXYDirection(this) == 'y'){
                checkPlayerYPosition = checkPosition + this.getPlayerYPosition();
                checkPlayerXPosition = this.getPlayerXPosition();
            }
            int normXPos = normalizedCoordinates(checkPlayerXPosition);
            int normYPos = normalizedCoordinates(checkPlayerYPosition);

            int hasPlayerCollidedWithWall= walls.hasCollidedTest(wall,this, normXPos, normYPos);

            if(hasPlayerCollidedWithWall == 0){

                setPlayerNewPosition(checkPlayerXPosition,checkPlayerYPosition);
                break;
            }else if(hasPlayerCollidedWithWall == 1){
                setPlayerNewPosition(this.getPlayerXPosition(), this.getPlayerYPosition());

                break;
            }else{
                setPlayerNewPosition(checkPlayerXPosition, checkPlayerYPosition);
            }
            System.out.println("move x pos " + this.getPlayerXPosition());
            System.out.println("move y pos" + this.getPlayerYPosition());
        }
        System.out.println("after loop  x pos " + this.getPlayerXPosition());
        System.out.println("after loop y pos" + this.getPlayerYPosition());
    }

    /**
     *   int normXPos = normalizedCoordinates(checkPlayerXPosition); // Normaliserte koordinater som skal sjekkes
     *             int normYPos = normalizedCoordinates(checkPlayerYPosition);
     *             boolean isPlayerOnBoard = keepPlayerOnBoard(checkPlayerXPosition, checkPlayerYPosition);
     *
     */
    private void setPlayerNewPosition(float xPosition, float yPosition) {
        updatePlayerXPosition(xPosition);
        updatePlayerYPosition(yPosition);
    }

    private void setPlayerCurrentLocation() {
        updatePlayerXPosition(this.getPlayerXPosition());
        updatePlayerYPosition(this.getPlayerYPosition());
    }

    public void singlePlayerRound(ArrayList<Player> players,TileLayers layer) {

        if (this.ready) {
            for(int round = 0; round < 5; round ++) {
                newUpdatePlayerLocation(chosenCards.get(round), layer);
                //updatePlayerLocation(chosenCards.get(round));
            }
            conveyor.runConveyor(players, layer.yellowConveyor, layer.blueConveyor);
            gear.runGears(players,layer.redGear, layer.greenGear);
            laser.fireAllLasers(players,layer.laser);
            hole.hole(players, layer.hole);

            cardMoveLogic.readyButtonClickable(this);
        }
    }


    /**
     * This method saves mouse click coordinates (coordinates on the window that is pushed) to a vector 3 object.
     * Translated them to map coordinates and saves them to class variables in HumanPlayer.
     * Map coordinates will always match the map regardless of the window size of OS
     * This method has to be called in the render function to get the camera else it will provide a NPE
     * @param camera OrthographicCamera created in graphics
     */

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

    /**
     * Create a click-box around the cards the player is dealt.
     * When the card on the screen is clicked with the mouse moveCardWhenClicked is called.
     */

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
    public boolean keyDown(int keyPressed) {return false;}
    @Override
    public boolean keyUp(int keyPressed) { return false;}
    @Override
    public boolean keyTyped(char c) {return false;}
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {return false;}
}
