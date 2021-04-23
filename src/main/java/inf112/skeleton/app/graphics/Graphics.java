package inf112.skeleton.app.graphics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.game.*;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.screens.EndScreen;
import inf112.skeleton.app.screens.HostGameScreenProcessor;
import inf112.skeleton.app.screens.JoinGameScreenProcessor;
import inf112.skeleton.app.screens.MenuInputProcessor;
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
    private BitmapFont font;
    public Texture background;
    public Texture menuScreenBackground;
    public Texture youWin, youLose;
    public Texture exitButton, playAgainButton;
    public Texture rulesButton;
    public Texture reset, notReset;
    public Texture ready, notReady;
    public Texture emptyDamageToken, damageToken1, damageToken2, damageToken3,
            damageToken4, damageToken5, damageToken6, damageToken7,
            damageToken8, damageToken9, damageToken10;
    public Texture emptyLifeToken, lifeToken1, lifeToken2, lifeToken3;
    public Texture singlePlayerButton;
    public Texture joinMultiPlayerButton;
    public Texture hostMultiPlayerButton;
    public Texture startGameButton;
    public Texture readyButton;
    public Texture backToMenuScreenButton;
    public PlayerGraphics playerGraphics;
    public final CardGraphics cardGraphics;
    public  Player singlePlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    private final MenuInputProcessor menuInputProcessor;
    public final HostGameScreenProcessor hostGameScreenProcessor;
    public final JoinGameScreenProcessor joinGameScreenProcessor;
    private final EndScreen endScreen;
    public Sprite singlePlayerSprite;
    public ArrayList<Sprite> cardSpriteList;
    private  CardMoveLogic cardMoveLogic = new CardMoveLogic();
    private HashMap<Action, Texture> cardTextures = new HashMap<>();
    public final Game game;
    public final ArrayList<Player> singlePlayerList =new ArrayList<>();
    public String enteredIp = "";
    public boolean singlePlayerGameStarted = true;

    public Graphics(Game game) {
        menuInputProcessor = new MenuInputProcessor(this);
        hostGameScreenProcessor = new HostGameScreenProcessor(this);
        joinGameScreenProcessor = new JoinGameScreenProcessor(this);
        endScreen = new EndScreen(this);
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

        /**

        singlePlayer.playerDeck = cardMoveLogic.playerDeck();

        singlePlayerSprite = playerGraphics.getPlayerSprite(singlePlayer.color);
        singlePlayerSprite.setSize(300,300);
         */
        singlePlayer.playerDeck = cardMoveLogic.playerDeck();
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
        background = new Texture("background1.png");
        youWin = new Texture("YouWin.png");
        youLose = new Texture("YouLose.png");

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

        rulesButton = new Texture("RulesButton.png");

        // Menu Textures
        menuScreenBackground = new Texture("MenuScreen/MenuBackground.png");
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

        menuScreenBackground = new Texture("MenuScreen/MenuBackground.png");
        singlePlayerButton = new Texture("SinglePlayerButton1.png");
        joinMultiPlayerButton = new Texture("JoinGameButton1.png");
        hostMultiPlayerButton = new Texture("HostGameButton1.png");

        //Host screen textures
        font = new BitmapFont();
        startGameButton = new Texture("StartGameButton.png");
        backToMenuScreenButton = new Texture("BackButton.png");

        //Join screen textures
        readyButton = new Texture("ReadyButton.png");

        //End screen textures
        exitButton = new Texture("ExitButton.png"); //TODO might change later
        playAgainButton = new Texture("ReturnButton.png");
    }
    /**
     * temp runs a single-player gui
     */
    public void singlePlayer(){


        singlePlayerSprite = playerGraphics.getPlayerSprite(singlePlayer.color);

        singlePlayer.setMouseClickCoordinates(camera);
        updateCardSprite(singlePlayer);
        singlePlayerSprite.setPosition(singlePlayer.getPlayerXPosition(), singlePlayer.getPlayerYPosition());
        Texture playerTexture = playerGraphics.getPlayerTexture(singlePlayer);
        singlePlayerSprite.setTexture(playerTexture);
        singlePlayerSprite.setSize(300,300);
        singlePlayerSprite.draw(tiledMapRenderer.getBatch());
        game.doSinglePlayerMove(layer);

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
        spriteBatch.draw(singlePlayerButton, 530, 350, 250, 150);
        spriteBatch.draw(hostMultiPlayerButton, 530, 250, 250, 150);
        spriteBatch.draw(joinMultiPlayerButton, 530, 150, 250, 150);
        spriteBatch.end();

        // Handle inputs
        menuInputProcessor.setMouseClickCoordinates(camera);
        Gdx.input.setInputProcessor(menuInputProcessor);
    }

    private void renderHostGameScreen(){
        //draw background
        spriteBatch.begin();
        spriteBatch.draw(menuScreenBackground, 0, 0, 1280, 720);
        spriteBatch.end();

        //draw message
        spriteBatch.begin();
        font.draw(spriteBatch, "Waiting for players..", 500, 500);
        font.draw(spriteBatch, "There are " + game.numberOfPlayersConnected + " players in the game", 500, 450);
        spriteBatch.end();



        //draw buttons
        spriteBatch.begin();
        spriteBatch.draw(startGameButton,530,250,250,150);
        spriteBatch.draw(backToMenuScreenButton,530,150,250,150);
        spriteBatch.end();

        //handle input
        hostGameScreenProcessor.setMouseClickCoordinates(camera);
        Gdx.input.setInputProcessor(hostGameScreenProcessor);
    }

    private void renderJoinGameScreen(){
        //draw background
        spriteBatch.begin();
        spriteBatch.draw(menuScreenBackground, 0, 0, 1280, 720);

        //draw buttons
        spriteBatch.draw(readyButton,530,250,250,150);
        spriteBatch.draw(backToMenuScreenButton,530,150,250,150);

        // Draw input
        font.draw(spriteBatch, "Enter IP address by typing...", 500, 500);
        font.draw(spriteBatch, enteredIp, 500, 460);
        spriteBatch.end();

        //handle input

        joinGameScreenProcessor.setMouseClickCoordinates(camera);
        Gdx.input.setInputProcessor(joinGameScreenProcessor);
    }

    private void renderWin() {
        spriteBatch.begin();
        spriteBatch.draw(youWin, 0, 0, 1280, 720);
        spriteBatch.end();

        spriteBatch.begin();
        spriteBatch.draw(playAgainButton, 530, 250, 250, 150);
        spriteBatch.end();

        spriteBatch.begin();
        spriteBatch.draw(exitButton, 530, 150, 250, 150);
        spriteBatch.end();

        endScreen.setMouseClickCoordinates(camera);
        Gdx.input.setInputProcessor(endScreen);
    }

    private void renderLose() {
        spriteBatch.begin();
        spriteBatch.draw(youLose, 0, 0, 1280, 720);
        spriteBatch.end();

        spriteBatch.begin();
        spriteBatch.draw(playAgainButton, 530, 250, 250, 150);
        spriteBatch.end();

        spriteBatch.begin();
        spriteBatch.draw(exitButton, 530, 150, 250, 150);
        spriteBatch.end();

        endScreen.setMouseClickCoordinates(camera);
        Gdx.input.setInputProcessor(endScreen);
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
        if (game.currentScreen == GameScreen.HOST) {
            renderHostGameScreen();
            return;
        }
        if (game.currentScreen == GameScreen.JOIN){
            renderJoinGameScreen();
            return;
        }
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1280, 720);
        spriteBatch.end();

        if(game.currentScreen == GameScreen.GAME){
            spriteBatch.begin();
            spriteBatch.draw(rulesButton,-30,520,200,130);
            spriteBatch.end();
            endScreen.setMouseClickCoordinates(camera);
            Gdx.input.setInputProcessor(endScreen);
        }

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
            if(singlePlayerGameStarted == true){
                singlePlayerGameStarted= game.createSinglePlayer();
            }

            singlePlayer = game.myHumanPlayer;
            singlePlayer();

        } else {
            singlePlayer = game.myHumanPlayer;
            game.multiplayerRound(layer);
            updatePlayerSprite(game.players, game.myHumanPlayer);

        }
        tiledMapRenderer.getBatch().end();

        //the player gets a win screen after visiting all the flags
        if (isWinner()) {
            if(game.winScreen== GameScreen.WIN) {
                renderWin();
                if(game.typeOfGameStarted == GameType.NETWORK_HOST) {
                    game.server.stop();
                }
            }
        }
        //the player gets a lose screen when the player dies or loses against other players
        if(singlePlayer.isPlayerAlive()){
            if(game.loseScreen == GameScreen.LOSE){
                renderLose();
            }
        }
    }



    public boolean isWinner(){
        return singlePlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"));
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
            spriteBatch.draw(lifeToken1, 755, 505, 110, 65);
        }
        else if(singlePlayer.getPlayerHealth() == 2){
            spriteBatch.draw(lifeToken2, 755, 505, 110, 65);
        }
        else if(singlePlayer.getPlayerHealth() == 3){
            spriteBatch.draw(lifeToken3, 755, 505, 110, 65);
        }
        else{
            spriteBatch.draw(emptyLifeToken, 755, 505, 110, 65);
        }
        spriteBatch.end();
    }

    public void damageTokenIndicator(){
        // displaying the damagetoken
        spriteBatch.begin();
        if(singlePlayer.getPlayerDamageTaken() == 1){
            spriteBatch.draw(damageToken1, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 2){
            spriteBatch.draw(damageToken2, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 3){
            spriteBatch.draw(damageToken3, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 4){
            spriteBatch.draw(damageToken4, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 5){
            spriteBatch.draw(damageToken5, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 6){
            spriteBatch.draw(damageToken6, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 7){
            spriteBatch.draw(damageToken7, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 8){
            spriteBatch.draw(damageToken8, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 9){
            spriteBatch.draw(damageToken9, 720, 345, 185, 110);
        }
        else if(singlePlayer.getPlayerDamageTaken() == 10){
            spriteBatch.draw(damageToken10, 720, 345, 185, 110);
        }
        else{
            spriteBatch.draw(emptyDamageToken, 720, 345, 185, 110);
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