package inf112.skeleton.app.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.shared.Direction;


public class LocalHumanPlayer extends Player implements InputProcessor {

    public LocalHumanPlayer(TiledMapTileLayer flagLayer, Direction direction) {
        super(flagLayer, direction);
    }
    public float xPosition;
    public float yPosition;
    public float newXPosition;
    public float newYPosition;

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
    public float setXPos(){
        if(canPlayerMove(newXPosition,newYPosition)) xPosition = newXPosition;
        return xPosition;
    }

    /**
     * set y position
     * @return
     */
    public float setYPos(){
        if(canPlayerMove(newXPosition,newYPosition)) yPosition = newYPosition;
        return yPosition;
    }

    @Override
    public void updatePlayerLocation(float updateX, float updateY) {

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
        return 0;
    }

    @Override
    public boolean isGameOver(TiledMapTileLayer flagLayer) {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }

    /*
        public float updateX;
        public float updateY;
        public Graphics graphics;

        private CardDeck cardDeck = new CardDeck();
        public ArrayList<Card> card = cardDeck.getDeck();

        public LocalHumanPlayer(Sprite sprite, TiledMapTileLayer flagLayer, Direction direction) {
            super(sprite, flagLayer, direction);
        }


        @Override
        public void draw(Batch batch) {
            updatePlayerLocation(updateX, updateY);
            //if(isPlayerOnFlag()) System.out.println("VICTORY!");
            super.draw(batch);
        }


        //TODO denne fjernes før innlevering da vi nå skal bruke kort og ikke innput
        @Override
         public void updatePlayerLocation(float updateX, float updateY) {
       //     if (canPlayerMove(updateX, updateY)){
         //       this.setPosition(updateX, updateY);
          //  }
            for(Card card : cardDeck.getDeck()){

               // System.out.println(card.action +""+card.priority);

            }

            this.setPosition(cardDeck.getCard().action.getAction(), getY());
        }


        public boolean isItmoveCard(Card card){
            return card.action == Action.MOVE_ONE || card.action == Action.MOVE_TWO|| card.action == Action.MOVE_THREE||card.action== Action.BACK_UP;
        }
        public void updatePlayerLocationCard(Card card, Player player) {
            if (isItmoveCard(card)) {
                if (player.direction == Direction.NORTH && canPlayerMove(getX(), card.action.getAction())) {
                    this.setPosition(getX(), card.action.getAction());
                } else if (player.direction == Direction.SOUTH) {
                    this.setPosition(getX(), -card.action.getAction());
                } else if (player.direction == Direction.EAST) {
                    this.setPosition(card.action.getAction(), getY());
                } else if (player.direction == Direction.WEST) {
                    this.setPosition(card.action.getAction(), getY());
                }
            }else if(!isItmoveCard(card)) {
                if(card.action == Action.U_TURN){
                    if(player.direction == Direction.WEST){
                        player.direction = Direction.EAST;
                    }else if(player.direction == Direction.EAST){
                        player.direction = Direction.WEST;
                    }else if (player.direction == Direction.NORTH){
                        player.direction = Direction.SOUTH;
                    }else if (player.direction == Direction.SOUTH){
                        player.direction = Direction.NORTH;
                    }
                }

                }
            else {
                this.setPosition(getX(),getY());
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

    @Override
    public boolean move() {

        return true;
    }
*/

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
            newYPosition = yPosition + tileDirection;
        }
        else if(keyPressed==Input.Keys.DOWN){
            newYPosition = yPosition - tileDirection;
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
