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
import inf112.skeleton.app.game.GameScreen;
import inf112.skeleton.app.game.GameType;
import inf112.skeleton.app.game.MenuInputProcessor;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphics implements ApplicationListener {
    public TiledMap tiledMap;
    public TileLayers layer;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public Texture background;
    public Texture menuScreenBackground;
    public Texture youWin;
    public Texture reset, notReset;
    public Texture ready, notReady;
    public Texture emptyDamageToken, damageToken1, damageToken2, damageToken3,
            damageToken4, damageToken5, damageToken6, damageToken7,
            damageToken8, damageToken9, damageToken10;
    public Texture emptyLifeToken, lifeToken1, lifeToken2, lifeToken3;
    public Texture singlePlayerButton;
    public Texture joinMultiPlayerButton;
    public Texture hostMultiPlayerButton;
    public PlayerGraphics playerGraphics;
    public final CardGraphics cardGraphics;
    public final HumanPlayer singlePlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    private final MenuInputProcessor menuInputProcessor;
    public Sprite singlePlayerSprite;
    public ArrayList<Sprite> cardSpriteList;
    private final CardMoveLogic cardMoveLogic = new CardMoveLogic();
    private HashMap<Action, Texture> cardTextures = new HashMap<>();
    public final Game game;
    public final ArrayList<Player> singlePlayerList =new ArrayList<>();


    public Graphics(Game game) {
        menuInputProcessor = new MenuInputProcessor(this);
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


    public void updatePlayerSprite(ArrayList<Player> players, Player myPlayer){
        if (players == null || players.isEmpty()) {
            return;
        }
        setInputProcessor(myPlayer);
        updateCardSprite(myPlayer);
        myPlayer.setMouseClickCoordinates(camera);
        for (Player player : players){
            Sprite playerSprite = playerGraphics.getPlayerSprite(player);
            Texture playerTexture = playerGraphics.getPlayerTexture(player);
            playerSprite.setTexture(playerTexture);
            playerSprite.setSize(300,300);
            playerSprite.setPosition(player.getPlayerXPosition(), player.getPlayerYPosition());
            playerSprite.draw(tiledMapRenderer.getBatch());
        }
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
        singlePlayerList.add(singlePlayer);
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

        lifeToken3 = new Texture("LifeToken/1.png");
        lifeToken2 = new Texture("LifeToken/2.png");
        lifeToken1 = new Texture("LifeToken/3.png");
        emptyLifeToken = new Texture("LifeToken/4.png");

        emptyDamageToken = new Texture("Damagetoken/50.png");
        damageToken1 = new Texture("Damagetoken/60.png");
        damageToken2 = new Texture("Damagetoken/70.png");
        damageToken3 = new Texture("Damagetoken/80.png");
        damageToken4 = new Texture("Damagetoken/90.png");
        damageToken5 = new Texture("Damagetoken/100.png");
        damageToken6 = new Texture("Damagetoken/110.png");
        damageToken7 = new Texture("Damagetoken/120.png");
        damageToken8 = new Texture("Damagetoken/130.png");
        damageToken9 = new Texture("Damagetoken/140.png");
        damageToken10 = new Texture("Damagetoken/150.png");

        // Menu Textures
        menuScreenBackground = new Texture("MenuScreen/MenuBackground.png"); // TODO actual textures
        singlePlayerButton = new Texture("SinglePlayerButton.png");
        joinMultiPlayerButton = new Texture("JoinGameButton.png");
        hostMultiPlayerButton = new Texture("HostGameButton.png");
        layer = new TileLayers((TiledMapTileLayer) tiledMap.getLayers().get("Laser"),
                (TiledMapTileLayer) tiledMap.getLayers().get("BlueConveyor"),
                (TiledMapTileLayer) tiledMap.getLayers().get("YellowConveyor"),
                (TiledMapTileLayer) tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) tiledMap.getLayers().get("GreenGear"),
                (TiledMapTileLayer) tiledMap.getLayers().get("Hole"),
                (TiledMapTileLayer) tiledMap.getLayers().get("Walls"),
                (TiledMapTileLayer) tiledMap.getLayers().get("Checkpoint"));

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
        singlePlayer.singlePlayerRound(singlePlayerList,layer);

    }


    /**
     * Displayed on the screen.
     * @param width of the screen
     * @param height of the screen
     */
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 1280;
        camera.viewportHeight = 720;
        camera.update();
    }

    private void renderMenuScreen() {
        // Draw background
        spriteBatch.begin();
        spriteBatch.draw(menuScreenBackground, 0, 0, 1280, 720);
        spriteBatch.end();

        // Draw buttons
        spriteBatch.begin();
        spriteBatch.draw(singlePlayerButton, 600, 400, 125, 55);
        spriteBatch.draw(hostMultiPlayerButton, 600, 300, 125, 55);
        spriteBatch.draw(joinMultiPlayerButton, 600, 200, 125, 55);
        spriteBatch.end();

        // Handle inputs
        Gdx.input.setInputProcessor(menuInputProcessor);
    }

    /**
     * This is where the graphics of the game get rendered.
     */
    @Override
    public void render() {
        if (game.currentScreen == GameScreen.MENU) {
            renderMenuScreen();
            return;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1280, 720);
        spriteBatch.end();

        //shows when to click the buttons and when not to click
        readyButtonIndicator();
        resetButtonIndicator();

        //informs the player when damaged
        damageTokenIndicator();
        //informs how many lifes the player has left
        lifeTokenIndicator();

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        if (game.typeOfGameStarted == GameType.SINGLE_PLAYER) {
            Gdx.input.setInputProcessor(singlePlayer);
            singlePlayer();

        } else {
            game.executeMoves(layer);
            updatePlayerSprite(game.players, game.myHumanPlayer);
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
        } else {
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
        } else {
            spriteBatch.begin();
            spriteBatch.draw(notReset, 1053, 129, 125, 55);
            spriteBatch.end();
        }
    }

    public void lifeTokenIndicator(){
        // displaying the lifetoken
        spriteBatch.begin();
        if(singlePlayer.getPlayerHealth() == 1){
            spriteBatch.draw(lifeToken1, 740, 530, 110, 65);
        }
        else if(singlePlayer.getPlayerHealth() == 2){
            spriteBatch.draw(lifeToken2, 740, 530, 110, 65);
        }
        else if(singlePlayer.getPlayerHealth() == 3){
            spriteBatch.draw(lifeToken3, 740, 530, 110, 65);
        }
        else{
            spriteBatch.draw(emptyLifeToken, 740, 530, 110, 65);
        }
        spriteBatch.end();
    }

    public void damageTokenIndicator(){
        // displaying the damagetoken
        spriteBatch.begin();
        if(singlePlayer.getPlayerDamageTaken() == 1){
            spriteBatch.draw(damageToken1, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 2){
            spriteBatch.draw(damageToken2, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 3){
            spriteBatch.draw(damageToken3, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 4){
            spriteBatch.draw(damageToken4, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 5){
            spriteBatch.draw(damageToken5, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 6){
            spriteBatch.draw(damageToken6, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 7){
            spriteBatch.draw(damageToken7, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 8){
            spriteBatch.draw(damageToken8, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 9){
            spriteBatch.draw(damageToken9, 710, 350, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 10){
            spriteBatch.draw(damageToken10, 710, 350, 185, 110);
        }
        else{
            spriteBatch.draw(emptyDamageToken, 710, 350, 185, 110);
        }
        spriteBatch.end();
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