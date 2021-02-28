package inf112.skeleton.app.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
import java.util.ArrayList;



public class HumanPlayer extends Player implements InputProcessor {

    public HumanPlayer(TiledMapTileLayer flagLayer, Direction direction, String name) {

        super(flagLayer, direction, name);
    }
    public float CurrentPlayerXPosition;
    public float CurrentPlayerYPosition;
    public float currentCardXPosition;
    public float currentCardYPosition;

    private CardDeck cardDeck = new CardDeck();
    Graphics graphics;
    public ArrayList<Card> playerCardDeck = cardDeck.CardDeal();

    public float setCardStartPositionXDirection(float cardStartPositionXDirection){
        currentCardXPosition = cardStartPositionXDirection;
        return currentCardXPosition;
    }
    public float setCardStartPositionYDirection(float cardStartPositionYDirection){
        currentCardYPosition = cardStartPositionYDirection;
        return currentCardYPosition;
    }
    /**
     * Needed to mach Sprite positions with back-end
     * @param PlayerStartPositionXDirection
     * @return
     */
    public float setPlayerStartPositionXDirection(float PlayerStartPositionXDirection){
        CurrentPlayerXPosition = PlayerStartPositionXDirection;
        return CurrentPlayerXPosition;
    }
    /**
     * Needed to mach Sprite positions with back-end
     * @param PlayerStartPositionYCoordinate
     * @return
     */
    public float setPlayerStartPositionYCoordinate(float PlayerStartPositionYCoordinate){
        CurrentPlayerYPosition = PlayerStartPositionYCoordinate;
        return CurrentPlayerYPosition;
    }

    /**
     * set x position
     * @return
     */
    public float setPlayerXPosition(float newXPosition){
       // if(canPlayerMove(newXPosition, yPosition))
        CurrentPlayerXPosition = newXPosition;
        return CurrentPlayerXPosition;
    }

    /**
     * set y position
     * @return
     */
    public float setYPos(float newYPosition){
        if(canPlayerMove(CurrentPlayerXPosition, newYPosition)) CurrentPlayerYPosition = newYPosition;
        return CurrentPlayerYPosition;
    }

    /**
     *
     * @return x positon of player : float
     */
    public float getX() {
        return CurrentPlayerXPosition;
    }

    /**
     *
     * @return y position of player : float
     */
    public float getY() {
        return CurrentPlayerYPosition;
    }

    /**
     *  Checks if a card is a card that changes the position og a player
     * @param card : Card of a player
     * @return
     */
    public boolean movePlayerCard(Card card){
        return card.action == Action.MOVE_ONE || card.action == Action.MOVE_TWO|| card.action == Action.MOVE_THREE||card.action== Action.BACK_UP;
    }

    /**
     * dummiround method used only for developmen. WILL BE DELETED
     * @param player
     */
    public void round(HumanPlayer player){
        //System.out.println("NEW ROUND");
        ArrayList<Card> playerCard = cardDeck.CardDeal();
        for(int i = 0; i <9; i++){
            Card card = playerCard.get(i);
            //System.out.println(getX() + " old Xpos " + getY() + " old Y pos" );
            //System.out.println(player.direction + " oldDirection");
            //System.out.println(playerCard.get(i).action);
            updatePlayerLocation(player, card);
            //System.out.println(player.direction + " newDirection");
            //System.out.println(getX() + " new Xpos " + getY() + " new Y pos" );
            //System.out.println("  ");
        }
    }

    /**
     * sets new player direction from player cards
     * @param player
     * @param cardDirection
     */
    public void setDirection(HumanPlayer player, Card cardDirection){
       float newDrection = player.direction.getDirection()+cardDirection.action.getAction();
       if(newDrection > 270) newDrection = newDrection - 360;
       if(newDrection < 0) newDrection = 270;
       if(newDrection == 0) player.direction = Direction.NORTH;
       else if (newDrection == 90) player.direction = Direction.EAST;
       else if (newDrection == 180) player.direction = Direction.SOUTH;
       else if (newDrection == 270) player.direction = Direction.WEST;
    }

    /**
     * Update player location or direction from player card
     * @param player
     * @param card
     */
    @Override
    public void updatePlayerLocation(HumanPlayer player, Card card) {
        if (movePlayerCard(card)) {
            if (player.direction == Direction.NORTH && canPlayerMove(getX(),getY()+card.action.getAction())) {
                setYPos(getY()+card.action.getAction());
            } else if (player.direction == Direction.SOUTH && canPlayerMove(getX(),getY()-card.action.getAction())) {
                setYPos(getY()-card.action.getAction());
            } else if (player.direction == Direction.EAST && canPlayerMove(getX()+card.action.getAction(),getY())) {
                setPlayerXPosition(getX()+card.action.getAction());
            } else if (player.direction == Direction.WEST && canPlayerMove(getX()-card.action.getAction(),getY())) {
                setPlayerXPosition(getX()-card.action.getAction());
            }
        } else if (!movePlayerCard(card)) {
            setDirection(player, card);
        }
    }

    @Override
    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(CurrentPlayerXPosition), normalizedCoordinates(CurrentPlayerYPosition));
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
    public boolean isGameOver(TiledMapTileLayer flagLayer) {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }


    /**
     * keyDown registers what happens when the key is pressed down.
     * Player move one tile upon down-press
     *
     * @param keyPressed
     * @return true : boolean
     */

    @Override
    public boolean keyDown(int keyPressed) {
        int tileDirection = 300;
        if (keyPressed == Input.Keys.LEFT) {

            if (keyPressed == Input.Keys.LEFT) {
                setPlayerXPosition(CurrentPlayerXPosition - tileDirection);
            } else if (keyPressed == Input.Keys.RIGHT) {
                setPlayerXPosition(CurrentPlayerXPosition + tileDirection);
            } else if (keyPressed == Input.Keys.UP) {
                setYPos(CurrentPlayerYPosition + tileDirection);
            } else if (keyPressed == Input.Keys.DOWN) {
                setYPos(CurrentPlayerYPosition - tileDirection);
            }
        }
        return true;
    }


    /**
     * keyUp registers what happens when the key is released.
     * Player to stop moving when button is released
     *
     * @param keyPressed
     * @return true : boolean
     */
    @Override
    public boolean keyUp(int keyPressed) {

        return false;
    }


    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    public float cardPosX(){
        return currentCardXPosition;
    }
    public float cardPosY(){
        return currentCardXPosition;
    }


    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        float x  = Gdx.input.getX();
        float y = Gdx.input.getY();
        System.out.println("Y" + Gdx.input.getY());
        System.out.println("X" + Gdx.input.getX());
        if (x >609 && x < 678 && y > 869 && y < 957){
            currentCardXPosition = 3900;
            currentCardYPosition = 3900;
        }
        return false;
    }


    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }


    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }


    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }


    @Override
    public boolean scrolled(int i) {
        return false;
    }


}
