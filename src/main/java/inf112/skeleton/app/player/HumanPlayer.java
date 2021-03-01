package inf112.skeleton.app.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;
import java.util.ArrayList;
import java.util.Arrays;


public class HumanPlayer extends Player implements InputProcessor {


    public HumanPlayer(Direction direction, String name) {

        super(direction, name);
    }
    private int dummyPlayerDeck = 9;
    private CardDeck cardDeck = new CardDeck();
    private ArrayList<Card> chosenCards = new ArrayList<>();

    public float playerCurrentXPosition;
    public float playerCurrentYPosition;
    public ArrayList<Card> playerDeck = cardDeck.dealNineCards();
    // Coordinates for cards. even = x odd = y
    public ArrayList<Float> cardCoordinates = new ArrayList<Float>(
            Arrays.asList(0f,-600f,
                        500f,-600f,
                        1000f,-600f,
                        1500f,-600f,
                        2000f,-600f,
                        2500f,-600f,
                        3000f,-600f,
                        3500f,-600f,
                        4000f,-600f));



    /**
     * Needed to mach Sprite positions with back-end
     * @param playerStartXPosition
     * @return
     */
    public float setPlayerStartXPosition(float playerStartXPosition){
        playerCurrentXPosition = playerStartXPosition;
        return playerCurrentXPosition;
    }
    /**
     *
     * Needed to mach Sprite positions with back-end
     * @param playerStartYPosition
     * @return
     */
    public float setPlayerStartYPosition(float playerStartYPosition){
        playerCurrentYPosition = playerStartYPosition;
        return playerCurrentYPosition;
    }

    /**
     * set x position
     * @return
     */
    public float updatePlayerXPosition(float newXPosition){
        return playerCurrentXPosition = newXPosition;
    }

    /**
     * set y position
     * @return
     */
    public float updatePlayerYPosition(float newYPosition){
        return playerCurrentYPosition = newYPosition;
    }

    /**
     *
     * @return x positon of player : float
     */
    public float getPlayerXPosition() {
        return playerCurrentXPosition;
    }

    /**
     *
     * @return y position of player : float
     */
    public float getPlayerYPosition() {
        return playerCurrentYPosition;
    }

    /**
     *  Checks if a card is a card that changes the position of a player
     *  and not the direction
     * @param card : Card of a player
     * @return
     */
    public boolean moveTypeCard(Card card){
        return card.action == Action.MOVE_ONE || card.action == Action.MOVE_TWO|| card.action == Action.MOVE_THREE||card.action== Action.BACK_UP;
    }

     //TODO dummy method to do rounds without priority used only for development. DELETE BEFORE DELIVERY
    public void round(HumanPlayer player){
        //System.out.println("NEW ROUND");
        if(dummyPlayerDeck ==4 && chosenCards.size()==5){
            updatePlayerLocation(player,chosenCards.get(0));
            updatePlayerLocation(player,chosenCards.get(1));
            updatePlayerLocation(player,chosenCards.get(2));
            updatePlayerLocation(player,chosenCards.get(3));
            updatePlayerLocation(player,chosenCards.get(4));
            dummyPlayerDeck = 9;
            chosenCards = new ArrayList<>();
            playerDeck = new ArrayList<>();
            playerDeck = cardDeck.dealNineCards();
            cardCoordinates =new ArrayList<Float>(
                    Arrays.asList(0f,-600f,
                            500f,-600f,
                            1000f,-600f,
                            1500f,-600f,
                            2000f,-600f,
                            2500f,-600f,
                            3000f,-600f,
                            3500f,-600f,
                            4000f,-600f));
        }
        /*
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
        }*/
    }

    /**
     * sets new player direction from player cards
     * @param player
     * @param cardDirection
     */
    public void setPlayerDirection(HumanPlayer player, Card cardDirection){
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
        if (moveTypeCard(card)) {
            if (player.direction == Direction.NORTH && canPlayerMove(getPlayerXPosition(), getPlayerYPosition()+card.action.getAction())) {
                updatePlayerYPosition(getPlayerYPosition()+card.action.getAction());
            } else if (player.direction == Direction.SOUTH && canPlayerMove(getPlayerXPosition(), getPlayerYPosition()-card.action.getAction())) {
                updatePlayerYPosition(getPlayerYPosition()-card.action.getAction());
            } else if (player.direction == Direction.EAST && canPlayerMove(getPlayerXPosition()+card.action.getAction(), getPlayerYPosition())) {
                updatePlayerXPosition(getPlayerXPosition()+card.action.getAction());
            } else if (player.direction == Direction.WEST && canPlayerMove(getPlayerXPosition()-card.action.getAction(), getPlayerYPosition())) {
                updatePlayerXPosition(getPlayerXPosition()-card.action.getAction());
            }
        } else if (!moveTypeCard(card)) {
            setPlayerDirection(player, card);
        }
    }

    /**
     * Sets new position for the card a player choose
     * @param cardXPositionIndex
     * @param cardYPositionIndex
     */
    public void setNewCardPos(int cardXPositionIndex, int cardYPositionIndex){
        int chosenCardListSize = chosenCards.size();
        if (chosenCardListSize < 6){
            cardCoordinates.set(cardXPositionIndex, 3900f);
            if (chosenCardListSize == 1) cardCoordinates.set(cardYPositionIndex, 3900f);
            if (chosenCardListSize == 2) cardCoordinates.set(cardYPositionIndex, 3300f);
            if (chosenCardListSize == 3) cardCoordinates.set(cardYPositionIndex, 2700f);
            if (chosenCardListSize == 4) cardCoordinates.set(cardYPositionIndex, 2100f);
            if (chosenCardListSize == 5) cardCoordinates.set(cardYPositionIndex, 1500f);
        }else{
            cardCoordinates.set(cardXPositionIndex,cardCoordinates.get(cardXPositionIndex));
            cardCoordinates.set(cardYPositionIndex,cardCoordinates.get(cardYPositionIndex));
        }
    }

    @Override
    public boolean isPlayerOnFlag(TiledMapTileLayer flagLayer) {
        TiledMapTileLayer.Cell cell = flagLayer.getCell(normalizedCoordinates(playerCurrentXPosition), normalizedCoordinates(playerCurrentYPosition));
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
                updatePlayerXPosition(playerCurrentXPosition - tileDirection);
            } else if (keyPressed == Input.Keys.RIGHT) {
                updatePlayerXPosition(playerCurrentXPosition + tileDirection);
            } else if (keyPressed == Input.Keys.UP) {
                updatePlayerYPosition(playerCurrentYPosition + tileDirection);
            } else if (keyPressed == Input.Keys.DOWN) {
                updatePlayerYPosition(playerCurrentYPosition - tileDirection);
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

    //TODO need to be able to undo choice of card
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        float x  = Gdx.input.getX();
        float y = Gdx.input.getY();
        System.out.println("Y" + Gdx.input.getY());
        System.out.println("X" + Gdx.input.getX());
        if (x >460 && x < 515 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(0));
            dummyPlayerDeck -=1;
            setNewCardPos(0,1);
        }
        else if(x >530 && x < 590 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(1));
            dummyPlayerDeck -= 1;
            setNewCardPos(2,3);
        }
        else if(x >605 && x < 660 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(2));
            dummyPlayerDeck -= 1;
            setNewCardPos(4,5);
        }
        else if(x >675 && x < 730 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(3));
            dummyPlayerDeck -=1;
            setNewCardPos(6,7);
        }
        else if(x >745 && x < 800 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(4));
            dummyPlayerDeck -=1;
            setNewCardPos(8,9);
        }
        else if(x >815 && x < 875 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(5));
            dummyPlayerDeck -= 1 ;
            setNewCardPos(10,11);
        }
        else if(x >890 && x < 945 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(6));
            dummyPlayerDeck -= 1 ;
            setNewCardPos(12,13);
        }
        else if(x >960 && x < 1015 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(7));
            dummyPlayerDeck -= 1 ;
            setNewCardPos(14,15);
        }
        else if(x >1030 && x < 1090 && y > 810 && y < 895){
            chosenCards.add(playerDeck.get(8));
            dummyPlayerDeck -= 1 ;
            setNewCardPos(16,17);
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
