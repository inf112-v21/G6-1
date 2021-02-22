package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.player.LocalHumanPlayer;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;


public class Graphics implements  ApplicationListener {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public LocalHumanPlayer localHumanPlayer;
    public Sprite sprite;
    public CardMoveOne testcard;
    public  Card testcard2;
    public Card testcard3;
    public CardFactory cardFactory;
    public Sprite CardMoveOneSprite;
    public Sprite CardMoveTwoSprite;
    public Sprite CardMoveThreeSprite;
    public Sprite CardRotateLeftSprite;
    public Sprite CardRotateRightSprite;
    public Sprite CardBackUpSprite;
    public Sprite CardUTurnSprite;

    public void CardSizeAndPosition(Sprite sprite, float xPos, float yPos){
        sprite.setSize(600,600);
        sprite.setPosition(xPos,yPos);
    }
    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        
        float h = Gdx.graphics.getHeight();
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 6f; //Shows more of the board
        camera.setToOrtho(false, h,w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        localHumanPlayer = new LocalHumanPlayer(new Sprite(new Texture("Player/OwlPlayer1.png")),(TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"), Direction.NORTH);
        testcard = new CardMoveOne(new Sprite(new Texture("Cards/Move1.png")),1,Action.MOVE_ONE);
        testcard.setSize(600,600);
        testcard.setPosition(0,-600);
        testcard2 = new CardMoveTwo(new Sprite(new Texture("Cards/Move2.png")),1,Action.MOVE_TWO);
        testcard2.setSize(600,600);
        testcard2.setPosition(600,-600);


        CardMoveOneSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardSizeAndPosition(CardMoveOneSprite, 0,-600);
        /*
        CardMoveOneSprite.setPosition(0,-600);
        CardMoveOneSprite.setSize(600,600);
        CardMoveTwoSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardMoveThreeSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardRotateLeftSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardRotateRightSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardBackUpSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        CardUTurnSprite = new Sprite(new Texture("Player/OwlPlayer1.png"));
        */

        Gdx.input.setInputProcessor((InputProcessor) localHumanPlayer);
        localHumanPlayer.setPlayerSize(300,300);
        //cardDeck.card(0);
        localHumanPlayer.setPosition(localHumanPlayer.updateX = 0,localHumanPlayer.updateY = 0);
    }/// TODO note to self ser ut som at kort i alle klassene skal være CARD

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

        Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        /*
        localHumanPlayer.draw(tiledMapRenderer.getBatch());
        testcard.draw(tiledMapRenderer.getBatch());

        testcard2.draw(tiledMapRenderer.getBatch());
        */
        CardMoveOneSprite.draw(tiledMapRenderer.getBatch());
        tiledMapRenderer.getBatch().end();
        if (localHumanPlayer.isGameOver(localHumanPlayer.flagLayer)) {
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

    }
}
