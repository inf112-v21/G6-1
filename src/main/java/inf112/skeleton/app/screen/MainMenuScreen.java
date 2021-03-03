package inf112.skeleton.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.graphics.Graphics;



public class MainMenuScreen  implements Screen{

    /*
    not done yet
     */

    private Graphics graphics;
    private Stage stage;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    Texture logo;
    Texture background;

    private BitmapFont fontLabel, fontLabelText;
    private Label.LabelStyle labelStyle;
    private ImageTextButton button1, button2, button3, button4, button5;
    private ImageTextButton.ImageTextButtonStyle imageTextButtonStyle;

    public MainMenuScreen(Graphics graphics){
        this.graphics = graphics;
        this.stage = stage;
        this.viewport = viewport;

        skin = new Skin();

        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(viewport.getWorldWidth(), viewport.getWorldHeight(), camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);

    }

    @Override
    public void show() {
        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.top();

        //Create buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //((Game)Gdx.app.getApplicationListener()).setScreen(new Graphics());
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(optionsButton);
        mainTable.row();
        mainTable.add(exitButton);

        //Add table to stage
        stage.addActor(mainTable);

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(graphics.background, 0, 0, 1280, 720);
        batch.draw(logo, (Gdx.app.getGraphics().getWidth() / 2) - 200, Gdx.app.getGraphics().getHeight() - 150, 400, 100);
        batch.end();

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

