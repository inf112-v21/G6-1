package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;


public class Graphics extends ScreenAdapter implements ApplicationListener{
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public HumanPlayer humanPlayer;

    public Texture background;
    public Texture youwin;

    public Sprite moveOneSprite;
    public Sprite moveTwoSprite;
    public Sprite moveThreeSprite;
    public Sprite rotateLeftSprite;
    public Sprite rotateRightSprite;
    public Sprite backUpSprite;
    public Sprite uTurnSprite;
    public Sprite player;

    /**
     * test for LHP
     */
    public void cardSize(Sprite sprite){
        sprite.setSize(400,550);

    }

    @Override
    public void create() {
        float w = 600;
        float h = 1000;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h,w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        humanPlayer = new HumanPlayer((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"), Direction.NORTH, " Erlend");
        player = new Sprite(new Texture(("Player/OwlPlayer1.png")));
        player.setSize(300,300);
        Gdx.input.setInputProcessor((InputProcessor) humanPlayer);
        player.setPosition(humanPlayer.setPlayerStartPositionXDirection(0) , humanPlayer.setPlayerStartPositionYCoordinate(0) );
        moveOneSprite = new Sprite(new Texture("Cards/Move1.png"));
        moveTwoSprite = new Sprite(new Texture("Cards/Move2.png"));
        //CardSizeAndPosition(CardMoveTwoSprite,600);
        moveThreeSprite = new Sprite(new Texture("Cards/Move3.png"));
        //CardSizeAndPosition(CardMoveThreeSprite,1200);
        rotateLeftSprite = new Sprite(new Texture("Cards/RotateLeft.png"));
        //CardSizeAndPosition(CardRotateLeftSprite,1800);
        rotateRightSprite = new Sprite(new Texture("Cards/RotateRight.png"));
        //CardSizeAndPosition(CardRotateRightSprite,2400);
        backUpSprite = new Sprite(new Texture("Cards/BackUp.png"));
        //CardSizeAndPosition(CardBackUpSprite,3000);
        uTurnSprite = new Sprite(new Texture("Cards/U-turn.png"));
        //CardSizeAndPosition(CardUTurnSprite,3600);

        background = new Texture("Background2.png");
        youwin = new Texture("YouWin.jpg");
    }

    public void dealHand() {
        int cardYPos = -600;
        ArrayList<Card> playerCardDeck;
        playerCardDeck = humanPlayer.playerCardDeck;
        ArrayList<Card> playerRoundCard = new ArrayList<>();
        playerRoundCard.add(playerCardDeck.get(0));
        if (playerCardDeck.size() >=4 && !playerRoundCard.isEmpty()) {
            int cardXPos = 0;
            for (int i = 0; i < 9; i++) {

                Card playerCard = playerCardDeck.get(i);
                //System.out.println(playerCard.action);
                tiledMapRenderer.getBatch().begin();
                if (playerCard.action == Action.MOVE_ONE) {
                    moveOneSprite.setPosition(cardXPos, cardYPos);
                    moveOneSprite.setSize(400, 550);
                    moveOneSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.MOVE_TWO) {
                    moveTwoSprite.setPosition(cardXPos, cardYPos);
                    moveTwoSprite.setSize(400, 550);
                    moveTwoSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.MOVE_THREE) {
                    moveThreeSprite.setPosition(cardXPos, cardYPos);
                    moveOneSprite.setSize(400, 550);
                    moveThreeSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.ROTATE_LEFT) {
                    rotateLeftSprite.setPosition(cardXPos, cardYPos);
                    rotateLeftSprite.setSize(400, 550);
                    rotateLeftSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.ROTATE_RIGHT) {
                    rotateRightSprite.setPosition(cardXPos, cardYPos);
                    rotateRightSprite.setSize(400, 550);
                    rotateRightSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.U_TURN) {
                    uTurnSprite.setPosition(cardXPos, cardYPos);
                    uTurnSprite.setSize(400, 550);
                    uTurnSprite.draw(tiledMapRenderer.getBatch());
                }
                if (playerCard.action == Action.BACK_UP) {
                    backUpSprite.setPosition(cardXPos, cardYPos);
                    backUpSprite.setSize(400, 550);
                    backUpSprite.draw(tiledMapRenderer.getBatch());
                }
                tiledMapRenderer.getBatch().end();
                cardXPos += 450;
            }
        }
    }



    /**
     * Displayed on the screen.
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    /**
     * This is where the graphics of the game get rendered.
     */
    @Override
    public void render() {
        //background image
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1500,800);
        //System.out.println(Gdx.graphics.getWidth() + " " +  Gdx.graphics.getHeight());
        spriteBatch.end();
        dealHand();
        //player on display
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        tiledMapRenderer.getBatch().begin();
        player.setPosition(humanPlayer.getX(), humanPlayer.getY());
        player.draw(tiledMapRenderer.getBatch());
        //CardMoveOneSprite.draw(tiledMapRenderer.getBatch());
        //CardMoveOneSprite.setPosition(localHumanPlayer.cardXPos,localHumanPlayer.cardYPos);
        //CardMoveTwoSprite.draw(tiledMapRenderer.getBatch());
        //CardMoveThreeSprite.draw(tiledMapRenderer.getBatch());
        //CardRotateLeftSprite.draw(tiledMapRenderer.getBatch());
        //CardRotateRightSprite.draw(tiledMapRenderer.getBatch());
        //CardUTurnSprite.draw(tiledMapRenderer.getBatch());
        //CardBackUpSprite.draw(tiledMapRenderer.getBatch());
        //humanPlayer.round(humanPlayer);

        tiledMapRenderer.getBatch().end();

        //if the player has won, get "you win"-message up
        if (humanPlayer.isGameOver(humanPlayer.flagLayer)) {
            pause();
            System.out.println("You Won!");
            //"you win" screen pops up
            spriteBatch.begin();
            spriteBatch.draw(youwin, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            spriteBatch.end();
            pause();
            //use timer
            //dispose(); //maybe get "you win" message up before it disposes so quickly
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    /**
     * Disposes the window mode.
     */
    @Override
    public void dispose() {
        tiledMap.dispose();
        spriteBatch.dispose();
        background.dispose();
        youwin.dispose();
    }
}