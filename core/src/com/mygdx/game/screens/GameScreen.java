package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.actors.AnimalsBuildingActor;
import com.mygdx.game.actors.BaseActor;
import com.mygdx.game.actors.DogActor;
import com.mygdx.game.actors.FarmerActor;
import com.mygdx.game.actors.FieldActor;
import com.mygdx.game.actors.HomeActor;
import com.mygdx.game.actors.PulseActor;
import com.mygdx.game.actors.ShopActor;
import com.mygdx.game.actors.StorageActor;
import com.mygdx.game.actors.TruckActor;
import com.mygdx.game.Constants;
import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.Infraestructura;
import com.mygdx.game.dominio.TipoRecurso;
import com.mygdx.game.images.ImageClampToEdge;
import com.mygdx.game.images.ImageRepeart;
import com.mygdx.game.MainGame;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;

    //actors
    private HomeActor homeActor;
    private FieldActor fieldActor;
    private ShopActor shopActor;
    private AnimalsBuildingActor chickenBuildingActor, pigsBuildingActor, cowsBuildingActor;
    private StorageActor storageActor;
    private FarmerActor farmerActor;
    private TruckActor truckActor ;
    private DogActor dogActor;
    private PulseActor chickenPulseActor ;



    //Textures
    private Texture  barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture,
        bushTexture, groundTexture, tree1Texture, tree2Texture, tree3Texture,roadTexture, truck1Texture, truck2Texture,
        mailboxTexture, bikeTexture, drikerTexture, flower1Texture, flower2Texture, flowerPotTexture, rock1Texture,
        rock2Texture, stone1Texture, stone2Texture, flower3Texture, scareCrowTexture, rottenGrass1Texture, rottenGrass2Texture,
        rottenGrass3Texture, bush2Texture, woodPathTexture1, woodPathTexture2, woodPathTexture3;
    private ArrayList<Texture> farmerTextures , dogTextures, chikenTextures, fieldTextures, pigTextures, cowTextures;

    //Images
    private ImageRepeart grondImage;
    //private ImageVerticalRepeat roadImage;
    private ImageClampToEdge roadImage, woodRoad;

    private List<ImageClampToEdge> trees, decorations, roads;

    //Game Parameters
    private float timeToBeat, timeSinceLastRNDSound, timeSinceLastFieldControl;


    public GameScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, -10), true);
        this.timeSinceLastRNDSound = 0;
        this.timeSinceLastFieldControl = 0;

    }
    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //Cargamos texturas
        truck1Texture = game.getAssetManager().get("Textures/Truck1.png");
        truck2Texture = game.getAssetManager().get("Textures/Truck2.png");

        barnTexture = game.getAssetManager().get("Textures/Buildings/Barn.png");
        chickenCoopTexture = game.getAssetManager().get("Textures/Buildings/ChickenCoop.png");
        houseTexture = game.getAssetManager().get("Textures/Buildings/House.png");
        pigstyTexture = game.getAssetManager().get("Textures/Buildings/Pigsty.png");
        shopTexture = game.getAssetManager().get("Textures/Buildings/Shop.png");
        storeTexture = game.getAssetManager().get("Textures/Buildings/Store.png");
        bushTexture = game.getAssetManager().get("Textures/Bush.png");
        bush2Texture = game.getAssetManager().get("Textures/bush2.png");
        groundTexture = game.getAssetManager().get("Textures/Ground.png");
        tree1Texture = game.getAssetManager().get("Textures/Tree1.png");
        tree2Texture = game.getAssetManager().get("Textures/Tree2.png");
        tree3Texture = game.getAssetManager().get("Textures/Tree3.png");
        roadTexture = game.getAssetManager().get("Textures/Road.png");
        mailboxTexture = game.getAssetManager().get("Textures/Mailbox.png");
        bikeTexture = game.getAssetManager().get("Textures/bike.png");
        flowerPotTexture = game.getAssetManager().get("Textures/flowerpot1.png");
        drikerTexture = game.getAssetManager().get("Textures/drinker.png");
        flower1Texture = game.getAssetManager().get("Textures/flower1.png");
        flower2Texture = game.getAssetManager().get("Textures/flower2.png");
        flower3Texture = game.getAssetManager().get("Textures/flower3.png");
        rock1Texture = game.getAssetManager().get("Textures/rock1.png");
        rock2Texture = game.getAssetManager().get("Textures/rock2.png");
        stone1Texture = game.getAssetManager().get("Textures/stone1.png");
        stone2Texture = game.getAssetManager().get("Textures/stone2.png");
        scareCrowTexture = game.getAssetManager().get("Textures/Scarecrow.png");
        rottenGrass1Texture = game.getAssetManager().get("Textures/rotengrass1.png");
        rottenGrass2Texture = game.getAssetManager().get("Textures/rotengrass2.png");
        rottenGrass3Texture = game.getAssetManager().get("Textures/rotengrass3.png");
        woodPathTexture1 = game.getAssetManager().get("Textures/WoodPath.png");
        woodPathTexture2 = game.getAssetManager().get("Textures/WoodPath2.png");
        woodPathTexture3 = game.getAssetManager().get("Textures/WoodPath3.png");


        farmerTextures = new ArrayList<>(21);
        dogTextures = new ArrayList<>(21);
        chikenTextures = new ArrayList<>(7);
        pigTextures = new ArrayList<>(7);
        cowTextures = new ArrayList<>(7);
        fieldTextures = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            String farmer = String.format("Textures/Farmer/Farmer%d.png",i);
            farmerTextures.add((Texture) game.getAssetManager().get(farmer));
            String dog = String.format("Textures/Dog/Dog%d.png",i);
            dogTextures.add((Texture) game.getAssetManager().get(dog));
        }
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/empty.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Corn1.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Corn2.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Corn3.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Corn4.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Strawberry1.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Strawberry2.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Strawberry3.png"));
        fieldTextures.add((Texture)game.getAssetManager().get("Textures/field/Strawberry4.png"));



        for (int i = 0; i < 4; i++) {
            String chicken = String.format("Textures/Chicken/Chiken%d.png", i);
            chikenTextures.add((Texture) game.getAssetManager().get(chicken));
           /* String pig = String.format("Textures/Pig/Pig%d.png", i);
            // pigTextures.add((Texture) game.getAssetManager().get(pig));
            String cow = String.format("Textures/Cow/Cow%d.png", i);
            // cowTextures.add((Texture) game.getAssetManager().get(cow));

            */
        }

        Campo field = (Campo)this.game.getUsuario().getGranja().getInfraestructuras().get(Infraestructura.FIELD);

        fieldActor = new FieldActor(world, fieldTextures, new Vector2(10f, 5.5f), game.getSoundFactory(),field);
        homeActor = new HomeActor(world, houseTexture, new Vector2(26f, 15f), game.getSoundFactory());
        chickenBuildingActor = new AnimalsBuildingActor(world, chickenCoopTexture, new Vector2(16f, 15f), game.getSoundFactory(), TipoRecurso.CHICKEN);
        pigsBuildingActor = new AnimalsBuildingActor(world, pigstyTexture, new Vector2(10f, 15f), game.getSoundFactory(), TipoRecurso.PIG);
        cowsBuildingActor = new AnimalsBuildingActor(world, barnTexture, new Vector2(4f, 15f), game.getSoundFactory(), TipoRecurso.COW);
        shopActor = new ShopActor(world, shopTexture, new Vector2(25.75f, 3f), game.getSoundFactory());
        storageActor = new StorageActor(world, storeTexture, new Vector2(26f, 9f), game.getSoundFactory());
        farmerActor = new FarmerActor(world,farmerTextures,new Vector2(10, 8), game.getSoundFactory());
        truckActor = new TruckActor(world, truck1Texture,truck2Texture, new Vector2(21, 25), game.getSoundFactory());
        dogActor = new DogActor(world, dogTextures, new Vector2(23,13), game.getSoundFactory());
        chickenPulseActor = new PulseActor(world, chikenTextures, new Vector2(15f, 15.5f) );

        grondImage = new ImageRepeart(groundTexture,-20,-10,60,40, 2f);
        roadImage = new ImageClampToEdge(roadTexture,19.25f,-3,3.5f,25f);

        trees = new ArrayList<>();
        decorations = new ArrayList<>();
        roads = new ArrayList<>();
        trees.add(new ImageClampToEdge(tree1Texture, 18.25f, 8, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree2Texture, 22f, 13f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree1Texture, 12f, 16f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree2Texture, 17f, 15f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree1Texture, 8f, 16f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree2Texture, 28f, 15.5f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree1Texture, 30f, 9f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree3Texture, 30.3f, 5f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree1Texture, 29.75f, 2f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree3Texture, 30.75f, 13f, 1.75f, 3.5f));
        trees.add(new ImageClampToEdge(tree3Texture, 0.3f, 15f, 1.75f, 3.5f));
        decorations.add(new ImageClampToEdge(bushTexture, 0.5f, 12f, 1f, 1f));
        decorations.add(new ImageClampToEdge(bush2Texture, 13f, 12.125f, 1f, 1f));
        decorations.add(new ImageClampToEdge(bush2Texture, 0.5f, 0.5f, 1f, 1f));
        decorations.add(new ImageClampToEdge(bushTexture, 27.3f, 5.5f, 1f, 1f));
        decorations.add(new ImageClampToEdge(mailboxTexture, 28.5f, 12.5f, 0.5f, 1.2f));
        decorations.add(new ImageClampToEdge(bikeTexture, 22.4f, 16.65f, 2f, 1.2f));
        decorations.add(new ImageClampToEdge(flowerPotTexture, 8.75f, 12.2f, 0.75f, 1f));
        decorations.add(new ImageClampToEdge(flower1Texture, 0.5f, 5f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower1Texture, 30.6f, 16.5f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower2Texture, 18.75f, 3f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower1Texture, 27.5f, 4.1f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower2Texture, 15f, 10.3f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower3Texture, 29.75f, 14f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(flower3Texture, 11f, 0.15f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(rock1Texture, 0.1f, 3f, 1f, 1f));
        decorations.add(new ImageClampToEdge(rock2Texture, 29.5f, 0.5f, 1f, 1f));
        decorations.add(new ImageClampToEdge(stone1Texture, 18.3f, 4f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(stone1Texture, 18.7f, 15f, 0.6f, 0.5f));
        decorations.add(new ImageClampToEdge(stone2Texture, 5f, 10.2f, 0.7f, 0.7f));
        decorations.add(new ImageClampToEdge(stone2Texture, 11f, 10.2f, 0.7f, 0.7f));
        decorations.add(new ImageClampToEdge(stone2Texture, 29f, 8f, 0.7f, 0.7f));
        decorations.add(new ImageClampToEdge(drikerTexture, 16.5f, 12.1f, 1.2f, 0.7f));
        decorations.add(new ImageClampToEdge(scareCrowTexture, 18.5f, 5f, 0.85f, 1.7f));
        decorations.add(new ImageClampToEdge(rottenGrass1Texture, 1f, 9f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(rottenGrass2Texture, 7f, 13.2f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(rottenGrass3Texture, 26f, 6f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(rottenGrass2Texture, 6f, 0.1f, 0.5f, 0.5f));
        decorations.add(new ImageClampToEdge(rottenGrass3Texture, 17.8f, 0.4f, 0.5f, 0.5f));
        roads.add(new ImageClampToEdge(woodPathTexture1, 22.5f, 11f, 4.9f, 0.7f));
        roads.add(new ImageClampToEdge(woodPathTexture1, 14.50f, 11f, 4.9f, 0.7f));
        roads.add(new ImageClampToEdge(woodPathTexture1, 9.6f, 11f, 4.9f, 0.7f));
        roads.add(new ImageClampToEdge(woodPathTexture1, 4.75f, 11f, 4.9f, 0.7f));
        roads.add(new ImageClampToEdge(woodPathTexture3, 2.1f, 11f, 2.8f, 0.7f));
        roads.add(new ImageClampToEdge(woodPathTexture2, 2.1f, 11.7f, 0.8f, 0.8f));
        roads.add(new ImageClampToEdge(woodPathTexture2, 8.2f, 11.7f, 0.8f, 0.8f));
        roads.add(new ImageClampToEdge(woodPathTexture2, 14.35f, 11.7f, 0.8f, 0.8f));
        roads.add(new ImageClampToEdge(woodPathTexture2, 25.8f, 11.7f, 0.8f, 0.8f));
        roads.add(new ImageClampToEdge(woodPathTexture2, 26.6f, 11.7f, 0.8f, 0.8f));





        stage.addActor(grondImage);
        stage.addActor(roadImage);
        stage.addActor(fieldActor);
        for (ImageClampToEdge road: roads) {
            stage.addActor(road);
        }
        for (ImageClampToEdge tree: trees) {
            stage.addActor(tree);
        }

        stage.addActor(homeActor);
        stage.addActor(chickenBuildingActor);
        stage.addActor(pigsBuildingActor);
        stage.addActor(cowsBuildingActor);

        stage.addActor(truckActor);



        stage.addActor(shopActor);

        stage.addActor(dogActor); //It is important to implement before StorageActor
        stage.addActor(storageActor);
        for (ImageClampToEdge bush: decorations) {
            stage.addActor(bush);
        }
        stage.addActor(farmerActor);
       // stage.addActor(chickenPulseActor);

        this.game.getSoundFactory().playGameMusic();

        storageActor.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                GameScreen.this.game.showStorageScreen();
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
                GameScreen.this.game.showAnimalsScreen(cowsBuildingActor.getAnimalType());
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
                GameScreen.this.game.showAnimalsScreen(chickenBuildingActor.getAnimalType());
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
                GameScreen.this.game.showAnimalsScreen(pigsBuildingActor.getAnimalType());
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
                GameScreen.this.game.showOptionsScreen();
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
                GameScreen.this.game.showShopScreen();
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
                GameScreen.this.game.showFieldScreen();
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

        this.timeSinceLastFieldControl += delta;
        this.timeSinceLastRNDSound += delta;
        if ( this.timeToBeat < this.timeSinceLastRNDSound){
            this.timeSinceLastRNDSound = 0;
            this.timeToBeat = (float) (Math.random() * 5) + 5;
            game.getSoundFactory().playRDNSound();
        }
        //solo controla el estado del campo cada segundo , ahorrando muchos recursos
        if (timeSinceLastFieldControl > 1){
            this.game.getFieldController().controlField();
        }


        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);

        stage.draw();

    }

    @Override
    public void dispose() {
        Texture[] allTextures = { barnTexture, chickenCoopTexture, houseTexture, pigstyTexture, shopTexture, storeTexture,
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
    public void disableAll(boolean enableDisable){

    }
    public void actions(int actionIndex){

    }

}
