package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.AnimalsBuilding;
import com.mygdx.game.Actors.BaseActor;
import com.mygdx.game.Actors.DogActor;
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
    private TruckActor truckActor ;
    private DogActor dogActor;



    //Textures
    private Texture fieldTexture, barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture,
    bushTexture, groundTexture, tree1Texture, tree2Texture, roadTexture, truck1Texture, truck2Texture;
    private ArrayList<Texture> farmerTextures , dogTextures, chikenTextures, pigTextures, cowTextures;

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
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //Cargamos texturas
        fieldTexture = game.getAssetManager().get("Textures/Crop.png");
        truck1Texture = game.getAssetManager().get("Textures/Truck1.png");
        truck2Texture = game.getAssetManager().get("Textures/Truck2.png");

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
        dogTextures = new ArrayList<>(21);
        chikenTextures = new ArrayList<>(7);
        pigTextures = new ArrayList<>(7);
        cowTextures = new ArrayList<>(7);
        for (int i = 0; i < 21; i++) {
            String farmer = String.format("Textures/Farmer/Farmer%d.png",i);
            farmerTextures.add((Texture) game.getAssetManager().get(farmer));
            String dog = String.format("Textures/Dog/Dog%d.png",i);
            dogTextures.add((Texture) game.getAssetManager().get(dog));
        }
        for (int i = 0; i < 7; i++) {
            String chicken = String.format("Textures/Chiken/Chiken%d.png", i);
            chikenTextures.add((Texture) game.getAssetManager().get(chicken));
            String pig = String.format("Textures/Pig/Pig%d.png", i);
            // pigTextures.add((Texture) game.getAssetManager().get(pig));
            String cow = String.format("Textures/Cow/Cow%d.png", i);
            // cowTextures.add((Texture) game.getAssetManager().get(cow));
        }



        fieldActor = new Field(world, fieldTexture, new Vector2(10f, 5.5f), game.getSoundFactory());
        homeActor = new Home(world, houseTexture, new Vector2(26f, 15f), game.getSoundFactory());
        chickenBuildingActor = new AnimalsBuilding(world, chickenCoopTexture, new Vector2(16f, 15f), game.getSoundFactory());
        pigsBuildingActor = new AnimalsBuilding(world, pigstyTexture, new Vector2(10f, 15f), game.getSoundFactory());
        cowsBuildingActor = new AnimalsBuilding(world, barnTexture, new Vector2(4f, 15f), game.getSoundFactory());
        shopActor = new Shop(world, shopTexture, new Vector2(25.75f, 3f), game.getSoundFactory());
        storageActor = new Storage(world, storeTexture, new Vector2(26f, 9f), game.getSoundFactory());
        farmerActor = new FarmerActor(world,farmerTextures,new Vector2(10, 8), game.getSoundFactory());
        truckActor = new TruckActor(world, truck1Texture,truck2Texture, new Vector2(21, 25),game.getSoundFactory());
        dogActor = new DogActor(world, dogTextures, new Vector2(23,13), game.getSoundFactory());


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

        stage.addActor(dogActor); //It is important to implement before Storage
        stage.addActor(storageActor);
        stage.addActor(farmerActor);


        storageActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                game.setScreen(game.getStorageScreen());
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
                game.getSoundFactory().stopAll();
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
            dogTextures.get(i).dispose();
        }
        for (int i = 0; i < 7; i++) {
            chikenTextures.get(i).dispose();
            //pigTextures.get(i).dispose();
            //cowTextures.get(i).dispose();
        }

        BaseActor[] allActors = { homeActor, fieldActor, shopActor, chickenBuildingActor, pigsBuildingActor, cowsBuildingActor,
                storageActor, farmerActor, truckActor, dogActor};
        for (BaseActor actor : allActors){
            actor.detach();
        }

        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }

}
