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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.factories.DialogFactory;
import com.mygdx.game.DomainMocker;
import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.Infraestructura;
import com.mygdx.game.dominio.TipoRecurso;
import com.mygdx.game.images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.control.EspacioController;
import com.mygdx.game.control.FieldController;


public class FieldScreen extends BaseScreen {

    private Stage stage;
    private World world;

    private Skin skin, glassySkim;

    private TextButton goBackButton, plantButton;
    private Button waterButton, manureButton, herbicideButton ;
    private Window  areaL, areaR, areaB;
    private Label fundsLabel, timeLabel, timeTitleLabel, estateLabel, estateTileLabel;
    private Label infoTypeTipleLabel, infotypeLabel, levelTitleLabel, levelLabel, waterTitleLabel, waterLabel,
            manureTitleLabel, manureLabel, grassTitleLabel, grassLabel, addLabel, waterQuantitylabel, manureQuantityLabel,
            herbizideQuantityLabel;
    private SelectBox<String> typeSelectioSP;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;
    private Campo field;
    private FieldController fieldController;
    private EspacioController espacioController;
    private long totalRestTime, timePassedSinceLastActualization, totalGrowDurationInMillis;
    private float acumulatedDElta;
    private boolean firstTimeToGet;



    public FieldScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), false);
        this.field = (Campo)this.game.getUsuario().getGranja().getInfraestructuras().get(Infraestructura.FIELD);
        this.fieldController = this.game.getFieldController();
        espacioController = new EspacioController(this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios());
        firstTimeToGet = true;

        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();





        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));
        this.glassySkim = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/fieldBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);



        // inicialización de los elementos
        goBackButton = new TextButton("Volver", glassySkim, "big");
        plantButton= new TextButton("Plantar", glassySkim, "big");

        waterButton = new Button(skin,"new_custom");
        manureButton = new Button(skin,"new_custom");
        herbicideButton = new Button(skin,"new_custom");

        areaL = new Window("", skin, "dialog");
        areaB = new Window("", skin,"dialog");
        areaR = new Window("", skin,"dialog");

        fundsLabel = new Label(String.format("Fondos: %s", recursos),     glassySkim, "big");
        estateTileLabel = new Label("Estado: ",                         glassySkim, "big");
        estateLabel = new Label("",                                     glassySkim, "big-gold");
        timeTitleLabel = new Label("Tiempo restante: ",                 glassySkim, "big");
        timeLabel = new Label("",                                       glassySkim, "big-gold");
        infotypeLabel = new Label("",                                   glassySkim, "big-blue");
        infoTypeTipleLabel = new Label("Tipo de Cultivo:",              glassySkim, "big");
        levelTitleLabel = new Label("Nivel de crecimiento",             glassySkim, "big");
        levelLabel = new Label("",                                      glassySkim, "big-blue");
        waterTitleLabel = new Label("Necesita riego:",                  glassySkim, "big");
        waterLabel = new Label("",                                      glassySkim, "big-blue");
        manureTitleLabel = new Label("Necesita abono:",                 glassySkim, "big");
        manureLabel = new Label("",                                     glassySkim, "big-blue");
        grassTitleLabel = new Label("Malas hierbas",                    glassySkim, "big");
        grassLabel = new Label("",                                      glassySkim, "big-blue");
        addLabel = new Label("Usar en el campo:",                       glassySkim, "big");
        waterQuantitylabel = new Label("",                              glassySkim, "big");
        manureQuantityLabel = new Label("",                             glassySkim, "big");
        herbizideQuantityLabel = new Label("",                          glassySkim, "big");

        typeSelectioSP = new SelectBox<>(glassySkim);


        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        plantButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.25f);

        Label[] labels = { fundsLabel, estateTileLabel, estateLabel, timeTitleLabel, timeLabel, infoTypeTipleLabel, infotypeLabel ,
                            levelTitleLabel, levelLabel, waterTitleLabel, waterLabel, manureTitleLabel, manureLabel, grassTitleLabel,
                            grassLabel, addLabel, waterQuantitylabel, manureQuantityLabel, herbizideQuantityLabel};
        for (Label label: labels){
            label.setFontScale(Constants.FONT_SIZE * 0.25f);
        }
        //typeSelectioSP.setScale(2f);
        typeSelectioSP.getStyle().listStyle.font = glassySkim.getFont("font-big");
        typeSelectioSP.getStyle().listStyle.font.getData().scale(Constants.FONT_SIZE * 0.2f);
        typeSelectioSP.getStyle().font.getData().setScale(0.6f);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.getSoundFactory().playPickUp();
                FieldScreen.this.game.showGameScreen();
            }
        });

        plantButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //Plant field code
                FieldScreen.this.game.getSoundFactory().playSelect2();
                String cropSelected = typeSelectioSP.getList().getSelected();
                String mensaje = String.format("Plantar el campo con %s?\nEsta accion consumira\n500 %s", cropSelected, cropSelected);
                DialogFactory.showOkCancelDialog(
                        FieldScreen.this, stage,
                        String.format("Plantar %s", cropSelected),
                        mensaje,
                        0.45f,
                        0.4f,
                        1,
                        null);
            }
        });

        waterButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.getSoundFactory().playSelect2();
                DialogFactory.showOkCancelDialog(
                        FieldScreen.this, stage,
                        "Regar",
                        String.format("Esta accion consumira %s\nlitros de agua\n¿Estas seguro?",
                                fieldController.getWaterQuantity(field.getPlantedResourceType()) ),
                        0.4f,
                        0.4f,
                        2, null);
            }
        });
        manureButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.getSoundFactory().playSelect2();
                DialogFactory.showOkCancelDialog(
                        FieldScreen.this, stage,
                        "Abonar",
                        String.format("Esta accion consumira %s\nlitros de abono\n¿Estas seguro?",
                                fieldController.getManureQuantity(field.getPlantedResourceType()) ),
                        0.4f,
                        0.4f,
                        3, null);
            }
        });
        herbicideButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.getSoundFactory().playSelect2();
                DialogFactory.showOkCancelDialog(
                        FieldScreen.this, stage,
                        "Quitar malas hierbas",
                        String.format("Esta accion consumira %s\nlitros de herbicida\n¿Estas seguro?",
                                fieldController.getHerbicideQuantity(field.getPlantedResourceType())),
                        0.4f,
                        0.4f,
                        4, null);
            }
        });
        typeSelectioSP.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FieldScreen.this.game.getSoundFactory().playSelect3();

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

        waterButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.28f);
        manureButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.19f);
        herbicideButton.setPosition(Constants.DEVICE_WIDTH * 0.79f, Constants.DEVICE_HEIGHT * 0.1f);

        //estados
        areaR.setTouchable(Touchable.disabled);
        areaL.setTouchable(Touchable.disabled);
        areaB.setTouchable(Touchable.disabled);

        goBackButton.setColor(Color.GREEN);
        estateTileLabel.setAlignment(Align.right);
        timeTitleLabel.setAlignment(Align.right);
        typeSelectioSP.setItems("Patata", "Maiz", "Fresa");

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
        areaR.setColor(1,1,1,0.85f);
        areaL.setColor(1,1,1,0.85f);
        areaB.setColor(1,1,1,0.85f);
        setTextsAndStates();



        //Se añaden los elementos
        stage.addActor(backgroundImage);
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
        acumulatedDElta += delta;
        //movimiento del mundo
        if (field.isPlanted()) {

            timeLabel.setText(millisToString(totalRestTime - (timePassedSinceLastActualization + (long)(acumulatedDElta * 1000))));
            levelLabel.setText(String.format("%s%s", this.getCompletedPercent(), "%"));
            if (field.getStage() == 1 && getCompletedPercent() > 33){
                fieldController.controlField();
                setTextsAndStates();
            }else if (field.getStage() == 2 && getCompletedPercent() > 66){
                fieldController.controlField();
                setTextsAndStates();
            }else if (field.getStage() == 3 && getCompletedPercent() == 100){
                fieldController.controlField();
                setTextsAndStates();
            }else if (field.getStage() == 4){
                if (firstTimeToGet){
                    firstTimeToGet = false;
                    actions(5);
                }
            }
        }
        stage.draw();
        stage.act();
        world.step(delta, 6, 2);





    }

    @Override
    public void dispose() {
        stage.getBatch().dispose();
        stage.dispose();
        world.dispose();

    }

    public int getCompletedPercent(){
        int percent = (int)( ((totalGrowDurationInMillis - (totalRestTime - (long)(acumulatedDElta * 1000))) * 100) / totalGrowDurationInMillis);
        if (percent <= 100) return percent;
        else return 100;

    }




    private String millisToString(long millis){
        int days, hours, minutes, seconds;
        if (millis > 0){
            long restingMillis = millis;

            days = (int) (restingMillis/ (24 * 60 * 60 * 1000));
            restingMillis = restingMillis -  ( days * 24 * 60 * 60 * 1000);
            hours = (int) (restingMillis / (60 * 60 * 1000));
            restingMillis = restingMillis -  ( hours * 60 * 60 * 1000);
            minutes = (int) (restingMillis / (60 * 1000));
            restingMillis = restingMillis -  ( minutes * 60 * 1000);
            seconds = (int) (restingMillis / 1000);
        }else{
            days = 0;
            hours = 0;
            minutes = 0;
            seconds = 0;
        }

        return String.format("%sd %sh %sm %ss", days, hours, minutes, seconds);
    }



    public void disableAll(boolean enableDisable){
        goBackButton.setDisabled(enableDisable);
        typeSelectioSP.setDisabled(enableDisable);
        plantButton.setDisabled(enableDisable);
        waterButton.setDisabled(enableDisable);
        manureButton.setDisabled(enableDisable);
        herbicideButton.setDisabled(enableDisable);
        if (!enableDisable)setTextsAndStates();
    }
    public void setTextsAndStates(){
        if (field.isPlanted()){
            totalGrowDurationInMillis = fieldController.getTotalGrowDurationInMillis();
            estateLabel.setText("Creciendo");
            typeSelectioSP.setDisabled(true);
            plantButton.setDisabled(true);
            totalRestTime = fieldController.getRestingTime();
            timePassedSinceLastActualization = 0;
            levelLabel.setText(fieldController.getCompletedPercent());



            infotypeLabel.setText(DomainMocker.getAllResorurcesList().get(field.getPlantedResourceType()).getNombre());
            int quantity;
            if (field.isNeedsWater()) {
                waterLabel.setText("Si");
                waterButton.setDisabled(false);
                quantity = fieldController.getWaterQuantity(field.getPlantedResourceType());
                waterQuantitylabel.setText(String.format("Agua: %s",quantity));
            }else{
                waterButton.setDisabled(true);
                waterLabel.setText("No");
                waterQuantitylabel.setText("Agua: -");
            }
            if (field.isNeedsManure()) {
                manureLabel.setText("Si");
                manureButton.setDisabled(false);
                quantity = fieldController.getManureQuantity(field.getPlantedResourceType());
                manureQuantityLabel.setText(String.format("Abono: %s",quantity));
            }else{
                manureButton.setDisabled(true);
                manureLabel.setText("No");
                manureQuantityLabel.setText("Abono: -");
            }
            if (field.isNeedsHerbizide()) {
                grassLabel.setText("Si");
                herbicideButton.setDisabled(false);
                quantity = fieldController.getHerbicideQuantity(field.getPlantedResourceType());
                herbizideQuantityLabel.setText(String.format("Herbicida: %s",quantity));
            }else{
                herbicideButton.setDisabled(true);
                grassLabel.setText("No");
                herbizideQuantityLabel.setText("Herbicida: -");
            }
            if (field.getStage() == 4){
                waterButton.setDisabled(true);
                manureButton.setDisabled(true);
                herbicideButton.setDisabled(true);
            }

        }else {
            infotypeLabel.setText("Ninguno");
            estateLabel.setText( "Sin uso");
            levelLabel.setText("0%");
            timeLabel.setText("-");
            waterLabel.setText("-");
            manureLabel.setText("-");
            grassLabel.setText("-");
            waterQuantitylabel.setText("Agua: ");
            manureQuantityLabel.setText("Abono: ");
            herbizideQuantityLabel.setText("Herbicida: ");
            typeSelectioSP.setDisabled(false);
            plantButton.setDisabled(false);
            waterButton.setDisabled(true);
            manureButton.setDisabled(true);
            herbicideButton.setDisabled(true);

        }
        acumulatedDElta = 0;
    }

    public void actions(int actionIndex){

        switch (actionIndex) {
            case 1:
                //plant action
                this.firstTimeToGet = true;
                int selectedCrop;
                if (typeSelectioSP.getList().getSelected().equals("Patata")) selectedCrop = TipoRecurso.POTATO;
                else if (typeSelectioSP.getList().getSelected().equals("Fresa")) selectedCrop = TipoRecurso.STRAWBERRY;
                else selectedCrop = TipoRecurso.CORN;

                if(espacioController.put(selectedCrop, -500)){
                    //panting stuff
                    fieldController.plant(selectedCrop);
                    timePassedSinceLastActualization = 0;
                    totalGrowDurationInMillis = fieldController.getTotalGrowDurationInMillis();

                }else{
                    String crop = typeSelectioSP.getList().getSelected();
                    DialogFactory.showOkDialog(this, stage,
                             String.format("%s Insuficiente",crop ),
                             String.format("Parece que no tienes suficiente\n%s, puedes comparlos en la tienda\n o elegir otro tipo de cultivo.", crop.toLowerCase() ),
                            0.5f,
                            0.4f);
                }
                break;
            case 2:
                //add water
                if(espacioController.put(TipoRecurso.WATER, -fieldController.getWaterQuantity(field.getPlantedResourceType()))){
                    fieldController.waterField();
                }else{
                    DialogFactory.showOkDialog(this, stage,
                            "Escasez de agua",
                            String.format("Ups, necesitas %s litros\nde agua y no tienes sufuciente.\nRellena el deposito antes",
                                    fieldController.getWaterQuantity(field.getPlantedResourceType())) ,
                            0.5f,
                            0.4f);
                }
                break;
            case 3:
                //add Manure
                if(espacioController.put(TipoRecurso.MANURE, -fieldController.getManureQuantity(field.getPlantedResourceType()))){
                    fieldController.manureToField();
                }else{
                    DialogFactory.showOkDialog(this, stage,
                            "Escasez de abono",
                            String.format("Ups, necesitas %s litros\nde abono y no tienes sufuciente.\nRellena el almacen antes",
                                    fieldController.getManureQuantity(field.getPlantedResourceType())) ,
                            0.5f,
                            0.4f);
                }
                break;
            case 4:
                // add Herbicide
                if(espacioController.put(TipoRecurso.HERBIZIDE, -fieldController.getHerbicideQuantity(field.getPlantedResourceType()))){
                    fieldController.herbicideToField();
                }else{
                    DialogFactory.showOkDialog(this, stage,
                            "Escasez de herbicida",
                            String.format("Ups, necesitas %s litros\nde herbicida y no tienes sufuciente.\nRellena el almacen antes",
                                    fieldController.getHerbicideQuantity(field.getPlantedResourceType())) ,
                            0.5f,
                            0.4f);
                }
                break;
            case 5:
                // recoger campo
                this.game.getSoundFactory().playComplete();
                if(espacioController.put(field.getPlantedResourceType(), fieldController.getProduction())){
                    DialogFactory.showOkDialog(this, stage,
                            "Cosecha lista",
                            String.format("La cosecha está lista para\nla recogida, se han producido\n%s litros de %s",
                                    fieldController.getProduction(), DomainMocker.getAllResorurcesList().get(field.getPlantedResourceType()).getNombre() ) ,
                            0.5f,
                            0.4f);
                    //Reset field:
                    fieldController.setStage0();
                }else{
                    DialogFactory.showOkDialog(this, stage,
                            "Cosecha lista",
                            String.format("La cosecha está lista para\nla recogida, se han producido\n%s litros de %s pero el\n granero esta lleno.\n¡Haz sitio antes!",
                                    fieldController.getProduction(),
                                    DomainMocker.getAllResorurcesList().get(field.getPlantedResourceType()).getNombre() ) ,
                            0.5f,
                            0.4f);
                }

                break;

            default:

        }
        this.setTextsAndStates();
        this.game.getUserController().saveUser();
    }
}
