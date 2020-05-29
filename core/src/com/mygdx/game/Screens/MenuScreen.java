package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.Images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class MenuScreen extends BaseScreen {


    private Stage stage;
    private World world;
    private TextButton playButton;
    private Button settingButton;
    private ImageClampToEdge backgroundImage;
    private Label idLbabel;
    private Skin skin;
    private Texture backgroundTexture;

   public MenuScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);



       // apariencias de los skins
       this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));


       // inicialización de los elementos
       playButton = new TextButton("Jugar", skin , "custom");
       settingButton = new Button(skin, "settings");
       backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/menuBack.png");
       backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                                                                            Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);
       idLbabel = new Label(String.format("Id: 00%s", this.game.getUsuario().getId()), skin, "custom_grey");


       //funcionalidades
       playButton.addCaptureListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               MenuScreen.this.game.showGameScreen();
           }
       });

       settingButton.addCaptureListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               MenuScreen.this.game.showOptionsScreen();
           }
       });

       //tmaño de las fuentes
       playButton.getLabel().setFontScale(Constants.FONT_SIZE);
       idLbabel.setFontScale(Constants.FONT_SIZE * 0.8f);

       //tamaño de los elementos
       playButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
       settingButton.setSize(Constants.DEVICE_WIDTH * 0.05f,Constants.DEVICE_WIDTH * 0.05f);
       idLbabel.setSize(Constants.DEVICE_WIDTH * 0.3f,Constants.DEVICE_WIDTH * 0.08f);

        //posición de los elementos
       playButton.setPosition(Constants.DEVICE_WIDTH * 0.425f, Constants.DEVICE_HEIGHT * 0.05f);
       settingButton.setPosition(Constants.DEVICE_WIDTH * 0.94f, Constants.DEVICE_HEIGHT * 0.9f);
       idLbabel.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.88375f);


       stage.addActor(backgroundImage);
       stage.addActor(playButton);
       stage.addActor(settingButton);
       stage.addActor(idLbabel);

   }

    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void hide() {
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);


        stage.draw();

    }

    @Override
    public void dispose() {
        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }
    public void disableAll(boolean enableDisable){

    }
    public void actions(int actionIndex){

    }
}

