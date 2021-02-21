package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.cards.Card;
import inf112.skeleton.app.cards.CardFactory;
import inf112.skeleton.app.cards.CardMoveOne;
import inf112.skeleton.app.player.LocalHumanPlayer;
import inf112.skeleton.app.shared.Action;


public class Graphics implements  ApplicationListener {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public CardMoveOne testCard;
    public LocalHumanPlayer localHumanPlayer;
    public CardFactory cardFactory = new CardFactory();
    public CardMoveOne cardmoveone;
    public Sprite sprite;

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
        localHumanPlayer = new LocalHumanPlayer(new Sprite(new Texture("Player/OwlPlayer1.png")),(TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"));
        cardmoveone =  cardFactory.createCardMoveOne();
        //cardmoveone = new CardMoveOne(new Sprite(new Texture("Cards/Move1.png")),1,Action.MOVE_ONE);
        //testCard.setSize(300,300);
        //testCard.setPosition(-300,-300);
        Gdx.input.setInputProcessor((InputProcessor) localHumanPlayer);
        localHumanPlayer.setPlayerSize(300,300);
        localHumanPlayer.setPosition(localHumanPlayer.updateX = 0,localHumanPlayer.updateY = 0);
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

        Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        tiledMapRenderer.getBatch().begin();
        //TODO her må det være mulig å kjøre draw felles
        localHumanPlayer.draw(tiledMapRenderer.getBatch());
        //System.out.println(testCard.action.getAction());
        //cardFactory.draw(tiledMapRenderer.getBatch());
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
