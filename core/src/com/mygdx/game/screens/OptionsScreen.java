package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.images.ImageClampToEdge;
import com.mygdx.game.MainGame;

public class OptionsScreen extends BaseScreen {
    private Stage stage;
    private World world;
    private Skin skin, glassSkin;
    private TextButton goBackButton, goMenuButton, addNeighbour, closeSession;
    private Window area1, area2, area3;
    private Label idLabel, userLabel, farmNameTitleLabel, farmNameLabel, musicVolumeLabel, effectsVolumeLabel, musicLabel, effectsLabel;
    private Button musicCheckBox, effectsCheckBox;
    private Slider musicVolumeSlider, effectsVolumeSlider;
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
        this.glassSkin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));

        // inicialización de los elementos
        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/optionBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);
        goBackButton = new TextButton("Al Juego", glassSkin, "big");
        goMenuButton = new TextButton("Menu", glassSkin, "big");
        addNeighbour = new TextButton("Buscar vecino", glassSkin, "big");
        closeSession = new TextButton("Cerrar sesion", glassSkin, "big");
        idLabel = new Label(String.format("Id: 00000%s", this.game.getUsuario().getId()), glassSkin, "big");
        userLabel = new Label(String.format("%s %s", this.game.getUsuario().getNombre(), this.game.getUsuario().getApellidos() ), glassSkin, "big");
        farmNameTitleLabel = new Label("Nombre de la granja:", glassSkin, "big");
        musicVolumeLabel = new Label("Volumen", glassSkin, "big");
        effectsVolumeLabel = new Label("Volumen", glassSkin, "big");
        musicLabel = new Label("Musica", glassSkin, "big");
        effectsLabel = new Label("Efectos", glassSkin, "big");
        farmNameLabel = new Label(this.game.getUsuario().getGranja().getNombre(), glassSkin, "big-blue");
        area1 = new Window("", skin,"dialog");
        area2 = new Window("", skin,"dialog");
        area3 = new Window("", skin,"dialog");
        effectsCheckBox = new Button(skin,"switch_inverse");
        onStyle = effectsCheckBox.getStyle();
        musicCheckBox = new Button(skin,"switch");
        offStyle = musicCheckBox.getStyle();
        musicVolumeSlider = new Slider(0,1,0.01f, false, glassSkin,"default-horizontal");
        effectsVolumeSlider = new Slider(0,1,0.01f, false, glassSkin,"default-horizontal");
        neighbourTextField = new TextField("", glassSkin);

        // Tamaño de la fuente
        closeSession.getLabel().setFontScale(Constants.FONT_SIZE * 0.225f);
        addNeighbour.getLabel().setFontScale(Constants.FONT_SIZE * 0.225f);
        goMenuButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        idLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        userLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        farmNameTitleLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        farmNameLabel.setFontScale(Constants.FONT_SIZE* 0.25f);
        musicVolumeLabel.setFontScale(Constants.FONT_SIZE* 0.25f);
        effectsVolumeLabel.setFontScale(Constants.FONT_SIZE* 0.25f);
        musicLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        effectsLabel.setFontScale(Constants.FONT_SIZE*  0.25f);
        TextField.TextFieldStyle textFieldStyle = neighbourTextField.getStyle();
        textFieldStyle.font.getData().setScale(Constants.FONT_SIZE * 0.33f);
        neighbourTextField.setStyle(textFieldStyle);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().playPickUp();
                OptionsScreen.this.game.showGameScreen();

            }
        });
        goMenuButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().playPickUp();
                OptionsScreen.this.game.showMenuScreen();
            }
        });
        closeSession.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().playPickUp();
                OptionsScreen.this.game.setUserLogged(false);
                OptionsScreen.this.game.saveUserPreferences("","");
                OptionsScreen.this.game.showLoginScreen();
            }
        });
        musicCheckBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().playSelect2();
                OptionsScreen.this.game.getSoundFactory().setMusic(!OptionsScreen.this.game.getSoundFactory().isMusic());
                //save preference
            }
        });
        effectsCheckBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().playSelect2();
                OptionsScreen.this.game.getSoundFactory().setEffects(!OptionsScreen.this.game.getSoundFactory().isEffects());
                //save preference
            }
        });
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().setMusicVolume(musicVolumeSlider.getValue());
                //save preference
            }
        });
        effectsVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                OptionsScreen.this.game.getSoundFactory().setEffectsVolume(effectsVolumeSlider.getValue());
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
        area3.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.285f);
        idLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.08f);
        userLabel.setSize(Constants.DEVICE_WIDTH *0.35f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameTitleLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        farmNameLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        musicVolumeLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        effectsVolumeLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        musicLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        effectsLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        musicCheckBox.setSize(Constants.DEVICE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        effectsCheckBox.setSize(Constants.DEVICE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        musicVolumeSlider.setSize(Constants.DEVICE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.08f);
        effectsVolumeSlider.setSize(Constants.DEVICE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.08f);
        neighbourTextField.setSize(Constants.DEVICE_WIDTH *0.625f, Constants.DEVICE_HEIGHT * 0.08f);

        //posiciones de los elementos
        area1.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.68f);
        area2.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.48f);
        area3.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.185f);
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.33f, Constants.DEVICE_HEIGHT * 0.05f);
        addNeighbour.setPosition(Constants.DEVICE_WIDTH * 0.76f, Constants.DEVICE_HEIGHT * 0.525f);
        goMenuButton.setPosition(Constants.DEVICE_WIDTH * 0.57f, Constants.DEVICE_HEIGHT * 0.05f);
        closeSession.setPosition(Constants.DEVICE_WIDTH * 0.76f, Constants.DEVICE_HEIGHT * 0.725f);
        idLabel.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.8875f);
        userLabel.setPosition(Constants.DEVICE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.8875f);
        farmNameTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.7375f);
        farmNameLabel.setPosition(Constants.DEVICE_WIDTH * 0.38f, Constants.DEVICE_HEIGHT * 0.7375f);
        musicVolumeLabel.setPosition(Constants.DEVICE_WIDTH * 0.3f, Constants.DEVICE_HEIGHT * 0.3375f);
        effectsVolumeLabel.setPosition(Constants.DEVICE_WIDTH * 0.3f, Constants.DEVICE_HEIGHT * 0.2475f);
        musicVolumeSlider.setPosition(Constants.DEVICE_WIDTH * 0.42f, Constants.DEVICE_HEIGHT * 0.3375f);
        effectsVolumeSlider.setPosition(Constants.DEVICE_WIDTH * 0.42f, Constants.DEVICE_HEIGHT * 0.2475f);
        musicLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.3375f);
        effectsLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.2475f);
        musicCheckBox.setPosition(Constants.DEVICE_WIDTH * 0.20f, Constants.DEVICE_HEIGHT * 0.3513f);
        effectsCheckBox.setPosition(Constants.DEVICE_WIDTH * 0.2f, Constants.DEVICE_HEIGHT * 0.2613f);
        neighbourTextField.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.535f);

        //estados
        goBackButton.setColor(Color.GREEN);
        goMenuButton.setColor(Color.ROYAL);
        area1.setTouchable(Touchable.disabled);
        area2.setTouchable(Touchable.disabled);
        area3.setTouchable(Touchable.disabled);
        userLabel.setAlignment(Align.right);
        area1.setColor(1,1,1,0.9f);
        area2.setColor(1,1,1,0.9f);
        area3.setColor(1,1,1,0.9f);
        neighbourTextField.setMessageText(" Id usuario");
        if (!this.game.isUserLogged()){
            idLabel.setText("");
            userLabel.setText("");
        }

        //Se añaden los elementos
        stage.addActor(backgroundImage);
        stage.addActor(area2);
        stage.addActor(area1);
        stage.addActor(area3);
        stage.addActor(goBackButton);
        stage.addActor(goMenuButton);
        stage.addActor(closeSession);
        stage.addActor(addNeighbour);
        stage.addActor(idLabel);
        stage.addActor(userLabel);
        stage.addActor(farmNameTitleLabel);
        stage.addActor(farmNameLabel);
        stage.addActor(musicVolumeLabel);
        stage.addActor(effectsVolumeLabel);
        stage.addActor(musicLabel);
        stage.addActor(effectsLabel);
        stage.addActor(musicCheckBox);
        stage.addActor(effectsCheckBox);
        stage.addActor(musicVolumeSlider);
        stage.addActor(effectsVolumeSlider);
        stage.addActor(neighbourTextField);
    }

    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);

        //actualizacion estados segn preferencias
        if (this.game.getSoundFactory().isMusic()) musicCheckBox.setStyle(onStyle);
        if (!this.game.getSoundFactory().isEffects()) effectsCheckBox.setStyle(offStyle);
        musicVolumeSlider.setValue(this.game.getSoundFactory().getMusicVolume());
        effectsVolumeSlider.setValue(this.game.getSoundFactory().getEffectsVolume());
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
    public void disableAll(boolean enableDisable){}
    public void actions(int actionIndex){}
}