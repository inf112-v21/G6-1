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
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphics  implements ApplicationListener{
    public TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public PlayerGraphics playerGraphics;
    public CardGraphics cardGraphics;
    public Player playerOne = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    public Game game;
    public HashMap<Action, Texture> getCardTexture;
    public Texture background;
    public Texture youWin;
    public Texture startTexture;
    public Sprite playerOneSprite;
    public ArrayList<Sprite> cardSpriteList;
    private CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public testClass testClass = new testClass();
    public HashMap<Color, Sprite> playersSprite;
    public ArrayList<Player> testPlayerList;
    public  HashMap<Color, Sprite> playerSprite;



    public Graphics(Game game) {
        playerGraphics = new PlayerGraphics();
        cardGraphics = new CardGraphics();
        this.game = game;
    }

    public void updateCardSprite(Player humanPlayer){
        int cardNumber = 0;
        int cardCoordinateX = 0;
        int cardCoordinateY = 1;
        for (Sprite card: cardSpriteList){
            card.setSize(455, 650);
            card.setPosition(humanPlayer.cardCoordinates.get(cardCoordinateX),humanPlayer.cardCoordinates.get(cardCoordinateY));
            card.setTexture(cardGraphics.getCardTexture().get(humanPlayer.playerDeck.get(cardNumber).action));
            card.draw(tiledMapRenderer.getBatch());
            cardNumber++;
            cardCoordinateX +=2;
            cardCoordinateY += 2;
        }
    }

    public HashMap<Color, Sprite> getPlayerSprite(){
        HashMap<Color,Sprite> playerSprite = new HashMap<>();
        playerSprite.put(Color.ORANGE,new Sprite(playerGraphics.getPlayerTextures().get(Color.ORANGE).get(Direction.NORTH)));
        playerSprite.put(Color.GREEN,new Sprite((playerGraphics.getPlayerTextures().get(Color.GREEN).get(Direction.NORTH))));
        playerSprite.put(Color.PURPLE,new Sprite((playerGraphics.getPlayerTextures().get(Color.PURPLE).get(Direction.NORTH))));
        playerSprite.put(Color.PINK,new Sprite((playerGraphics.getPlayerTextures().get(Color.PINK).get(Direction.NORTH))));
        playerSprite.put(Color.GREY,new Sprite((playerGraphics.getPlayerTextures().get(Color.GREY).get(Direction.NORTH))));
        return playerSprite;
    }

    public void setInputProcessor(Player player){
        Gdx.input.setInputProcessor((InputProcessor) player);
    }

    public void playFunctions(Player player){
        player.setMouseClickCoordinates(camera);
    }

    public void updatePlayerSprite(ArrayList<Player> players){
        if (players == null || players.isEmpty()) {
            // No players created yet, don't render any
            return;
        }

        HashMap<Color, Sprite> playersSprite = getPlayerSprite();
        for (Player player : players){
            playFunctions(player);
            setInputProcessor(player);
            updateCardSprite(testPlayerList.get(0));
            Sprite playerSprite = playersSprite.get(player.color);
            playerSprite.setTexture(playerGraphics.getPlayerTextures().get(player.color).get(player.direction));
            playerSprite.setSize(300,300);
            playerSprite.setPosition(player.getPlayerXPosition(), player.getPlayerYPosition());
            playerSprite.draw(tiledMapRenderer.getBatch());
        }
    }

    @Override
    public void create() {
        //TODO create start position for all players

        playerOne.playerDeck = cardMoveLogic.playerDeck();
        playerOneSprite = new Sprite((playerGraphics.getPlayerTextures().get(playerOne.color).get(playerOne.direction)));
        playerOneSprite.setSize(300,300);
        playerSprite = getPlayerSprite();

        float w = 600;
        float h = 1000;
        cardSpriteList = cardGraphics.createCardSprite();
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h, w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        startTexture = new Texture("Player/OwlPlayer.png");
        playersSprite = getPlayerSprite();
        background = new Texture("Background.png");
        youWin = new Texture("YouWin.jpg");
        testPlayerList =testClass.createhuman();
    }

    public void singlePlayer(){
        playerOne.setMouseClickCoordinates(camera);
        updateCardSprite(playerOne);
        playerOneSprite.setPosition(playerOne.getPlayerXPosition(), playerOne.getPlayerYPosition());
        playerOneSprite.setTexture(playerGraphics.getPlayerTextures().get(playerOne.color).get(playerOne.direction)); //greenPiece.get(humanPlayer.direction))
        playerOneSprite.draw(tiledMapRenderer.getBatch());
        playerOne.round();

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


        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1280, 720);
        spriteBatch.end();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        Gdx.input.setInputProcessor((InputProcessor) playerOne);

        tiledMapRenderer.getBatch().begin();
        if(game.typeOfGameStarted == "single player"){
            singlePlayer();
        } else{
            updatePlayerSprite(game.players);
        }
        tiledMapRenderer.getBatch().end();

        if (playerOne.isPlayerOnFlag((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"))) {
            pause();
            System.out.println("You Won!");
            spriteBatch.begin();
            spriteBatch.draw(youWin, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            spriteBatch.end();
            pause();
            dispose();
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