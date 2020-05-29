package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.Images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class OptionsScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, goMenuButton, addNeighbour, closeSession;
    private Window area1, area2, area3, area4;
    private Label idLabel, userLabel, farmNameTitleLabel, farmNameLabel, volumeLabel, musicLabel, effectsLabel;
    private Button musicCheckBox, effectsCheckBox;
    private Slider volumeSlider;
    private TextField neighbourTextField;
    private Button.ButtonStyle onStyle, offStyle;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;


    public OptionsScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);



        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/optionBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);
        goBackButton = new TextButton("Al Juego", skin, "custom");
        goMenuButton = new TextButton("Menu", skin);
        addNeighbour = new TextButton("Buscar vecino", skin);
        closeSession = new TextButton("Cerrar sesion", skin);

        idLabel = new Label(String.format("Id: 000%s", this.game.getUsuario().getId()), skin, "required");
        userLabel = new Label(String.format("%s %s", this.game.getUsuario().getNombre(), this.game.getUsuario().getApellidos() ), skin, "required");
        farmNameTitleLabel = new Label("Nombre de la granja:", skin, "custom_grey");
        volumeLabel = new Label("Volumen", skin, "custom_grey");
        musicLabel = new Label("Musica", skin, "custom_grey");
        effectsLabel = new Label("Efectos", skin, "custom_grey");
        farmNameLabel = new Label(this.game.getUsuario().getGranja().getNombre(), skin, "custom_blue");
        area1 = new Window("", skin,"dialog");
        area2 = new Window("", skin,"dialog");
        area3 = new Window("", skin,"dialog");
        area4 = new Window("", skin,"dialog");


        effectsCheckBox = new Button(skin,"switch_inverse");
        onStyle = effectsCheckBox.getStyle();
        musicCheckBox = new Button(skin,"switch");
        offStyle = musicCheckBox.getStyle();


        volumeSlider = new Slider(0,1,0.01f, false, skin,"default-horizontal");
        neighbourTextField = new TextField("", skin);

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

        TextField.TextFieldStyle textFieldStyle = neighbourTextField.getStyle();
        textFieldStyle.font.getData().setScale(Constants.FONT_SIZE);
        neighbourTextField.setStyle(textFieldStyle);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.showGameScreen();
            }
        });
        goMenuButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.showMenuScreen();
            }
        });
        closeSession.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.setUserLogged(false);
                OptionsScreen.this.game.saveUserPreferences("","");
                OptionsScreen.this.game.showLoginScreen();
            }
        });
        musicCheckBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().setMusic(!OptionsScreen.this.game.getSoundFactory().isMusic());
                //save preference
            }
        });
        effectsCheckBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().setEffects(!OptionsScreen.this.game.getSoundFactory().isEffects());
                //save preference
            }
        });

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().setVolume(volumeSlider.getValue());
                //save preference
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        addNeighbour.setSize(Constants.DEVICE_WIDTH * 0.17f, Constants.DEVICE_HEIGHT *0.10f);
        closeSession.setSize(Constants.DEVICE_WIDTH * 0.17f, Constants.DEVICE_HEIGHT *0.10f);
        goMenuButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        area1.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area2.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area3.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area4.setSize(Constants.DEVICE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.19f);
        idLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.08f);
        userLabel.setSize(Constants.DEVICE_WIDTH *0.35f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameTitleLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        volumeLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        musicLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        effectsLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        musicCheckBox.setSize(Constants.DEVICE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        effectsCheckBox.setSize(Constants.DEVICE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        volumeSlider.setSize(Constants.DEVICE_WIDTH *0.6f, Constants.DEVICE_HEIGHT * 0.08f);
        neighbourTextField.setSize(Constants.DEVICE_WIDTH *0.625f, Constants.DEVICE_HEIGHT * 0.08f);


        //posiciones de los elementos

        area1.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.68f);
        area2.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.48f);
        area3.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.28f);
        area4.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.08f);
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.59f, Constants.DEVICE_HEIGHT * 0.125f);
        addNeighbour.setPosition(Constants.DEVICE_WIDTH * 0.76f, Constants.DEVICE_HEIGHT * 0.525f);
        goMenuButton.setPosition(Constants.DEVICE_WIDTH * 0.77f, Constants.DEVICE_HEIGHT * 0.125f);
        closeSession.setPosition(Constants.DEVICE_WIDTH * 0.76f, Constants.DEVICE_HEIGHT * 0.725f);
        idLabel.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.8875f);
        userLabel.setPosition(Constants.DEVICE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.8875f);
        farmNameTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.7375f);
        farmNameLabel.setPosition(Constants.DEVICE_WIDTH * 0.38f, Constants.DEVICE_HEIGHT * 0.7375f);
        volumeLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.3375f);
        volumeSlider.setPosition(Constants.DEVICE_WIDTH * 0.28f, Constants.DEVICE_HEIGHT * 0.3375f);

        musicLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.1375f);
        effectsLabel.setPosition(Constants.DEVICE_WIDTH * 0.32f, Constants.DEVICE_HEIGHT * 0.1375f);
        musicCheckBox.setPosition(Constants.DEVICE_WIDTH * 0.20f, Constants.DEVICE_HEIGHT * 0.15125f);
        effectsCheckBox.setPosition(Constants.DEVICE_WIDTH * 0.44f, Constants.DEVICE_HEIGHT * 0.15125f);
        neighbourTextField.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.535f);




        //estados

        area1.setTouchable(Touchable.disabled);
        area2.setTouchable(Touchable.disabled);
        area3.setTouchable(Touchable.disabled);
        area4.setTouchable(Touchable.disabled);
        userLabel.setAlignment(Align.right);
        volumeSlider.getStyle().knob.setMinHeight(Constants.DEVICE_WIDTH * 0.02f);
        volumeSlider.getStyle().knob.setMinWidth(Constants.DEVICE_WIDTH * 0.02f);
        volumeSlider.getStyle().knobDown.setMinHeight(Constants.DEVICE_WIDTH * 0.02f);
        volumeSlider.getStyle().knobDown.setMinWidth(Constants.DEVICE_WIDTH * 0.02f);

        addNeighbour.setStyle(StyleFactory.BLUE_TEXT_BUTTON_STYLE());
        closeSession.setStyle(StyleFactory.BLUE_TEXT_BUTTON_STYLE());
        goMenuButton.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        neighbourTextField.setMessageText(" Id usuario");




        //Se añaden los elementos
        stage.addActor(backgroundImage);
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
        stage.addActor(volumeSlider);
        stage.addActor(neighbourTextField);

    }


    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //actualizacion estados segn preferencias
        if (this.game.getSoundFactory().isMusic()) musicCheckBox.setStyle(onStyle);
        if (!this.game.getSoundFactory().isEffects()) effectsCheckBox.setStyle(offStyle);
        volumeSlider.setValue(this.game.getSoundFactory().getVolume());


    }

    @Override
    public void hide() {
        this.game.saveSoundPreferences();
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
    public void disableAll(boolean enableDisable){

    }
    public void actions(int actionIndex){

    }
}