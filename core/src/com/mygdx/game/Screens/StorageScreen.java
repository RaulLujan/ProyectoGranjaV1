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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class StorageScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, button1, button2, button3, button4;
    private Window area1, area2, area3, area4;
    private Label fundsLabel;
    private Label depositNameLabel1, quantityTitleLabel1, quantityLabel1, resourcesTitleLabel1, resourcesLabel1;
    private Label depositNameLabel2, quantityTitleLabel2, quantityLabel2, resourcesTitleLabel2, resourcesLabel2;
    private Label depositNameLabel3, quantityTitleLabel3, quantityLabel3, resourcesTitleLabel3, resourcesLabel3;
    private Label depositNameLabel4, quantityTitleLabel4, quantityLabel4, resourcesTitleLabel4, resourcesLabel4;





    public StorageScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);




        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", skin, "custom");
        button1 = new TextButton("Expandir", skin);
        button2 = new TextButton("Expandir", skin);
        button3 = new TextButton("Expandir", skin);
        button4 = new TextButton("Expandir", skin);
        area1 = new Window("", skin,"dialog");
        area2 = new Window("", skin,"dialog");
        area3 = new Window("", skin,"dialog");
        area4 = new Window("", skin,"dialog");
        fundsLabel = new Label("Fondos: 1.450.654 $",skin, "required");
        depositNameLabel1 = new Label("Deposito de Agua",skin, "custom_green");
        depositNameLabel2 = new Label("Camara frigorifica",skin, "custom_green");
        depositNameLabel3 = new Label("Almacen general",skin, "custom_green");
        depositNameLabel4 = new Label("Granero",skin, "custom_green");
        quantityTitleLabel1 = new Label("Capacidad",skin, "required");
        quantityTitleLabel2 = new Label("Capacidad",skin, "required");
        quantityTitleLabel3 = new Label("Capacidad",skin, "required");
        quantityTitleLabel4 = new Label("Capacidad",skin, "required");
        quantityLabel1 = new Label("50.000",skin, "custom_blue");
        quantityLabel2 = new Label("10.000",skin, "custom_blue");
        quantityLabel3 = new Label("50.000",skin, "custom_blue");
        quantityLabel4 = new Label("30.000",skin, "custom_blue");
        resourcesTitleLabel1 = new Label("Recursos",skin, "required");
        resourcesTitleLabel2 = new Label("Recursos",skin, "required");
        resourcesTitleLabel3 = new Label("Recursos",skin, "required");
        resourcesTitleLabel4 = new Label("Recursos",skin, "required");
        resourcesLabel1 = new Label("Agua",skin, "custom_blue");
        resourcesLabel2 = new Label("Carne, Huevos, Leche",skin, "custom_blue");
        resourcesLabel3 = new Label("Abono, Herbicida",skin, "custom_blue");
        resourcesLabel4 = new Label("Maiz, Fresas, Patatas",skin, "custom_blue");





        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE);
        button1.getLabel().setFontScale(Constants.FONT_SIZE);
        button2.getLabel().setFontScale(Constants.FONT_SIZE);
        button3.getLabel().setFontScale(Constants.FONT_SIZE);
        button4.getLabel().setFontScale(Constants.FONT_SIZE);
        fundsLabel.setFontScale(Constants.FONT_SIZE);
        depositNameLabel1.setFontScale(Constants.FONT_SIZE);
        depositNameLabel2.setFontScale(Constants.FONT_SIZE);
        depositNameLabel3.setFontScale(Constants.FONT_SIZE);
        depositNameLabel4.setFontScale(Constants.FONT_SIZE);
        quantityTitleLabel1.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityTitleLabel2.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityTitleLabel3.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityTitleLabel4.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityLabel1.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityLabel2.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityLabel3.setFontScale(Constants.FONT_SIZE * 0.8f);
        quantityLabel4.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesTitleLabel1.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesTitleLabel2.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesTitleLabel3.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesTitleLabel4.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesLabel1.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesLabel2.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesLabel3.setFontScale(Constants.FONT_SIZE * 0.8f);
        resourcesLabel4.setFontScale(Constants.FONT_SIZE * 0.8f);



        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.showGameScreen();
            }
        });
        button1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //expand storage
            }
        });
        button2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //expand storage
            }
        });
        button3.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //expand storage
            }
        });
        button4.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //expand storage
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button1.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button2.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button3.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button4.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        area1.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.18f);
        area2.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.18f);
        area3.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.18f);
        area4.setSize(Constants.DEVIDE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.18f);
        fundsLabel.setSize(Constants.DEVIDE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        depositNameLabel1.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel2.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel3.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel4.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel1.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel2.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel3.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel4.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel1.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel2.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel3.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel4.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel1.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel2.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel3.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel4.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel1.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel2.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel3.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel4.setSize(Constants.DEVIDE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);




        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVIDE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVIDE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        area1.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.66f);
        area2.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.45f);
        area3.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.24f);
        area4.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.03f);
        button1.setPosition(Constants.DEVIDE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.7f);
        button2.setPosition(Constants.DEVIDE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.49f);
        button3.setPosition(Constants.DEVIDE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.28f);
        button4.setPosition(Constants.DEVIDE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.07f);
        depositNameLabel1.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.71f);
        depositNameLabel2.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.51f);
        depositNameLabel3.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.29f);
        depositNameLabel4.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel1.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.75f);
        quantityTitleLabel2.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.54f);
        quantityTitleLabel3.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.33f);
        quantityTitleLabel4.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.12f);
        quantityLabel1.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.75f);
        quantityLabel2.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.54f);
        quantityLabel3.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.33f);
        quantityLabel4.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.12f);
        resourcesTitleLabel1.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.68f);
        resourcesTitleLabel2.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.47f);
        resourcesTitleLabel3.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.26f);
        resourcesTitleLabel4.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.05f);
        resourcesLabel1.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.68f);
        resourcesLabel2.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.47f);
        resourcesLabel3.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.26f);
        resourcesLabel4.setPosition(Constants.DEVIDE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.05f);



        //estados
        area1.setTouchable(Touchable.disabled);
        area2.setTouchable(Touchable.disabled);
        area3.setTouchable(Touchable.disabled);
        area4.setTouchable(Touchable.disabled);
        button1.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        button2.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        button3.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        button4.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());

        //Se añaden los elementos
        stage.addActor(goBackButton);

        stage.addActor(area1);
        stage.addActor(area2);
        stage.addActor(area3);
        stage.addActor(area4);
        stage.addActor(button1);
        stage.addActor(button3);
        stage.addActor(button2);
        stage.addActor(button4);

        stage.addActor(fundsLabel);
        stage.addActor(depositNameLabel1);
        stage.addActor(depositNameLabel2);
        stage.addActor(depositNameLabel3);
        stage.addActor(depositNameLabel4);
        stage.addActor(quantityTitleLabel1);
        stage.addActor(quantityTitleLabel2);
        stage.addActor(quantityTitleLabel3);
        stage.addActor(quantityTitleLabel4);
        stage.addActor(quantityLabel1);
        stage.addActor(quantityLabel2);
        stage.addActor(quantityLabel3);
        stage.addActor(quantityLabel4);
        stage.addActor(resourcesTitleLabel1);
        stage.addActor(resourcesTitleLabel2);
        stage.addActor(resourcesTitleLabel3);
        stage.addActor(resourcesTitleLabel4);
        stage.addActor(resourcesLabel1);
        stage.addActor(resourcesLabel2);
        stage.addActor(resourcesLabel3);
        stage.addActor(resourcesLabel4);


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
