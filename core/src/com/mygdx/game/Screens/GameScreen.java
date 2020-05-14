package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.AnimalsBuilding;
import com.mygdx.game.Actors.Field;
import com.mygdx.game.Actors.Home;
import com.mygdx.game.Actors.Shop;
import com.mygdx.game.Actors.Storage;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

import java.util.ArrayList;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;

    //actors
    private Home homeActor;
    private Field fieldActor;
    private Shop shopActor;
    private AnimalsBuilding chickenBuildingActor, pigsBuildingActor, cowsBuildingActor;
    private Storage storageActor;


    //Textures

    private Texture fieldTexture, barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture;


    public GameScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        stage.setDebugAll(true); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //Cargamos texturas
        fieldTexture = game.getAssetManager().get("badlogic.jpg");

        barnTexture = game.getAssetManager().get("Textures/Barn.png");
        chickenCoopTexture = game.getAssetManager().get("Textures/ChickenCoop.png");
        houseTexture = game.getAssetManager().get("Textures/House.png");
        pigstyTexture = game.getAssetManager().get("Textures/Pigsty.png");
        shopTexture = game.getAssetManager().get("Textures/Shop.png");
        storeTexture = game.getAssetManager().get("Textures/Store.png");

        fieldActor = new Field(world, fieldTexture, new Vector2(10f, 5.5f), game.getSoundFactory());
        homeActor = new Home(world, houseTexture, new Vector2(26f, 15f), game.getSoundFactory());
        chickenBuildingActor = new AnimalsBuilding(world, chickenCoopTexture, new Vector2(16f, 15f), game.getSoundFactory());
        pigsBuildingActor = new AnimalsBuilding(world, pigstyTexture, new Vector2(10f, 15f), game.getSoundFactory());
        cowsBuildingActor = new AnimalsBuilding(world, barnTexture, new Vector2(4f, 15f), game.getSoundFactory());
        shopActor = new Shop(world, shopTexture, new Vector2(26f, 3f), game.getSoundFactory());
        storageActor = new Storage(world, storeTexture, new Vector2(26f, 8.5f), game.getSoundFactory());

        stage.addActor(fieldActor);
        stage.addActor(homeActor);
        stage.addActor(chickenBuildingActor);
        stage.addActor(pigsBuildingActor);
        stage.addActor(cowsBuildingActor);

        stage.addActor(shopActor);
        stage.addActor(storageActor);


        storageActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getStorageScreen());
                //do your stuff
                //it will work when finger is released..

            }

            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                boolean touchdown=false;
                //do your stuff it will work when u touched your actor
                return true;
            }

        });



    }

    @Override
    public void hide() {
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(0.1f, 0.6f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);

        //stage.getCamera().position.set(fieldActor.getX(), fieldActor.getX(), stage.getCamera().position.z);



        stage.draw();

    }

    @Override
    public void dispose() {
        Texture[] allTextures = {fieldTexture, barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture};
        for (Texture textura : allTextures) {
            textura.dispose();
        }

        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }

}
