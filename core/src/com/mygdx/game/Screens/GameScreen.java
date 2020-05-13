package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.AnimalsBuilding;
import com.mygdx.game.Actors.Field;
import com.mygdx.game.Actors.Home;
import com.mygdx.game.Actors.Shop;
import com.mygdx.game.Actors.Storage;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;

    //actors
    private Home homeActor;
    private Field fieldActor;
    private Shop ShopActor;
    private AnimalsBuilding chickenBuildingActor;
    private Storage StorageActor;

    //Textures
    private Texture fieldTexture;

    public GameScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        stage.setDebugAll(true); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);


        fieldTexture = game.getAssetManager().get("badlogic.jpg");

        fieldActor = new Field(world, fieldTexture, new Vector2(1f, 1f), game.getSoundFactory());
        stage.addActor(fieldActor);

    }

    @Override
    public void hide() {
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(1f, 0.0f, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);

        stage.getCamera().position.set(fieldActor.getX(), fieldActor.getX(), stage.getCamera().position.z);



        stage.draw();

    }

    @Override
    public void dispose() {
        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }

}
