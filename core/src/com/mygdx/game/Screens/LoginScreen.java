package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class LoginScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, loginButton, registerButton;
    private Window areaT;
    private Label nameTitleLabel, passTitleLabel;
    private TextField nameTextField, passTextField;


    public LoginScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);


        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", skin, "custom");
        loginButton = new TextButton("Entrar", skin, "custom");
        registerButton = new TextButton("Registrar", skin);
        areaT = new Window("", skin, "dialog");
        nameTitleLabel = new Label("Usuario",skin, "required");
        passTitleLabel = new Label("Passworld",skin, "required");
        nameTextField = new TextField("", skin);
        passTextField = new TextField("", skin);



        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE);
        loginButton.getLabel().setFontScale(Constants.FONT_SIZE);
        registerButton.getLabel().setFontScale(Constants.FONT_SIZE);
        nameTitleLabel.setFontScale(Constants.FONT_SIZE);
        passTitleLabel.setFontScale(Constants.FONT_SIZE);


        TextField.TextFieldStyle textFieldStyle = nameTextField.getStyle();
        textFieldStyle.font.getData().setScale(Constants.FONT_SIZE);
        nameTextField.setStyle(textFieldStyle);
        passTextField.setStyle(textFieldStyle);


        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LoginScreen.this.game.showMenuScreen();
            }
        });
        //funcionalidades
        loginButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(LoginScreen.this.game.validate(nameTextField.getText(), passTextField.getText())){
                    LoginScreen.this.game.setUserLogged(true);
                    LoginScreen.this.game.saveUserPreferences(nameTextField.getText(), passTextField.getText());
                }
                LoginScreen.this.game.showGameScreen();
               // nameTextField.setText(LoginScreen.this.game.validate(nameTextField.getText(), passTextField.getText())+"");
            }
        });


        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        loginButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        registerButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        areaT.setSize(Constants.DEVIDE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.75f);
        nameTitleLabel.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.10f);
        passTitleLabel.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.1f);
        nameTextField.setSize(Constants.DEVIDE_WIDTH *0.38f, Constants.DEVICE_HEIGHT * 0.1f);
        passTextField.setSize(Constants.DEVIDE_WIDTH *0.38f, Constants.DEVICE_HEIGHT * 0.1f);


        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVIDE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        nameTitleLabel.setPosition(Constants.DEVIDE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.7f);
        areaT.setPosition(Constants.DEVIDE_WIDTH * 0.25f, Constants.DEVICE_HEIGHT * 0.10f);

        passTitleLabel.setPosition(Constants.DEVIDE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.45f);
        nameTextField.setPosition(Constants.DEVIDE_WIDTH * 0.31f, Constants.DEVICE_HEIGHT * 0.575f);
        passTextField.setPosition(Constants.DEVIDE_WIDTH * 0.31f, Constants.DEVICE_HEIGHT * 0.325f);
        loginButton.setPosition(Constants.DEVIDE_WIDTH * 0.32f, Constants.DEVICE_HEIGHT * 0.15f);
        registerButton.setPosition(Constants.DEVIDE_WIDTH * 0.53f, Constants.DEVICE_HEIGHT * 0.15f);

        //estados
        areaT.setTouchable(Touchable.disabled);
        nameTitleLabel.setAlignment(Align.center);
        passTitleLabel.setAlignment(Align.center);
        nameTextField.setMessageText("  Usuario");
        passTextField.setMessageText("  Passworld");
        registerButton.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());

        //Se añaden los elementos
        stage.addActor(goBackButton);

        stage.addActor(areaT);
        stage.addActor(nameTitleLabel);
        stage.addActor(passTitleLabel);
        stage.addActor(nameTextField);
        stage.addActor(passTextField);
        stage.addActor(loginButton);
        stage.addActor(registerButton);




    }


    @Override
    public void show() {
        stage.setDebugAll(false); // On true se renderizan los bordes verdes de los actores e imágenes
        Gdx.input.setInputProcessor(stage);


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