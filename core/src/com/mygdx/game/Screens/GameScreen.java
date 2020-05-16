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
import com.mygdx.game.Actors.FarmerActor;
import com.mygdx.game.Actors.Field;
import com.mygdx.game.Actors.Home;
import com.mygdx.game.Actors.Shop;
import com.mygdx.game.Actors.Storage;
import com.mygdx.game.Actors.TruckActor;
import com.mygdx.game.Constants;
import com.mygdx.game.Images.ImageRepeart;
import com.mygdx.game.Images.ImageVerticalRepeat;
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
    private FarmerActor farmerActor;
    private TruckActor truckActor;


    //Textures
    private Texture fieldTexture, barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture,
    bushTexture, groundTexture, tree1Texture, tree2Texture, roadTexture, truckTexture;
    private ArrayList<Texture> farmerTextures;

    //Images
    private ImageRepeart grondImage;
    private ImageVerticalRepeat roadImage;

    //Game Parameters
    private float timeToBeat, timeSinceLastRNDSound;


    public GameScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, -10), true);
        this.timeSinceLastRNDSound = 0;

    }
    @Override
    public void show() {
        stage.setDebugAll(true); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //Cargamos texturas
        fieldTexture = game.getAssetManager().get("Textures/fieldPRO.png");
        truckTexture = game.getAssetManager().get("Textures/truckPRO.png");

        barnTexture = game.getAssetManager().get("Textures/Buildings/Barn.png");
        chickenCoopTexture = game.getAssetManager().get("Textures/Buildings/ChickenCoop.png");
        houseTexture = game.getAssetManager().get("Textures/Buildings/House.png");
        pigstyTexture = game.getAssetManager().get("Textures/Buildings/Pigsty.png");
        shopTexture = game.getAssetManager().get("Textures/Buildings/Shop.png");
        storeTexture = game.getAssetManager().get("Textures/Buildings/Store.png");
        bushTexture = game.getAssetManager().get("Textures/Bush.png");
        groundTexture = game.getAssetManager().get("Textures/Ground.png");
        tree1Texture = game.getAssetManager().get("Textures/Tree1.png");
        tree2Texture = game.getAssetManager().get("Textures/Tree2.png");
        roadTexture = game.getAssetManager().get("Textures/Road.png");

        farmerTextures = new ArrayList<>(21);
        for (int i = 0; i < 21; i++) {
            String farmer = String.format("Textures/Farmer/Farmer%d.png",i);
            farmerTextures.add((Texture) game.getAssetManager().get(farmer));
        }


        fieldActor = new Field(world, fieldTexture, new Vector2(10f, 5.5f), game.getSoundFactory());
        homeActor = new Home(world, houseTexture, new Vector2(26f, 15f), game.getSoundFactory());
        chickenBuildingActor = new AnimalsBuilding(world, chickenCoopTexture, new Vector2(16f, 15f), game.getSoundFactory());
        pigsBuildingActor = new AnimalsBuilding(world, pigstyTexture, new Vector2(10f, 15f), game.getSoundFactory());
        cowsBuildingActor = new AnimalsBuilding(world, barnTexture, new Vector2(4f, 15f), game.getSoundFactory());
        shopActor = new Shop(world, shopTexture, new Vector2(25.75f, 3f), game.getSoundFactory());
        storageActor = new Storage(world, storeTexture, new Vector2(26f, 9f), game.getSoundFactory());
        farmerActor = new FarmerActor(world,farmerTextures,new Vector2(10, 8), game.getSoundFactory());
        truckActor = new TruckActor(world, truckTexture, new Vector2(21, 25),game.getSoundFactory());


        grondImage = new ImageRepeart(groundTexture,-20,-10,60,40, 2f);
        roadImage = new ImageVerticalRepeat(roadTexture,19.25f,-10,3.5f,40, 1);

        stage.addActor(grondImage);
        stage.addActor(roadImage);
        stage.addActor(fieldActor);
        stage.addActor(homeActor);
        stage.addActor(chickenBuildingActor);
        stage.addActor(pigsBuildingActor);
        stage.addActor(cowsBuildingActor);

        stage.addActor(truckActor);
        stage.addActor(shopActor);
        stage.addActor(storageActor);
        stage.addActor(farmerActor);


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

        cowsBuildingActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getAnimalsScreen());
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
        chickenBuildingActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getAnimalsScreen());
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
        pigsBuildingActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getAnimalsScreen());
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
        homeActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getMenuScreen());
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
        shopActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getShopScreen());
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
        fieldActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getFieldScreen());
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

        this.timeToBeat = (float) (Math.random() * 10) + 2;

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

        this.timeSinceLastRNDSound += delta;
        if ( this.timeToBeat < this.timeSinceLastRNDSound){
            this.timeSinceLastRNDSound = 0;
            this.timeToBeat = (float) (Math.random() * 5) + 5;
            game.getSoundFactory().playRDNSound();
        }


        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);

        //stage.getCamera().position.set(fieldActor.getX(), fieldActor.getX(), stage.getCamera().position.z);



        stage.draw();

    }

    @Override
    public void dispose() {
        Texture[] allTextures = {fieldTexture, barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture,
                bushTexture, groundTexture, tree1Texture, tree2Texture, roadTexture};
        for (Texture textura : allTextures) {
            textura.dispose();
        }
        for (int i = 0; i < 21; i++) {
            farmerTextures.get(i).dispose();
        }

        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }

}
