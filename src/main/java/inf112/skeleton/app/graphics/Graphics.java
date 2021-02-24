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
import inf112.skeleton.app.player.LocalHumanPlayer;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;


public class Graphics implements  ApplicationListener {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public LocalHumanPlayer localHumanPlayer;
    public Sprite CardMoveOneSprite;
    public Sprite CardMoveTwoSprite;
    public Sprite CardMoveThreeSprite;
    public Sprite CardRotateLeftSprite;
    public Sprite CardRotateRightSprite;
    public Sprite CardBackUpSprite;
    public Sprite CardUTurnSprite;

    public void CardSizeAndPosition(Sprite sprite, float xPos){
        sprite.setSize(400,550);
        sprite.setPosition(xPos,-600);
    }
    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.zoom = 6f; //Shows more of the board
        camera.setToOrtho(false, h,w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        localHumanPlayer = new LocalHumanPlayer(new Sprite(new Texture("Player/OwlPlayer1.png")),(TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"), Direction.NORTH);
        Gdx.input.setInputProcessor((InputProcessor) localHumanPlayer);
        localHumanPlayer.setPlayerSize(300,300);
        localHumanPlayer.setPosition(localHumanPlayer.updateX = 0,localHumanPlayer.updateY = 0);

        CardMoveOneSprite = new Sprite(new Texture("Cards/Move1.png"));
        CardSizeAndPosition(CardMoveOneSprite, 0);
        CardMoveTwoSprite = new Sprite(new Texture("Cards/Move2.png"));
        CardSizeAndPosition(CardMoveTwoSprite,600);
        CardMoveThreeSprite = new Sprite(new Texture("Cards/Move3.png"));
        CardSizeAndPosition(CardMoveThreeSprite,1200);
        CardRotateLeftSprite = new Sprite(new Texture("Cards/RotateLeft.png"));
        CardSizeAndPosition(CardRotateLeftSprite,1800);
        CardRotateRightSprite = new Sprite(new Texture("Cards/RotateRight.png"));
        CardSizeAndPosition(CardRotateRightSprite,2400);
        CardBackUpSprite = new Sprite(new Texture("Cards/BackUp.png"));
        CardSizeAndPosition(CardBackUpSprite,3000);
        CardUTurnSprite = new Sprite(new Texture("Cards/U-turn.png"));
        CardSizeAndPosition(CardUTurnSprite,3600);
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
        localHumanPlayer.draw(tiledMapRenderer.getBatch());
        CardMoveOneSprite.draw(tiledMapRenderer.getBatch());
        CardMoveTwoSprite.draw(tiledMapRenderer.getBatch());
        CardMoveThreeSprite.draw(tiledMapRenderer.getBatch());
        CardRotateLeftSprite.draw(tiledMapRenderer.getBatch());
        CardRotateRightSprite.draw(tiledMapRenderer.getBatch());
        CardUTurnSprite.draw(tiledMapRenderer.getBatch());
        CardBackUpSprite.draw(tiledMapRenderer.getBatch());
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
