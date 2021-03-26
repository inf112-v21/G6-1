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
import inf112.skeleton.app.game.GameType;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphics implements ApplicationListener {
    public TiledMap tiledMap;

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public Texture background;
    public Texture youWin;
    public Texture reset, notReset;
    public Texture ready, notReady;
    public Texture damagetoken;
    public Texture lifetoken;
    public Texture menuScreenBackground;
    public Texture singlePlayerButton;
    public Texture joinMultiPlayerButton;
    public Texture hostMultiPlayerButton;
    public PlayerGraphics playerGraphics;
    public CardGraphics cardGraphics;
    public HumanPlayer singlePlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    public Sprite singlePlayerSprite;
    public ArrayList<Sprite> cardSpriteList;
    private CardMoveLogic cardMoveLogic = new CardMoveLogic();
    private HashMap<Action, Texture> cardTextures = new HashMap<>();
    public Game game;
    public ArrayList<Player> singelPlayerList =new ArrayList<>();
    public Graphics(Game game) {
        // menuInputProcessor = new MenuInputProcessor(this);
        cardGraphics = new CardGraphics();
        this.game = game;
    }

    public void updateCardSprite(Player humanPlayer) {
        int cardNumber = 0;
        int cardCoordinateX = 0;
        int cardCoordinateY = 1;
        for (Sprite card: cardSpriteList) {
            card.setSize(455, 650);
            card.setPosition(humanPlayer.cardCoordinates.get(cardCoordinateX),humanPlayer.cardCoordinates.get(cardCoordinateY));
            card.setTexture(cardTextures.get(humanPlayer.playerDeck.get(cardNumber).action));
            card.draw(tiledMapRenderer.getBatch());
            cardNumber++;
            cardCoordinateX += 2;
            cardCoordinateY += 2;
        }
    }


    public void setInputProcessor(Player player){
        Gdx.input.setInputProcessor((InputProcessor) player);
    }


    public void updatePlayerSprite(ArrayList<Player> players){
        if (players == null || players.isEmpty()) {
            // No players created yet, don't render any
            return;
        }
        for (Player player : players){
            player.setMouseClickCoordinates(camera);
            setInputProcessor(player);
            updateCardSprite(player);
            /*
            System.out.println("Player of color " +player.color);
            System.out.println("Start");
            for(Card playerdeckcard : player.playerDeck){
                System.out.println(playerdeckcard.action);
            }
            System.out.println("chosenCard");
            for(Card playerchosencard : player.chosenCards){
                System.out.println(playerchosencard.action);
            }*/

            Sprite playerSprite = playerGraphics.getPlayerSprite(player);
            Texture playerTexture = playerGraphics.getPlayerTexture(player);
            playerSprite.setTexture(playerTexture);

            playerSprite.setSize(300,300);
            playerSprite.setPosition(player.getPlayerXPosition(), player.getPlayerYPosition());
            playerSprite.draw(tiledMapRenderer.getBatch());
        }
    }

    /**
     * temp runs a single-player gui
     */
    public void singlePlayer(){
        singlePlayer.setMouseClickCoordinates(camera);
        updateCardSprite(singlePlayer);

        singlePlayerSprite.setPosition(singlePlayer.getPlayerXPosition(), singlePlayer.getPlayerYPosition());
        Texture playerTexture = playerGraphics.getPlayerTexture(singlePlayer);
        singlePlayerSprite.setTexture(playerTexture);
        singlePlayerSprite.draw(tiledMapRenderer.getBatch());
        singlePlayer.singlePlayerRound(singelPlayerList,
                (TiledMapTileLayer) tiledMap.getLayers().get("Laser"),
                (TiledMapTileLayer) tiledMap.getLayers().get("BlueConveyor"),
                (TiledMapTileLayer) tiledMap.getLayers().get("YellowConveyor"),
                (TiledMapTileLayer) tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) tiledMap.getLayers().get("GreenGear"),
                (TiledMapTileLayer) tiledMap.getLayers().get("Hole"));

    }

    @Override
    public void create() {
        playerGraphics = new PlayerGraphics();

        singlePlayer.playerDeck = cardMoveLogic.playerDeck();

        singlePlayerSprite = playerGraphics.getPlayerSprite(singlePlayer.color);
        singlePlayerSprite.setSize(300,300);

        // Creates a list of sprites
        cardSpriteList = cardGraphics.createCardSprite();
        cardTextures = cardGraphics.createCardTexture();

        singelPlayerList.add(singlePlayer);
        float w = 600;
        float h = 1000;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h, w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        background = new Texture("Background.png");
        youWin = new Texture("YouWin.jpg");

        //reset and ready textures
        reset = new Texture("Buttons/RESET.png");
        notReset = new Texture("Buttons/notRESET.png");
        ready = new Texture("Buttons/READY.png");
        notReady = new Texture("Buttons/notREADY.png");

        damagetoken = new Texture("emptyDamageToken.png");
        lifetoken = new Texture("LifeToken.png");

        // Menu Textures
        menuScreenBackground = new Texture("MenuBackground.png"); // TODO actual textures
        singlePlayerButton = new Texture("Buttons/READY.png");
        joinMultiPlayerButton = new Texture("Buttons/RESET.png");
        hostMultiPlayerButton = new Texture("Buttons/RESET.png");
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

        //shows when to click the buttons and when not to click
        readyButtonIndicator();
        resetButtonIndicator();

        spriteBatch.begin();
        spriteBatch.draw(damagetoken, 830, 400, 50, 27);
        spriteBatch.draw(damagetoken, 800, 400, 50, 27);
        spriteBatch.draw(damagetoken, 770, 400, 50, 27);
        spriteBatch.draw(damagetoken, 740, 400, 50, 27);
        spriteBatch.draw(damagetoken, 710, 400, 50, 27);
        spriteBatch.draw(damagetoken, 830, 350, 50, 27);
        spriteBatch.draw(damagetoken, 800, 350, 50, 27);
        spriteBatch.draw(damagetoken, 770, 350, 50, 27);
        spriteBatch.draw(damagetoken, 740, 350, 50, 27);
        spriteBatch.draw(damagetoken, 710, 350, 50, 27);
        spriteBatch.end();

        spriteBatch.begin();
        spriteBatch.draw(lifetoken, 800, 535, 75, 40);
        spriteBatch.draw(lifetoken, 760, 535, 75, 40);
        spriteBatch.draw(lifetoken, 720, 535, 75, 40);
        spriteBatch.end();

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        if(game.typeOfGameStarted == GameType.SINGLE_PLAYER){
            Gdx.input.setInputProcessor((InputProcessor) singlePlayer);
            singlePlayer();

        } else{
            updatePlayerSprite(game.players);
        }
        tiledMapRenderer.getBatch().end();

        if (singlePlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"))) {
            pause();
            System.out.println("You Won!");
            spriteBatch.begin();
            spriteBatch.draw(youWin, 0, 0, 1280, 720);
            spriteBatch.end();
            pause();
            dispose();
        }
    }

    public void readyButtonIndicator(){
        if(singlePlayer.movedCards.size() >= 5){
            spriteBatch.begin();
            spriteBatch.draw(ready, 1053, 175, 125, 55);
            spriteBatch.end();
        } else if(singlePlayer.movedCards.size() < 5){
            spriteBatch.begin();
            spriteBatch.draw(notReady, 1053, 175, 125, 55);
            spriteBatch.end();
        }
    }

    public void resetButtonIndicator(){
        if(singlePlayer.movedCards.size() > 0){
            spriteBatch.begin();
            spriteBatch.draw(reset, 1053, 129, 125, 55);
            spriteBatch.end();
        } else if(singlePlayer.movedCards.size() <= 4){
            spriteBatch.begin();
            spriteBatch.draw(notReset, 1053, 129, 125, 55);
            spriteBatch.end();
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