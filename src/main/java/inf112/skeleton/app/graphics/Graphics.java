package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Direction;


public class Graphics extends ScreenAdapter implements ApplicationListener{
    private TiledMap tiledMap;
    public OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private SpriteBatch spriteBatch;
    public HumanPlayer humanPlayer;

    public Texture background;
    public Texture youWin;


    public Sprite moveOneSprite;
    public Sprite moveTwoSprite;
    public Sprite moveThreeSprite;
    public Sprite rotateLeftSprite;
    public Sprite rotateRightSprite;
    public Sprite backUpSprite;
    public Sprite uTurnSprite;
    public Sprite player;

    private Sprite cardOne;
    private Sprite cardTwo;
    private Sprite cardThree;
    private Sprite cardFour;
    private Sprite cardFive;
    private Sprite cardSix;
    private Sprite cardSeven;
    private Sprite cardEight;
    private Sprite cardNine;



    /**
     * test for LHP
     */
    public void cardSize(Sprite sprite){
        sprite.setSize(455,650);

    }


    @Override
    public void create() {
        float w = 600;
        float h = 1000;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 7f; //Shows more of the board
        camera.setToOrtho(false, h,w); //something needs adjustment here
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        humanPlayer = new HumanPlayer(Direction.NORTH, " Erlend","Super mario");
        humanPlayer.playerDeck = cardMoveLogic.playerDeck();
        player = new Sprite(new Texture(("Player/OwlPlayer1.png")));
        player.setSize(300,300);
        Gdx.input.setInputProcessor(humanPlayer);
        player.setPosition(humanPlayer.setPlayerStartXPosition(0) , humanPlayer.setPlayerStartYPosition(0) );

        //Create cardSprite to be matched whit correct backend card.
        moveOneSprite = new Sprite(new Texture("Cards/Move1.png"));
        cardSize(moveOneSprite);
        moveTwoSprite = new Sprite(new Texture("Cards/Move2.png"));
        cardSize(moveTwoSprite);
        moveThreeSprite = new Sprite(new Texture("Cards/Move3.png"));
        cardSize(moveThreeSprite);
        rotateLeftSprite = new Sprite(new Texture("Cards/RotateLeft.png"));
        cardSize(rotateLeftSprite);
        rotateRightSprite = new Sprite(new Texture("Cards/RotateRight.png"));
        cardSize(rotateRightSprite);
        backUpSprite = new Sprite(new Texture("Cards/BackUp.png"));
        cardSize(backUpSprite);
        uTurnSprite = new Sprite(new Texture("Cards/U-turn.png"));
        cardSize(uTurnSprite);
        bindCardSpriteToCard();

        background = new Texture("3.png");
        youWin = new Texture("YouWin.jpg");
    }

    /**
     * Binds the card to the correct card sprite from the players deck.
     */
    public void bindCardSpriteToCard(){
        cardOne = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(0));
        cardTwo = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(1));
        cardThree = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(2));
        cardFour = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(3));
        cardFive = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(4));
        cardSix = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(5));
        cardSeven = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(6));
        cardEight = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(7));
        cardNine  = getMatchingSpriteToPlayerCard(humanPlayer.playerDeck.get(8));
    }

    /**
     * Help method for bindCardSpriteToCard.
     * Checks the type of card from the player card deck
     * @param playerCard player card to match with sprite
     * @return Sprite
     */
    public Sprite getMatchingSpriteToPlayerCard(Card playerCard) {
                if (playerCard.action == Action.MOVE_ONE) return moveOneSprite;
                if (playerCard.action == Action.MOVE_TWO) return moveTwoSprite;
                if (playerCard.action == Action.MOVE_THREE) return moveThreeSprite;
                if (playerCard.action == Action.ROTATE_LEFT) return rotateLeftSprite;
                if (playerCard.action == Action.ROTATE_RIGHT) return rotateRightSprite;
                if (playerCard.action == Action.U_TURN) return uTurnSprite;
                if (playerCard.action == Action.BACK_UP) return backUpSprite;
        return null;
    }

    /**
     * Updates the cards positions on the board
     */
    public void updateCardPositions(){
        cardOne.setPosition(humanPlayer.cardCoordinates.get(0),humanPlayer.cardCoordinates.get(1));
        cardOne.draw(tiledMapRenderer.getBatch());
        cardTwo.setPosition(humanPlayer.cardCoordinates.get(2),humanPlayer.cardCoordinates.get(3));
        cardTwo.draw(tiledMapRenderer.getBatch());
        cardThree.setPosition(humanPlayer.cardCoordinates.get(4),humanPlayer.cardCoordinates.get(5));
        cardThree.draw(tiledMapRenderer.getBatch());
        cardFour.setPosition(humanPlayer.cardCoordinates.get(6),humanPlayer.cardCoordinates.get(7));
        cardFour.draw(tiledMapRenderer.getBatch());
        cardFive.setPosition(humanPlayer.cardCoordinates.get(8),humanPlayer.cardCoordinates.get(9));
        cardFive.draw(tiledMapRenderer.getBatch());
        cardSix.setPosition(humanPlayer.cardCoordinates.get(10),humanPlayer.cardCoordinates.get(11));
        cardSix.draw(tiledMapRenderer.getBatch());
        cardSeven.setPosition(humanPlayer.cardCoordinates.get(12),humanPlayer.cardCoordinates.get(13));
        cardSeven.draw(tiledMapRenderer.getBatch());
        cardEight.setPosition(humanPlayer.cardCoordinates.get(14),humanPlayer.cardCoordinates.get(15));
        cardEight.draw(tiledMapRenderer.getBatch());
        cardNine.setPosition(humanPlayer.cardCoordinates.get(16),humanPlayer.cardCoordinates.get(17));
        cardNine.draw(tiledMapRenderer.getBatch());
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
        spriteBatch.draw(background, 0, 0, 1280,720);
        spriteBatch.end();

        //player on display
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        humanPlayer.setMouseClickCoordinates(camera);
        tiledMapRenderer.getBatch().begin();
        bindCardSpriteToCard();
        updateCardPositions();
        player.setPosition(humanPlayer.getPlayerXPosition(), humanPlayer.getPlayerYPosition());
        player.draw(tiledMapRenderer.getBatch());
        humanPlayer.round();
        tiledMapRenderer.getBatch().end();


        //if the player has won, get "you win"-message up
        if (humanPlayer.isPlayerOnFlag((TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"))) {
            pause();
            System.out.println("You Won!");
            //"you win" screen pops up
            spriteBatch.begin();
            spriteBatch.draw(youWin, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        youWin.dispose();
    }
}