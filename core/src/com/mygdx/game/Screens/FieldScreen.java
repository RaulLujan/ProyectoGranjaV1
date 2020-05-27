package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.DialogFactory;
import com.mygdx.game.MainGame;

public class FieldScreen extends BaseScreen {

    private Stage stage;
    private World world;

    private Skin skin;

    private TextButton goBackButton, plantButton;
    private Button waterButton, manureButton, herbicideButton ;
    private Window  areaL, areaR, areaB;
    private Label fundsLabel, timeLabel, timeTitleLabel, estateLabel, estateTileLabel;
    private Label infoTypeTipleLabel, infotypeLabel, levelTitleLabel, levelLabel, waterTitleLabel, waterLabel,
            manureTitleLabel, manureLabel, grassTitleLabel, grassLabel, addLabel, waterQuantitylabel, manureQuantityLabel,
            herbizideQuantityLabel;
    private SelectBox<String> typeSelectioSP;


    public FieldScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);


        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();





        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));



        // inicialización de los elementos
        goBackButton = new TextButton("Volver", skin, "custom");
        plantButton= new TextButton("Plantar", skin, "custom");

        waterButton = new Button(skin,"new_custom");
        manureButton = new Button(skin,"new_custom");
        herbicideButton = new Button(skin,"new_custom");

        areaL = new Window("", skin, "dialog");
        areaB = new Window("", skin,"dialog");
        areaR = new Window("", skin,"dialog");

        fundsLabel = new Label(String.format("Fondos: %s", recursos),skin, "required");
        estateTileLabel = new Label("Estado: ",skin, "required");
        estateLabel = new Label("creciendo",skin, "custom_gold");
        timeTitleLabel = new Label("Tiempo restante: ",skin, "required");
        timeLabel = new Label("1d 12h 35s",skin, "custom_gold");
        infotypeLabel = new Label("Maiz",skin, "custom_blue");
        infoTypeTipleLabel = new Label("Tipo de Cultivo:",skin, "required");
        levelTitleLabel = new Label("Nivel de crecimiento",skin, "required");
        levelLabel = new Label("15%",skin, "custom_blue");
        waterTitleLabel = new Label("Necesita riego:",skin, "required");
        waterLabel = new Label("Si",skin, "custom_blue");
        manureTitleLabel = new Label("Necesita abono:",skin, "required");
        manureLabel = new Label("No",skin, "custom_blue");
        grassTitleLabel = new Label("Malas hierbas",skin, "required");
        grassLabel = new Label("SI",skin, "custom_blue");
        addLabel = new Label("Usar en el campo:",skin, "required");
        waterQuantitylabel = new Label("Agua: 1034",skin, "custom_green");
        manureQuantityLabel = new Label("Abono: 367",skin, "custom_green");
        herbizideQuantityLabel = new Label("Herbicida: 112",skin, "custom_green");

        typeSelectioSP = new SelectBox<String>(skin);


        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE);
        plantButton.getLabel().setFontScale(Constants.FONT_SIZE);
        typeSelectioSP.getStyle().listStyle.font.getData().scale(Constants.FONT_SIZE * 0.7f);
        Label[] labels = { fundsLabel, estateTileLabel, estateLabel, timeTitleLabel, timeLabel, infoTypeTipleLabel, infotypeLabel ,
                            levelTitleLabel, levelLabel, waterTitleLabel, waterLabel, manureTitleLabel, manureLabel, grassTitleLabel,
                            grassLabel, addLabel, waterQuantitylabel, manureQuantityLabel, herbizideQuantityLabel};
        for (Label label: labels){
            label.setFontScale(Constants.FONT_SIZE);
        }
        typeSelectioSP.setScale(2);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.showGameScreen();
            }
        });

        plantButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //Plant field code
                String mensaje ="Plantando como una fiera\n a lo bestia";
                DialogFactory.showOkDialog(FieldScreen.this, stage, "TITULO", mensaje, 0.5f, 0.4f);
            }
        });

        waterButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //Plant field code
                String mensaje ="Regando como una fiera\n a lo bestia";
                DialogFactory.showOkCancelDialog(FieldScreen.this, stage, "TITULO", mensaje, 0.4f, 0.4f, null, null);
            }
        });

        //Tamaños de los elementos
        for (Label label: labels){
            label.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        }
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        plantButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.08f);
        waterButton.setSize(Constants.DEVICE_WIDTH * 0.04f, Constants.DEVICE_WIDTH *0.04f);
        manureButton.setSize(Constants.DEVICE_WIDTH * 0.04f, Constants.DEVICE_WIDTH *0.04f);
        herbicideButton.setSize(Constants.DEVICE_WIDTH * 0.04f, Constants.DEVICE_WIDTH *0.04f);


        fundsLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);

        areaL.setSize(Constants.DEVICE_WIDTH *0.45f, Constants.DEVICE_HEIGHT * 0.3f);
        areaR.setSize(Constants.DEVICE_WIDTH *0.45f, Constants.DEVICE_HEIGHT * 0.3f);
        areaB.setSize(Constants.DEVICE_WIDTH *0.8f, Constants.DEVICE_HEIGHT * 0.5f);
        typeSelectioSP.setSize(Constants.DEVICE_WIDTH * 0.25f, Constants.DEVICE_HEIGHT * 0.08f);



        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVICE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        estateTileLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.71f);
        estateLabel.setPosition(Constants.DEVICE_WIDTH * 0.3f, Constants.DEVICE_HEIGHT * 0.71f);
        timeTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.61f);
        timeLabel.setPosition(Constants.DEVICE_WIDTH * 0.3f, Constants.DEVICE_HEIGHT * 0.61f);
        areaL.setPosition(Constants.DEVICE_WIDTH * 0.049f, Constants.DEVICE_HEIGHT * 0.55f);
        areaR.setPosition(Constants.DEVICE_WIDTH * 0.501f, Constants.DEVICE_HEIGHT * 0.55f);
        areaB.setPosition(Constants.DEVICE_WIDTH * 0.10f, Constants.DEVICE_HEIGHT * 0.03f);
        typeSelectioSP.setPosition(Constants.DEVICE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.71f);
        plantButton.setPosition(Constants.DEVICE_WIDTH * 0.7f, Constants.DEVICE_HEIGHT * 0.61f);

        infoTypeTipleLabel.setPosition(Constants.DEVICE_WIDTH * 0.18f, Constants.DEVICE_HEIGHT * 0.44f);
        infotypeLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.44f);
        levelTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.18f, Constants.DEVICE_HEIGHT * 0.34f);
        levelLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.34f);
        waterTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.18f, Constants.DEVICE_HEIGHT * 0.24f);
        waterLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.24f);
        manureTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.18f, Constants.DEVICE_HEIGHT * 0.14f);
        manureLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.14f);
        grassTitleLabel.setPosition(Constants.DEVICE_WIDTH * 0.18f, Constants.DEVICE_HEIGHT * 0.04f);
        grassLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.04f);

        addLabel.setPosition(Constants.DEVICE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.42f);
        waterQuantitylabel.setPosition(Constants.DEVICE_WIDTH * 0.57f, Constants.DEVICE_HEIGHT * 0.28f);
        manureQuantityLabel.setPosition(Constants.DEVICE_WIDTH * 0.57f, Constants.DEVICE_HEIGHT * 0.19f);
        herbizideQuantityLabel.setPosition(Constants.DEVICE_WIDTH * 0.57f, Constants.DEVICE_HEIGHT * 0.1f);

        waterButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.29f);
        manureButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.2f);
        herbicideButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.11f);

        //estados
        areaR.setTouchable(Touchable.disabled);
        areaL.setTouchable(Touchable.disabled);
        areaB.setTouchable(Touchable.disabled);

        estateTileLabel.setAlignment(Align.right);
        timeTitleLabel.setAlignment(Align.right);
        typeSelectioSP.setItems("Patatas", "Maiz", "Trigo");
        typeSelectioSP.setAlignment(Align.center);
        infoTypeTipleLabel.setAlignment(Align.right);
        levelTitleLabel.setAlignment(Align.right);
        waterTitleLabel.setAlignment(Align.right);
        manureTitleLabel.setAlignment(Align.right);
        grassTitleLabel.setAlignment(Align.right);
        addLabel.setAlignment(Align.center);
        waterQuantitylabel.setAlignment(Align.right);
        manureQuantityLabel.setAlignment(Align.right);
        herbizideQuantityLabel.setAlignment(Align.right);


        //Se añaden los elementos
        stage.addActor(goBackButton);

        stage.addActor(areaR);
        stage.addActor(areaB);
        stage.addActor(areaL);

        stage.addActor(typeSelectioSP);
        stage.addActor(plantButton);

        for (Label label: labels){
            stage.addActor(label);
        }
        stage.addActor(waterButton);
        stage.addActor(manureButton);
        stage.addActor(herbicideButton);

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




    public void disableAll(boolean enableDisable){
        goBackButton.setDisabled(enableDisable);
        typeSelectioSP.setDisabled(enableDisable);
        plantButton.setDisabled(enableDisable);
        waterButton.setDisabled(enableDisable);
        manureButton.setDisabled(enableDisable);
        herbicideButton.setDisabled(enableDisable);
    }
    public void actions(int actionIndex){

    }
}
