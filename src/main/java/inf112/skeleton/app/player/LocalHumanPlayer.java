package inf112.skeleton.app.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
import java.util.ArrayList;



public class LocalHumanPlayer extends Player implements InputProcessor {

    public LocalHumanPlayer(TiledMapTileLayer flagLayer, Direction direction) {

        super(flagLayer, direction);
    }
    public float xPosition;
    public float yPosition;
    public float cardXPos;
    public float cardYPos;

    private CardDeck cardDeck = new CardDeck();
    Graphics graphics;

    public float setCardX(float xCardStart){
        cardXPos = xCardStart;
        return cardXPos;
    }
    public float setCardY(float yCardStart){
        cardYPos = yCardStart;
        return cardYPos;
    }
    /**
     * Needed to mach Sprite positions with back-end
     * @param xStartPosition
     * @return
     */
    public float xStart(float xStartPosition){
        xPosition = xStartPosition;
        return xPosition;
    }
    /**
     * Needed to mach Sprite positions with back-end
     * @param yStartPosition
     * @return
     */
    public float yStartPos(float yStartPosition){
        yPosition = yStartPosition;
        return yPosition;
    }

    /**
     * set x position
     * @return
     */
    public float setXPos(float newXPosition){
       // if(canPlayerMove(newXPosition, yPosition))
        xPosition = newXPosition;
        return xPosition;
    }

    /**
     * set y position
     * @return
     */
    public float setYPos(float newYPosition){
        if(canPlayerMove(xPosition, newYPosition)) yPosition = newYPosition;
        return yPosition;
    }

    /**
     *
     * @return x positon of player : float
     */
    public float getX() {
        return xPosition;
    }

    /**
     *
     * @return y position of player : float
     */
    public float getY() {
        return yPosition;
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
    public void round(LocalHumanPlayer player){
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
    public void setDirection(LocalHumanPlayer player, Card cardDirection){
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
    public void updatePlayerLocation(LocalHumanPlayer player, Card card) {
        if (movePlayerCard(card)) {
            if (player.direction == Direction.NORTH && canPlayerMove(getX(),getY()+card.action.getAction())) {
                setYPos(getY()+card.action.getAction());
            } else if (player.direction == Direction.SOUTH && canPlayerMove(getX(),getY()-card.action.getAction())) {
                setYPos(getY()-card.action.getAction());
            } else if (player.direction == Direction.EAST && canPlayerMove(getX()+card.action.getAction(),getY())) {
                setXPos(getX()+card.action.getAction());
            } else if (player.direction == Direction.WEST && canPlayerMove(getX()-card.action.getAction(),getY())) {
                setXPos(getX()-card.action.getAction());
            }
        } else if (!movePlayerCard(card)) {
            setDirection(player, card);
        }
    }

    @Override
    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(xPosition), normalizedCoordinates(yPosition));
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
        int tileDirection=300;
        if(keyPressed==Input.Keys.LEFT){
            newXPosition = xPosition - tileDirection;
        }
        else if(keyPressed==Input.Keys.RIGHT){
            newXPosition = xPosition + tileDirection;
        }
        else if(keyPressed==Input.Keys.UP){
            setYPos(yPosition + tileDirection);
        }
        else if(keyPressed==Input.Keys.DOWN){
            setYPos(yPosition - tileDirection);
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

        return true;
    }


    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    public float cardPosX(){
        return cardXPos;
    }
    public float cardPosY(){
        return cardXPos;
    }


    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        float x  = Gdx.input.getX();
        float y = Gdx.input.getY();
        System.out.println("Y" + Gdx.input.getY());
        System.out.println("X" + Gdx.input.getX());
        if (x >609 && x < 678 && y > 869 && y < 957){
            cardXPos = 3900;
            cardYPos = 3900;
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
