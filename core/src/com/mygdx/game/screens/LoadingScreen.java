package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.actors.SpiningIconActor;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

import java.util.ArrayList;


public class LoadingScreen extends BaseScreen {


    private Stage stage;
    private World world;
    private SpiningIconActor tractorActor;
    private ArrayList<Texture> tractorTextures;
    private Label chargingLabel;
    private Skin skin;
    private ProgressBar progressBar;


    public LoadingScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes

        this.skin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));


        tractorTextures = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            String tractor = String.format("Textures/tractor/Tractor%d.png", i);

            tractorTextures.add((Texture) game.getMiniAssetManager().get(tractor));

        }


        tractorActor = new SpiningIconActor(world, tractorTextures, new Vector2(
                                                            Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER / 2,
                                                            Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER / 2) );
        stage.addActor(tractorActor);
        progressBar = new ProgressBar(0f,100f,1f,false,skin,"default-horizontal");
        progressBar.setSize(Constants.DEVICE_WIDTH * 0.5f, Constants.DEVICE_HEIGHT * 0.1f);
        progressBar.setPosition(Constants.DEVICE_WIDTH * 0.25f, Constants.DEVICE_HEIGHT * 0.10f);
        stage.addActor(progressBar);
        chargingLabel = new Label("", skin, "big");
        chargingLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
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


       if (game.getAssetManager().update()){
           game.finishLoading();
           game.showMenuScreen();
       }else{
            int progress = (int)(game.getAssetManager().getProgress() * 100);
            chargingLabel.setText(String.format("Loading: %s%s", progress,"%"));
            progressBar.setValue(progress);
       }

        stage.draw();



    }

    @Override
    public void dispose() {
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
