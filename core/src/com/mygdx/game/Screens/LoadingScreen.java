package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.SpiningIconActor;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;


public class LoadingScreen extends BaseScreen {


    private Stage stage;
    private World world;
    private SpiningIconActor tractorActor;
    private Texture tractorTexture;
    private float timeinLoadingScreen;
    private Label chargingLabel;
    private Skin skin;

    public LoadingScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        timeinLoadingScreen = 0;

        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        tractorTexture = game.getAssetManager().get("Textures/tractor.png");


        tractorActor = new SpiningIconActor(world, tractorTexture, new Vector2(
                                                            Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER / 2,
                                                            Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER / 2) );
        stage.addActor(tractorActor);
        chargingLabel = new Label("Cargando: 50%", skin, "custom_blue");
        chargingLabel.setFontScale(Constants.FONT_SIZE);
        chargingLabel.setSize(Constants.DEVICE_WIDTH * 0.2f, Constants.DEVICE_HEIGHT * 0.1f);
        chargingLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.15f);
        chargingLabel.setAlignment(Align.center);
        stage.addActor(chargingLabel);


    }

    @Override
    public void hide() {
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(0.9f,0.9f, 0.9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);


        timeinLoadingScreen += delta;

        if (Gdx.input.justTouched() && this.timeinLoadingScreen > 3){
            this.game.showMenuScreen();
        }

        stage.draw();



    }

    @Override
    public void dispose() {
        tractorTexture.dispose();
        tractorActor.detach();
        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }
    public void disableAll(boolean enableDisable){

    }
    public void actions(int actionIndex){

    }
}
