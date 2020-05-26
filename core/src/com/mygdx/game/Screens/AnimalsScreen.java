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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;

public class AnimalsScreen extends BaseScreen{

    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, cowsButton, pigsButton, chickenButton, buyButton, sellButton;
    private Window areaB, table, tabletop;
    private Label fundsLabel, capacityLabel, advertLabel, nameHeadLabel, typeHeadLabel, dateHeadLabel;
    private Button reproduceSwitchButton;
    private Label[] [] rows;
    private CheckBox[] checkBoxes;
    private TextButton[] buttons;

    public AnimalsScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);

        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", skin, "custom");
        cowsButton = new TextButton("Vacas", skin);
        reproduceSwitchButton = new Button(skin,"switch");
        areaB = new Window("", skin,"dialog");
        tabletop = new Window("", skin);
        table = new Window("", skin, "dialog");
        pigsButton = new TextButton("Cerdos", skin);
        chickenButton = new TextButton("Gallinas", skin);
        buyButton = new TextButton("Comprar", skin);
        sellButton = new TextButton("Vender", skin, "custom");
        fundsLabel = new Label("Fondos: 1.450.654 $",skin, "required");
        capacityLabel = new Label("Ocupacion del corral  4 / 5 ", skin, "custom_grey");
        advertLabel = new Label("Permitir a las la reproduccion automatica de los animales", skin, "custom_grey");
        nameHeadLabel = new Label("Nombre", skin, "white");
        typeHeadLabel = new Label("Tipo", skin, "white");
        dateHeadLabel = new Label("Fecha nacimiento", skin, "white");

        rows = new Label[3][5];
        checkBoxes = new CheckBox[rows[0].length];
        buttons = new TextButton[rows[0].length];


        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE);
        fundsLabel.setFontScale(Constants.FONT_SIZE);
        cowsButton.getLabel().setFontScale(Constants.FONT_SIZE);
        pigsButton.getLabel().setFontScale(Constants.FONT_SIZE);
        chickenButton.getLabel().setFontScale(Constants.FONT_SIZE);
        buyButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        sellButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        capacityLabel.setFontScale(Constants.FONT_SIZE);
        advertLabel.setFontScale(Constants.FONT_SIZE);
        nameHeadLabel.setFontScale(Constants.FONT_SIZE * 0.8f);
        typeHeadLabel.setFontScale(Constants.FONT_SIZE * 0.8f);
        dateHeadLabel.setFontScale(Constants.FONT_SIZE * 0.8f);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AnimalsScreen.this.game.showGameScreen();
            }
        });
        cowsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //cows code
            }
        });
        pigsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //pigsCode
            }
        });
        chickenButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //chickencode
            }
        });
        buyButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //chickencode
            }
        });
        sellButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //chickencode
            }
        });
        reproduceSwitchButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //swich reproduction code
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        buyButton.setSize(Constants.DEVIDE_WIDTH  * 0.12f, Constants.DEVICE_HEIGHT *0.06f);
        sellButton.setSize(Constants.DEVIDE_WIDTH  * 0.12f, Constants.DEVICE_HEIGHT *0.06f);
        fundsLabel.setSize(Constants.DEVIDE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        capacityLabel.setSize(Constants.DEVIDE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        advertLabel.setSize(Constants.DEVIDE_WIDTH *0.6f, Constants.DEVICE_HEIGHT * 0.10f);
        cowsButton.setSize(Constants.DEVIDE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        chickenButton.setSize(Constants.DEVIDE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        pigsButton.setSize(Constants.DEVIDE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        areaB.setSize(Constants.DEVIDE_WIDTH *0.88f, Constants.DEVICE_HEIGHT * 0.7f);
        table.setSize(Constants.DEVIDE_WIDTH *0.69f, Constants.DEVICE_HEIGHT * 0.44f);
        tabletop.setSize(Constants.DEVIDE_WIDTH *0.69f, Constants.DEVICE_HEIGHT * 0.08f);
        reproduceSwitchButton.setSize(Constants.DEVIDE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        nameHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        typeHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        dateHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);


        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVIDE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVIDE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        cowsButton.setPosition(Constants.DEVIDE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.72f);
        pigsButton.setPosition(Constants.DEVIDE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.72f);
        chickenButton.setPosition(Constants.DEVIDE_WIDTH * 0.65f, Constants.DEVICE_HEIGHT * 0.72f);
        areaB.setPosition(Constants.DEVIDE_WIDTH * 0.06f, Constants.DEVICE_HEIGHT * 0.03f);
        tabletop.setPosition(Constants.DEVIDE_WIDTH * 0.08f, Constants.DEVICE_HEIGHT * 0.45f);
        table.setPosition(Constants.DEVIDE_WIDTH * 0.08f, Constants.DEVICE_HEIGHT * 0.03f);
        capacityLabel.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.61f);
        advertLabel.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.53f);
        buyButton.setPosition(Constants.DEVIDE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.63f);
        sellButton.setPosition(Constants.DEVIDE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.63f);
        reproduceSwitchButton.setPosition(Constants.DEVIDE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.555f);
        nameHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.16f, Constants.DEVICE_HEIGHT * 0.45f);
        typeHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.36f, Constants.DEVICE_HEIGHT * 0.45f);
        dateHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.52f, Constants.DEVICE_HEIGHT * 0.45f);


        //estados
        areaB.setTouchable(Touchable.disabled);
        tabletop.setTouchable(Touchable.disabled);
        table.setTouchable(Touchable.disabled);
        buyButton.setStyle(StyleFactory.BLUE_TEXT_BUTTON_STYLE());
        sellButton.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        cowsButton.setStyle(StyleFactory.getStyle(StyleFactory.DARK_GREY_BLUE_COLOR, StyleFactory.GREY_BLUE_COLOR));
        pigsButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
        chickenButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
        tabletop.setStyle(StyleFactory.getStyle(StyleFactory.BLUE_COLOR));


        //Se añaden los elementos
        stage.addActor(goBackButton);
        stage.addActor(fundsLabel);
        stage.addActor(areaB);
        stage.addActor(table);
        stage.addActor(tabletop);
        stage.addActor(chickenButton);
        stage.addActor(pigsButton);
        stage.addActor(cowsButton);
        stage.addActor(capacityLabel);
        stage.addActor(buyButton);
        stage.addActor(sellButton);
        stage.addActor(advertLabel);
        stage.addActor(reproduceSwitchButton);
        stage.addActor(nameHeadLabel);
        stage.addActor(typeHeadLabel);
        stage.addActor(dateHeadLabel);


        //datos de la tabla
        for(int i = 0; i< rows.length;i++){
            for(int j = 0; j< rows[i].length;j++){
                rows[i][j] = new Label("xxx",skin,"required");
                rows[i][j].setFontScale(Constants.FONT_SIZE * 0.8f );
                rows[i][j].setAlignment(Align.center);
                rows[i][j].setSize(Constants.DEVIDE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT * 0.08f);
                rows[i][j].setPosition(Constants.DEVIDE_WIDTH *( 0.12f + i * 0.2f ), Constants.DEVICE_HEIGHT * (0.04f + j * 0.08f));
                stage.addActor(rows[i][j]);
            }

        }
        //checkboxses & buttons
        for(int i = 0; i< checkBoxes.length;i++){
            checkBoxes[i] = new CheckBox("", skin);
            buttons[i] = new TextButton("Sacrificar",skin);
            buttons[i].getLabel().setFontScale(Constants.FONT_SIZE * 0.7f);
            checkBoxes[i].getCells().get(0).size(Constants.DEVIDE_WIDTH * 0.025f, Constants.DEVIDE_WIDTH * 0.025f);
            checkBoxes[i].getImage().setScaling(Scaling.fit);
            buttons[i].setSize(Constants.DEVIDE_WIDTH * 0.10f, Constants.DEVICE_HEIGHT * 0.06f);
            checkBoxes[i].setPosition(Constants.DEVIDE_WIDTH *( 0.12f ), Constants.DEVICE_HEIGHT * (0.07f + i * 0.08f));
            buttons[i].setPosition(Constants.DEVIDE_WIDTH *( 0.78f ), Constants.DEVICE_HEIGHT * (0.045f + i * 0.08f));
            buttons[i].setStyle(StyleFactory.getStyle(StyleFactory.RED_COLOR, StyleFactory.DARK_RED_COLOR));
            stage.addActor(checkBoxes[i]);
            stage.addActor(buttons[i]);
        }
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
