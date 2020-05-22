package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

public class FieldScreen extends BaseScreen {

    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton play, exit;

    private GameScreen gameScreen;

    public FieldScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);



        // apariencia de los botones
        this.skin = new Skin(Gdx.files.internal("skins/skinWhite/uiskin.json"));



        // inicialización de los botones
        play = new TextButton("JUGAR", skin);

        // Tamaño de la fuente
        play.getLabel().setFontScale(Constants.FONT_SIZE);

        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.goBackMenuScreen();
            }
        });

        //disposición de tamaño y posiciones de los botones
        play.setSize(Constants.DEVIDE_WIDTH / 5, Constants.DEVICE_HEIGHT / 9);
        play.setPosition(Constants.DEVIDE_WIDTH * 0.65f, Constants.DEVICE_HEIGHT * 0.75f);

        // se añaden los botones
        stage.addActor(play);


    }

    @Override
    public void show() {
        stage.setDebugAll(true); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //se añaden funciones a cada botón



    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        stage.clear();
        world.dispose();
    }


    @Override
    public void render(float delta) {
        //limpieza de la pantalla
        Gdx.gl.glClearColor(0.2f, 0.8f, 0.2f, 1f);
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
}
