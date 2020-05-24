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

    private TextButton goBackButton;
    private Window areaT, areaB;
    private Label fundsLabel, resourceNameHeadLabel, maxCapacityHeadLabel, actualOcupationHeadLabel;
    private Label[] [] rows;
    private TextButton[] rowButtons;



    public StorageScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVIDE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);

        rows = new Label[3][9];
        rowButtons = new TextButton[rows[0].length];

        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", skin, "custom");
        areaT = new Window("", skin);
        areaB = new Window("", skin,"dialog");
        fundsLabel = new Label("Fondos: 1.450.654 $",skin, "required");
        resourceNameHeadLabel = new Label("Recurso",skin, "white");
        maxCapacityHeadLabel = new Label("Capacidad maxima",skin, "white");
        actualOcupationHeadLabel = new Label("Cantidad actual",skin, "white");



        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE);
        fundsLabel.setFontScale(Constants.FONT_SIZE);
        resourceNameHeadLabel.setFontScale(Constants.FONT_SIZE);
        maxCapacityHeadLabel.setFontScale(Constants.FONT_SIZE * 0.8f);
        actualOcupationHeadLabel.setFontScale(Constants.FONT_SIZE * 0.8f);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.showGameScreen();
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVIDE_WIDTH  * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        areaT.setSize(Constants.DEVIDE_WIDTH *0.65f, Constants.DEVICE_HEIGHT * 0.08f);
        areaB.setSize(Constants.DEVIDE_WIDTH *0.65f, Constants.DEVICE_HEIGHT * 0.72f);
        fundsLabel.setSize(Constants.DEVIDE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);

        resourceNameHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        maxCapacityHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        actualOcupationHeadLabel.setSize(Constants.DEVIDE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);

        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVIDE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVIDE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        areaT.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.75f);
        areaB.setPosition(Constants.DEVIDE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.03f);

        resourceNameHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.17f, Constants.DEVICE_HEIGHT * 0.75f);
        maxCapacityHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.33f, Constants.DEVICE_HEIGHT * 0.75f);
        actualOcupationHeadLabel.setPosition(Constants.DEVIDE_WIDTH * 0.54f, Constants.DEVICE_HEIGHT * 0.75f);

        //estados
        areaT.setStyle(StyleFactory.getStyle(StyleFactory.BLUE_COLOR));
        areaT.setTouchable(Touchable.disabled);
        areaB.setTouchable(Touchable.disabled);

        //Se añaden los elementos
        stage.addActor(goBackButton);

        stage.addActor(areaB);
        stage.addActor(areaT);
        stage.addActor(fundsLabel);
        stage.addActor(resourceNameHeadLabel);
        stage.addActor(maxCapacityHeadLabel);
        stage.addActor(actualOcupationHeadLabel);

        //datos de la tabla
        for(int i = 0; i< rows.length;i++){
            for(int j = 0; j< rows[i].length;j++){
                rows[i][j] = new Label("xxx",skin,"required");
                rows[i][j].setFontScale(Constants.FONT_SIZE);
                rows[i][j].setAlignment(Align.center);
                rows[i][j].setSize(Constants.DEVIDE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT * 0.08f);
                rows[i][j].setPosition(Constants.DEVIDE_WIDTH *( 0.14f + i * 0.2f ), Constants.DEVICE_HEIGHT * (0.03f + j * 0.08f));
                stage.addActor(rows[i][j]);

            }
        }

        //botones de compra-venta
        for(int i = 0; i< rowButtons.length;i++) {
                rowButtons[i] = new TextButton("Expandir", skin);
                rowButtons[i].getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
                rowButtons[i].setSize(Constants.DEVIDE_WIDTH * 0.12f, Constants.DEVICE_HEIGHT * 0.07f);
                rowButtons[i].setPosition(Constants.DEVIDE_WIDTH *0.775f , Constants.DEVICE_HEIGHT * (0.035f + i * 0.08f));
                stage.addActor(rowButtons[i]);

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
