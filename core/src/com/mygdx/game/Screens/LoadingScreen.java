package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.TractorActor;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

public class LoadingScreen extends BaseScreen {


    private Stage stage;
    private World world;
    private TractorActor tractorActor;
    private Texture tractorTexture;

    public LoadingScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        stage.setDebugAll(true); // On true se renderizan los bordes verdes de los actores e imágenes

        tractorTexture = game.getAssetManager().get("Textures/Buildings/tractor.png");

        tractorActor = new TractorActor(world, tractorTexture, new Vector2(2,2) );
        stage.addActor(tractorActor);


    }

    @Override
    public void hide() {
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //movimiento del mundo
        stage.act();
        world.step(delta, 6, 2);


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
}
