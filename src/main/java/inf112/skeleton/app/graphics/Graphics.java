package inf112.skeleton.app.graphics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.BoardItems.Laser;
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
    public Texture background;
    public Texture youWin;

    public PlayerGraphics playerGraphics;
    public CardGraphics cardGraphics;
    public HashMap<Color, Sprite> playersSprite;
    public Player singlePlayer = new HumanPlayer(Direction.NORTH,69,Color.GREEN);
    public Sprite singlePlayerSprite;
    public ArrayList<Sprite> cardSpriteList;
    private CardMoveLogic cardMoveLogic = new CardMoveLogic();
    private HashMap<Action, Texture> cardTextures;
    public Game game;
    public Laser laser = new Laser(Direction.WEST,600f);
    public ArrayList<Player> singelPlayerList =new ArrayList<>();
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
            card.setTexture(cardTextures.get(humanPlayer.playerDeck.get(cardNumber).action));
            card.draw(tiledMapRenderer.getBatch());
            cardNumber++;
            cardCoordinateX +=2;
            cardCoordinateY += 2;
        }
    }


    public void setInputProcessor(Player player){
        Gdx.input.setInputProcessor((InputProcessor) player);
    }

    /**
     * Det som ser ut til å skje er at vi renderer alle spillere sine kort itillegg til alle spillere.
     * Det er mulig at vi kunne hatt en screen til en spiller og rendret egene kort på denne og alle spillere.
     *
     * Hvorfor lager vi en for mye spiller i multiplayer??
     *
     * Om vi setter this.numberOfPlayers = numberOfPlayers -1; i Game setnumofplayers får vi bare en spiller opp.
     * Ting ser da ut til å fungere bortsett fra at vi ikke utfører trekkene.
     * Men vi legger kortene i chosencard og de flyttes som de skal.
     *
     * MÅ SJEKKE om vi spiller to stykker trykker vi på kort for forskjellig spiller???
     * Rendrer vi egene kort øverst??
     *
     * Når vi spiller to stk får vi opp to helt forskjellige sett med kort. vi er begge spiller går og ikke grønn.
     * Vi får opp kortene til grønn under våre kort og hverken grønn eller grå sine kort er like for begge som spiller.
     *
     *
     * Vi ønsker å rendre kortene til this.player
     * Vi ønsker å sette inp PRo til this. player
     * Vi ønsker å sette setMouseClickCoordinates til this.player
     *
     * Vi ønaker å rendre og oppdatere alle spillbrikkene til alle players
     *
     * Virker ikke som vi får tildelt en spesifikk player over nett, men tar en (første?) fra listen.
     *
     *Flytte inp pro når vi har fått ting til å virke
     */
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

            Sprite playerSprite = playersSprite.get(player.color);
            playerSprite.setTexture(playerGraphics.createPlayerTextures().get(player.color).get(player.direction));
            playerSprite.setSize(300,300);
            playerSprite.setPosition(player.getPlayerXPosition(), player.getPlayerYPosition());
            playerSprite.draw(tiledMapRenderer.getBatch());
        }
    }

    /**
     * temp runs a singel-player gui
     */
    public void singlePlayer(){
        singlePlayer.setMouseClickCoordinates(camera);
        updateCardSprite(singlePlayer);
        singlePlayerSprite.setPosition(singlePlayer.getPlayerXPosition(), singlePlayer.getPlayerYPosition());
        singlePlayerSprite.setTexture(playerGraphics.createPlayerTextures().get(singlePlayer.color).get(singlePlayer.direction)); //greenPiece.get(humanPlayer.direction))
        singlePlayerSprite.draw(tiledMapRenderer.getBatch());
        singlePlayer.singlePlayerRound();
    }

    @Override
    public void create() {

        singlePlayer.playerDeck = cardMoveLogic.playerDeck();
        singlePlayerSprite = new Sprite((playerGraphics.createPlayerTextures().get(singlePlayer.color).get(singlePlayer.direction)));
        singlePlayerSprite.setSize(300,300);

        // Creates a list of sprites
        cardSpriteList = cardGraphics.createCardSprite();
        cardTextures = cardGraphics.createCardTexture();
        playersSprite = playerGraphics.createPlayerSprite();
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
        laser.shootLaser(singelPlayerList);
        //System.out.println(singlePlayer.healthToken+ " HealthToken");
        //System.out.println(singlePlayer.damageTaken +" DamageTaken");
        TiledMapTileSet tileset =  tiledMap.getTileSets().getTileSet("Flag1");
        //System.out.println(tileset);
        tiledMapRenderer.getBatch().begin();
        if(game.typeOfGameStarted == "single player"){
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