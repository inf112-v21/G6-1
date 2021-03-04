package inf112.skeleton.app.graphics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphics extends ScreenAdapter implements ApplicationListener{
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public PlayerGraphics playerGraphics;
    public CardGraphics cardGraphics;
    //public HumanPlayer humanPlayer;
    public HumanPlayer playerOne;
    public HumanPlayer playerTwo;
    public HumanPlayer playerThree;
    public HumanPlayer playerFour;
    public HumanPlayer playerFive;
    {
        playerGraphics = new PlayerGraphics();
        cardGraphics = new CardGraphics();
    }
    public HashMap<Action, Texture> getCardTexture;
    public Texture background;
    public Texture youWin;
    public Texture startTexture;
    public Sprite playerOneSprite;
    public Sprite playerTwoSprite;
    public ArrayList<Sprite> cardSpriteList;
    private CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public testClass testClass = new testClass();;


    /**
     * test for LHP
     */



    public void updateCardSprite(ArrayList<Sprite> cardSprite, Player humanPlayer){
        int cardNumber = 0;
        int cardcorX = 0;
        int cardcorY = 1;
        for (Sprite card2: cardSpriteList){
            card2.setSize(455, 650);
            card2.setPosition(humanPlayer.cardCoordinates.get(cardcorX),humanPlayer.cardCoordinates.get(cardcorY));
            card2.setTexture(getCardTexture.get(humanPlayer.playerDeck.get(cardNumber).action));
            card2.draw(tiledMapRenderer.getBatch());
            cardNumber++;
            cardcorX +=2;
            cardcorY += 2;
        }
    }

    public void setUpPlayer(HumanPlayer humanPlayer){

        playerOneSprite = new Sprite((playerGraphics.getPlayerTextures().get(humanPlayer.color).get(humanPlayer.direction)));
        playerOneSprite.setSize(300,300);


    }
    @Override
    public void create() {


        playerOne = testClass.createhuman().get(0);
        playerOneSprite = new Sprite((playerGraphics.getPlayerTextures().get(playerOne.color).get(playerOne.direction)));
        playerOneSprite.setSize(300,300);

        playerTwo = testClass.createhuman().get(1);
        playerTwoSprite = new Sprite((playerGraphics.getPlayerTextures().get(playerTwo.color).get(playerTwo.direction)));
        playerTwoSprite.setSize(300,300);


        float w = 600;
        float h = 1000;
        cardSpriteList = cardGraphics.createCardSprite(playerOne);
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h, w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        startTexture = new Texture("Player/OwlPlayer.png");
        getCardTexture = cardGraphics.getCardTexture();

        Gdx.input.setInputProcessor(playerTwo);
        Gdx.input.setInputProcessor(playerOne);




        // player.setPosition(humanPlayer.setPlayerStartXPosition(0) , humanPlayer.setPlayerStartYPosition(0));
        //createCardSprite();

        background = new Texture("Background.png");
        youWin = new Texture("YouWin.jpg");
    }

    /**
     * Displayed on the screen.
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 1280;
        camera.viewportHeight = 720;
        camera.update();
    }

    /**
     * This is where the graphics of the game get rendered.
     */
    @Override
    public void render() {
        //background image
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1280, 720);
        spriteBatch.end();
        //player on display
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        playerOne.setMouseClickCoordinates(camera);


        tiledMapRenderer.getBatch().begin();
        updateCardSprite(cardSpriteList, playerOne);


        playerOneSprite.setPosition(playerOne.getPlayerXPosition(), playerOne.getPlayerYPosition());
        playerOneSprite.draw(tiledMapRenderer.getBatch());
        playerOneSprite.setTexture(playerGraphics.getPlayerTextures().get(playerOne.color).get(playerOne.direction)); //greenPiece.get(humanPlayer.direction));

        playerTwoSprite.setPosition(playerTwo.getPlayerXPosition(), playerTwo.getPlayerYPosition());
        playerTwoSprite.draw(tiledMapRenderer.getBatch());
        playerTwoSprite.setTexture(playerGraphics.getPlayerTextures().get(playerTwo.color).get(playerTwo.direction)); //greenPiece.get(humanPlayer.direction));
        playerOne.round();
        tiledMapRenderer.getBatch().end();

            //if the player has won, get "you win"-message up
            if (playerOne.isPlayerOnFlag((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"))) {
                pause();
                System.out.println("You Won!");
                //"you win" screen pops up
                spriteBatch.begin();
                spriteBatch.draw(youWin, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                spriteBatch.end();
                pause();
                //use timer
                dispose(); //maybe get "you win" message up before it disposes so quickly
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
        youWin.dispose();
    }
}