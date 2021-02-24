package inf112.skeleton.app.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;


public class LocalHumanPlayer extends Player implements InputProcessor {
    public float updateX;
    public float updateY;

    private CardDeck cardDeck = new CardDeck();
    public ArrayList<Card> deck;

    public LocalHumanPlayer(Sprite sprite, TiledMapTileLayer flagLayer, Direction direction) {
        super(sprite, flagLayer, direction);
    }

    /**
     * Update and draw Player SpriteBatch
     *
     * @param batch : Player
     */
    @Override
    public void draw(Batch batch) {
        updatePlayerLocation(updateX, updateY);
        //if(isPlayerOnFlag()) System.out.println("VICTORY!");
        super.draw(batch);
    }

    //TODO denne fjernes før innlevering da vi nå skal bruke kort og ikke innput
    @Override
     public void updatePlayerLocation(float updateX, float updateY) {
        /*if (canPlayerMove(updateX, updateY)){
            this.setPosition(updateX, updateY);
        }*/
        for(Card card : cardDeck.getDeck()){
            System.out.println(card.action +""+card.priority);
        }
        System.out.println();
        this.setPosition(cardDeck.getCard().action.getAction(), getY());
    }


    public boolean isItmoveCard(Card card){
        return card.action == Action.MOVE_ONE || card.action == Action.MOVE_TWO|| card.action == Action.MOVE_THREE||card.action== Action.BACK_UP;
    }
    public void updatePlayerLocationCard(Card card, Player player) {
        if (isItmoveCard(card)) {
            if (player.direction == Direction.NORTH) {
                this.setPosition(getX(), card.action.getAction());
            } else if (player.direction == Direction.SOUTH) {
                this.setPosition(getX(), -card.action.getAction());
            } else if (player.direction == Direction.EAST) {
                this.setPosition(card.action.getAction(), getY());
            } else if (player.direction == Direction.WEST) {
                this.setPosition(card.action.getAction(), getY());
            } else if(!isItmoveCard(card)) {

            }
        }
    }

/*
        moveCard.action;
        player.direction;
        card.action;
        if direction == noe
                move direction;
        if action feks turn;
        player.direction = new dirction;
    }
*/
    @Override
    public void setPlayerSize(float width, float height) {
        setSize(width, height);
    }

    @Override
    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(getX()), normalizedCoordinates(getY()));
        return cell!= null;
    }

    @Override
    public boolean canPlayerMove(float xDirection, float yDirection){
        return !(xDirection < 0 || xDirection > 3300 || yDirection < 0 || yDirection > 3900);
    }

    @Override
    public int normalizedCoordinates(float unNormalizedValue) {
        return (int) unNormalizedValue/300;
    }

    @Override
    public boolean isGameOver(TiledMapTileLayer flagLayer) {
        if (isPlayerOnFlag(flagLayer)) {
            return true;
        } else {
            return false;
        }
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
        if(keyPressed == Input.Keys.LEFT){
            updateX = getX() - tileDirection;
        }
        else if(keyPressed == Input.Keys.RIGHT){
            updateX = getX() + tileDirection;
        }
        else if(keyPressed == Input.Keys.UP){
            updateY = getY() + tileDirection;
        }
        else if(keyPressed == Input.Keys.DOWN){
            updateY = getY() - tileDirection;
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
        if(keyPressed == Input.Keys.LEFT || keyPressed == Input.Keys.RIGHT) {
            updateX = getX();
        }
        else if(keyPressed == Input.Keys.UP || keyPressed == Input.Keys.DOWN) {
            updateY = getY();
        }
        return true;
    }


    @Override
    public boolean keyTyped(char c) {
        return false;
    }


    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
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
