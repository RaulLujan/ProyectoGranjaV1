package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;

public class OptionsScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, goMenuButton, addNeighbour, closeSession;
    private Window area1, area2, area3, area4;
    private Label idLabel, userLabel, farmNameTitleLabel, farmNameLabel, volumeLabel, musicLabel, effectsLabel;
    private Button musicCheckBox, effectsCheckBox;
    private Slider volumeSlider;



    public OptionsScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);


        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos
        goBackButton = new TextButton("Al Juego", skin, "custom");
        goMenuButton = new TextButton("Menu", skin);
        addNeighbour = new TextButton("Buscar vecino", skin);
        closeSession = new TextButton("Cerrar sesion", skin);

        idLabel = new Label("Id usuario: 123456789", skin, "required");
        userLabel = new Label("Manuel Gonzalez", skin, "required");
        farmNameTitleLabel = new Label("Granja:", skin, "custom_green");
        volumeLabel = new Label("Volumen", skin, "custom_green");
        musicLabel = new Label("Musica", skin, "custom_green");
        effectsLabel = new Label("Efectos", skin, "custom_green");
        farmNameLabel = new Label("La Ponderosa", skin, "custom_blue");


        area1 = new Window("", skin,"dialog");
        area2 = new Window("", skin,"dialog");
        area3 = new Window("", skin,"dialog");
        area4 = new Window("", skin,"dialog");

        musicCheckBox = new Button(skin,"switch");
        effectsCheckBox = new Button(skin,"switch");

        volumeSlider = new Slider(0,1,0.05f, false, skin,"default-horizontal");

        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        goMenuButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        addNeighbour.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        closeSession.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        idLabel.setFontScale(Constants.FONT_SIZE);
        userLabel.setFontScale(Constants.FONT_SIZE);
        farmNameTitleLabel.setFontScale(Constants.FONT_SIZE);
        farmNameLabel.setFontScale(Constants.FONT_SIZE);
        volumeLabel.setFontScale(Constants.FONT_SIZE);
        musicLabel.setFontScale(Constants.FONT_SIZE);
        effectsLabel.setFontScale(Constants.FONT_SIZE);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.showGameScreen();
            }
        });


        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //example : pbrShader.metallicValue=metallicS.getValue();
                //Slider Code
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        addNeighbour.setSize(Constants.DEVIDE_WIDTH  * 0.17f, Constants.DEVICE_HEIGHT *0.10f);
        closeSession.setSize(Constants.DEVIDE_WIDTH  * 0.17f, Constants.DEVICE_HEIGHT *0.10f);
        goMenuButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        area1.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.15f);
        area2.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.15f);
        area3.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.15f);
        area4.setSize(Constants.DEVIDE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.15f);
        idLabel.setSize(Constants.DEVIDE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.08f);
        userLabel.setSize(Constants.DEVIDE_WIDTH *0.35f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameTitleLabel.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameLabel.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        volumeLabel.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        musicLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        effectsLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        musicCheckBox.setSize(Constants.DEVIDE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        effectsCheckBox.setSize(Constants.DEVIDE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        //volumeSlider.setSize(Constants.DEVIDE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.08f);


        //posiciones de los elementos

        area1.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.7f);
        area2.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.5f);
        area3.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.3f);
        area4.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.1f);
        goBackButton.setPosition(Constants.DEVIDE_WIDTH * 0.59f, Constants.DEVICE_HEIGHT * 0.125f);
        addNeighbour.setPosition(Constants.DEVIDE_WIDTH * 0.77f, Constants.DEVICE_HEIGHT * 0.525f);
        goMenuButton.setPosition(Constants.DEVIDE_WIDTH * 0.77f, Constants.DEVICE_HEIGHT * 0.125f);
        closeSession.setPosition(Constants.DEVIDE_WIDTH * 0.77f, Constants.DEVICE_HEIGHT * 0.725f);
        idLabel.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.8875f);
        userLabel.setPosition(Constants.DEVIDE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.8875f);
        farmNameTitleLabel.setPosition(Constants.DEVIDE_WIDTH * 0.2f, Constants.DEVICE_HEIGHT * 0.7375f);
        farmNameLabel.setPosition(Constants.DEVIDE_WIDTH * 0.33f, Constants.DEVICE_HEIGHT * 0.7375f);
        volumeLabel.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.3375f);
       // volumeSlider.setPosition(Constants.DEVIDE_WIDTH * 0.3f, Constants.DEVICE_HEIGHT * 0.3375f);

        musicLabel.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.1375f);
        effectsLabel.setPosition(Constants.DEVIDE_WIDTH * 0.32f, Constants.DEVICE_HEIGHT * 0.1375f);
        musicCheckBox.setPosition(Constants.DEVIDE_WIDTH * 0.20f, Constants.DEVICE_HEIGHT * 0.15125f);
        effectsCheckBox.setPosition(Constants.DEVIDE_WIDTH * 0.44f, Constants.DEVICE_HEIGHT * 0.15125f);




        //estados

        area1.setTouchable(Touchable.disabled);
        area2.setTouchable(Touchable.disabled);
        area3.setTouchable(Touchable.disabled);
        area4.setTouchable(Touchable.disabled);
        userLabel.setAlignment(Align.right);
        volumeSlider.scaleBy(3);


        //scaling Slider
        Container<Slider> container=new Container<Slider>(volumeSlider);
        container.setTransform(true);   // for enabling scaling and rotation
        container.size(Constants.DEVIDE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.08f);
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
        container.setPosition(Constants.DEVIDE_WIDTH * 0.55f, Constants.DEVICE_HEIGHT * 0.3775f);
        container.setScale(1.5f);  //scale according to your requirement






        //Se añaden los elementos
        stage.addActor(area2);
        stage.addActor(area1);
        stage.addActor(area3);
        stage.addActor(area4);
        stage.addActor(goBackButton);
        stage.addActor(goMenuButton);
        stage.addActor(closeSession);
        stage.addActor(addNeighbour);
        stage.addActor(idLabel);
        stage.addActor(userLabel);
        stage.addActor(farmNameTitleLabel);
        stage.addActor(farmNameLabel);
        stage.addActor(volumeLabel);
        stage.addActor(musicLabel);
        stage.addActor(effectsLabel);
        stage.addActor(musicCheckBox);
        stage.addActor(effectsCheckBox);
        stage.addActor(container);
        //stage.addActor(volumeSlider);

    }


    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
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
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.29f);
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