package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.Images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class LoginScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin glassSkin, skin;

    private TextButton goBackButton, loginButton, registerButton;
    private Window areaT;
    private Label nameTitleLabel, passTitleLabel;
    private TextField nameTextField, passTextField;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;


    public LoginScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);


        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));
        this.glassSkin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/loginBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", glassSkin);
        loginButton = new TextButton("Entrar", glassSkin);
        registerButton = new TextButton("Registrar", glassSkin);
        areaT = new Window("", skin, "dialog");
        nameTitleLabel = new Label("Usuario", glassSkin, "black");
        passTitleLabel = new Label("Passworld", glassSkin, "black");
        nameTextField = new TextField("", glassSkin);
        passTextField = new TextField("", glassSkin);



        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(goBackButton.getLabel().getFontScaleX()*0.8f);
        loginButton.getLabel().setFontScale(loginButton.getLabel().getFontScaleX()*0.8f);
        registerButton.getLabel().setFontScale(registerButton.getLabel().getFontScaleX()*0.8f);

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
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        loginButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        registerButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        areaT.setSize(Constants.DEVICE_WIDTH *0.5f, Constants.DEVICE_HEIGHT * 0.75f);
        nameTitleLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.10f);
        passTitleLabel.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.1f);
        nameTextField.setSize(Constants.DEVICE_WIDTH *0.38f, Constants.DEVICE_HEIGHT * 0.1f);
        passTextField.setSize(Constants.DEVICE_WIDTH *0.38f, Constants.DEVICE_HEIGHT * 0.1f);


        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        nameTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.7f);
        areaT.setPosition(Constants.DEVICE_WIDTH * 0.25f, Constants.DEVICE_HEIGHT * 0.10f);

        passTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.45f);
        nameTextField.setPosition(Constants.DEVICE_WIDTH * 0.31f, Constants.DEVICE_HEIGHT * 0.575f);
        passTextField.setPosition(Constants.DEVICE_WIDTH * 0.31f, Constants.DEVICE_HEIGHT * 0.325f);
        loginButton.setPosition(Constants.DEVICE_WIDTH * 0.32f, Constants.DEVICE_HEIGHT * 0.15f);
        registerButton.setPosition(Constants.DEVICE_WIDTH * 0.53f, Constants.DEVICE_HEIGHT * 0.15f);

        //estados
        areaT.setTouchable(Touchable.disabled);
        nameTitleLabel.setAlignment(Align.center);
        passTitleLabel.setAlignment(Align.center);
        nameTextField.setMessageText("  Usuario");
        passTextField.setMessageText("  Passworld");
        passTextField.setPasswordMode(true);
        passTextField.setPasswordCharacter('*');
        goBackButton.setColor(Color.GREEN);
        registerButton.setColor(Color.PURPLE);
        areaT.setColor(1,1,1,0.85f);

        //Se añaden los elementos
        stage.addActor(backgroundImage);
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
    public void disableAll(boolean enableDisable){

    }
    public void actions(int actionIndex){

    }
}