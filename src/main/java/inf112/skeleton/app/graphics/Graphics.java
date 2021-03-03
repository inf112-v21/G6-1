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
import inf112.skeleton.app.player.LocalHumanPlayer;
import inf112.skeleton.app.screen.MainMenuScreen;
import inf112.skeleton.app.shared.Direction;


public class Graphics implements ApplicationListener{
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public LocalHumanPlayer localHumanPlayer;

    public Texture background;
    public Texture youwin;

    public Sprite CardMoveOneSprite;
    public Sprite CardMoveTwoSprite;
    public Sprite CardMoveThreeSprite;
    public Sprite CardRotateLeftSprite;
    public Sprite CardRotateRightSprite;
    public Sprite CardBackUpSprite;
    public Sprite CardUTurnSprite;
    public Sprite player;

    /**
     * test for LHP
     */
    public void CardSizeAndPosition(Sprite sprite, float xPos){
        sprite.setSize(400,550);
        sprite.setPosition(xPos,-600);
    }

    @Override
    public void create() {
        float w = 600;
        float h = 1000;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h, w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        localHumanPlayer = new LocalHumanPlayer((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"), Direction.NORTH);
        player = new Sprite(new Texture(("Player/OwlPlayer1.png")));
        player.setSize(300,300);
        Gdx.input.setInputProcessor((InputProcessor) localHumanPlayer);
        player.setPosition(localHumanPlayer.xStart(0) ,localHumanPlayer.yStartPos(0) );

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

        background = new Texture("Background.png");
        youwin = new Texture("YouWin.jpg");
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

        tiledMapRenderer.getBatch().begin();
        player.setPosition(localHumanPlayer.getX(), localHumanPlayer.getY());
        player.draw(tiledMapRenderer.getBatch());
        //CardMoveOneSprite.draw(tiledMapRenderer.getBatch());
        //CardMoveOneSprite.setPosition(localHumanPlayer.cardXPos,localHumanPlayer.cardYPos);
        CardMoveTwoSprite.draw(tiledMapRenderer.getBatch());
        CardMoveThreeSprite.draw(tiledMapRenderer.getBatch());
        CardRotateLeftSprite.draw(tiledMapRenderer.getBatch());
        CardRotateRightSprite.draw(tiledMapRenderer.getBatch());
        CardUTurnSprite.draw(tiledMapRenderer.getBatch());
        CardBackUpSprite.draw(tiledMapRenderer.getBatch());
        localHumanPlayer.round(localHumanPlayer);

        tiledMapRenderer.getBatch().end();

        //if the player has won, get "you win"-message up
        if (localHumanPlayer.isGameOver(localHumanPlayer.flagLayer)) {
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