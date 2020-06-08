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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.factories.DialogFactory;
import com.mygdx.game.dominio.TipoRecurso;
import com.mygdx.game.images.ImageClampToEdge;
import com.mygdx.game.MainGame;

public class StorageScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin, glassSkin;

    private TextButton goBackButton, button1, button2, button3, button4;
    private Window area1, area2, area3, area4;
    private Label fundsLabel;
    private Label depositNameLabel1, quantityTitleLabel1, quantityLabel1, resourcesTitleLabel1, resourcesLabel1;
    private Label depositNameLabel2, quantityTitleLabel2, quantityLabel2, resourcesTitleLabel2, resourcesLabel2;
    private Label depositNameLabel3, quantityTitleLabel3, quantityLabel3, resourcesTitleLabel3, resourcesLabel3;
    private Label depositNameLabel4, quantityTitleLabel4, quantityLabel4, resourcesTitleLabel4, resourcesLabel4;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;




    public StorageScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);

        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();



        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));
        this.glassSkin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/storageBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", glassSkin, "big");
        button1 = new TextButton("Expandir", glassSkin, "big");
        button2 = new TextButton("Expandir", glassSkin, "big");
        button3 = new TextButton("Expandir", glassSkin, "big");
        button4 = new TextButton("Expandir", glassSkin, "big");
        area1 = new Window("", skin,"dialog");
        area2 = new Window("", skin,"dialog");
        area3 = new Window("", skin,"dialog");
        area4 = new Window("", skin,"dialog");
        fundsLabel = new Label(String.format("Fondos: %s", recursos),glassSkin, "big");
        depositNameLabel1 = new Label("Deposito de Agua",glassSkin, "big");
        depositNameLabel2 = new Label("Camara frigorifica",glassSkin, "big");
        depositNameLabel3 = new Label("Almacen general",glassSkin, "big");
        depositNameLabel4 = new Label("Granero",glassSkin, "big");
        quantityTitleLabel1 = new Label("Capacidad",glassSkin, "big");
        quantityTitleLabel2 = new Label("Capacidad",glassSkin, "big");
        quantityTitleLabel3 = new Label("Capacidad",glassSkin, "big");
        quantityTitleLabel4 = new Label("Capacidad",glassSkin, "big");
        int capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.WATER).getCapacidadMaxima();
        String capacityString = String.format("%s", capacity);
        quantityLabel1 = new Label( capacityString, glassSkin, "big-blue");
        capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MEAT).getCapacidadMaxima();
        capacityString = String.format("%s", capacity);
        quantityLabel2 = new Label(capacityString,glassSkin, "big-blue");
        capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MANURE).getCapacidadMaxima();
        capacityString = String.format("%s", capacity);
        quantityLabel3 = new Label(capacityString,glassSkin, "big-blue");
        capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.CORN).getCapacidadMaxima();
        capacityString = String.format("%s", capacity);
        quantityLabel4 = new Label(capacityString,glassSkin, "big-blue");
        resourcesTitleLabel1 = new Label("Recursos",glassSkin, "big");
        resourcesTitleLabel2 = new Label("Recursos",glassSkin, "big");
        resourcesTitleLabel3 = new Label("Recursos",glassSkin, "big");
        resourcesTitleLabel4 = new Label("Recursos",glassSkin, "big");
        resourcesLabel1 = new Label("Agua",glassSkin, "big-blue");
        resourcesLabel2 = new Label("Carne, Huevos, Leche",glassSkin, "big-blue");
        resourcesLabel3 = new Label("Abono, Herbicida",glassSkin, "big-blue");
        resourcesLabel4 = new Label("Maiz, Fresas, Patatas",glassSkin, "big-blue");





        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.35f);
        button1.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        button2.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        button3.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        button4.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        fundsLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        depositNameLabel1.setFontScale(Constants.FONT_SIZE * 0.27f);
        depositNameLabel2.setFontScale(Constants.FONT_SIZE* 0.27f);
        depositNameLabel3.setFontScale(Constants.FONT_SIZE* 0.27f);
        depositNameLabel4.setFontScale(Constants.FONT_SIZE* 0.27f);
        quantityTitleLabel1.setFontScale(Constants.FONT_SIZE * 0.2f);
        quantityTitleLabel2.setFontScale(Constants.FONT_SIZE * 0.2f);
        quantityTitleLabel3.setFontScale(Constants.FONT_SIZE * 0.2f);
        quantityTitleLabel4.setFontScale(Constants.FONT_SIZE * 0.2f);
        quantityLabel1.setFontScale(Constants.FONT_SIZE * 0.25f);
        quantityLabel2.setFontScale(Constants.FONT_SIZE * 0.25f);
        quantityLabel3.setFontScale(Constants.FONT_SIZE * 0.25f);
        quantityLabel4.setFontScale(Constants.FONT_SIZE * 0.25f);
        resourcesTitleLabel1.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesTitleLabel2.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesTitleLabel3.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesTitleLabel4.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesLabel1.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesLabel2.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesLabel3.setFontScale(Constants.FONT_SIZE * 0.2f);
        resourcesLabel4.setFontScale(Constants.FONT_SIZE * 0.2f);



        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.getSoundFactory().playPickUp();
                StorageScreen.this.game.showGameScreen();
            }
        });
        button1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.getSoundFactory().playSelect2();
                String titulo = "Expandir deposito de agua";
                String messaje = "¿Desea expandir el deposito\n por 100.000 F. ?\nla capacidad aumentara\nen 10.000 litros";
                DialogFactory.showOkCancelDialog(StorageScreen.this, stage, titulo, messaje, 0.5f,0.39f, 1, null);
            }
        });
        button2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.getSoundFactory().playSelect2();
                String titulo = "Expandir camara frigorifica";
                String messaje = "¿Desea expandir la camara frigorifica\npor 100.000 F. ?\nla capacidad aumentara\nen 2.000 litros";
                DialogFactory.showOkCancelDialog(StorageScreen.this, stage, titulo, messaje, 0.5f,0.39f, 2, null);
            }
        });
        button3.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.getSoundFactory().playSelect2();
                String titulo = "Expandir almacen general";
                String messaje = "¿Desea expandir el deposito\npor 100.000 F. ?\nla capacidad aumentara\nen 10.000 litros";
                DialogFactory.showOkCancelDialog(StorageScreen.this, stage, titulo, messaje, 0.5f,0.39f, 3, null);
            }
        });
        button4.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StorageScreen.this.game.getSoundFactory().playSelect2();
                String titulo = "Expandir granero";
                String messaje = "¿Desea expandir el granero\npor 100.000 F. ?\nla capacidad aumentara\nen 6.000 litros";
                DialogFactory.showOkCancelDialog(StorageScreen.this, stage, titulo, messaje, 0.5f,0.39f, 4, null);
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button1.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button2.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button3.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        button4.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        area1.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area2.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area3.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        area4.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.19f);
        fundsLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        depositNameLabel1.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel2.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel3.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        depositNameLabel4.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel1.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel2.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel3.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel4.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel1.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel2.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel3.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityLabel4.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel1.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel2.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel3.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesTitleLabel4.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel1.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel2.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel3.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);
        resourcesLabel4.setSize(Constants.DEVICE_WIDTH *0.2f, Constants.DEVICE_HEIGHT * 0.08f);




        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVICE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        area1.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.655f);
        area2.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.445f);
        area3.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.235f);
        area4.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.025f);
        button1.setPosition(Constants.DEVICE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.7f);
        button2.setPosition(Constants.DEVICE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.49f);
        button3.setPosition(Constants.DEVICE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.28f);
        button4.setPosition(Constants.DEVICE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.07f);
        depositNameLabel1.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.71f);
        depositNameLabel2.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.51f);
        depositNameLabel3.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.29f);
        depositNameLabel4.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityTitleLabel1.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.75f);
        quantityTitleLabel2.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.54f);
        quantityTitleLabel3.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.33f);
        quantityTitleLabel4.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.12f);
        quantityLabel1.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.75f);
        quantityLabel2.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.54f);
        quantityLabel3.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.33f);
        quantityLabel4.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.12f);
        resourcesTitleLabel1.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.68f);
        resourcesTitleLabel2.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.47f);
        resourcesTitleLabel3.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.26f);
        resourcesTitleLabel4.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.05f);
        resourcesLabel1.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.68f);
        resourcesLabel2.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.47f);
        resourcesLabel3.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.26f);
        resourcesLabel4.setPosition(Constants.DEVICE_WIDTH * 0.47f, Constants.DEVICE_HEIGHT * 0.05f);



        //estados
        area1.setTouchable(Touchable.disabled);
        area2.setTouchable(Touchable.disabled);
        area3.setTouchable(Touchable.disabled);
        area4.setTouchable(Touchable.disabled);
        goBackButton.setColor(Color.GREEN);
        area1.setColor(1,1,1,0.9f);
        area2.setColor(1,1,1,0.9f);
        area3.setColor(1,1,1,0.9f);
        area4.setColor(1,1,1,0.9f);


        //Se añaden los elementos
        stage.addActor(backgroundImage);
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
    public void disableAll(boolean enableDisable){
        goBackButton.setDisabled(enableDisable);
        button1.setDisabled(enableDisable);
        button2.setDisabled(enableDisable);
        button3.setDisabled(enableDisable);
        button4.setDisabled(enableDisable);

    }
    public void actions(int actionIndex){
        int resources = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MONEY).getOcupacionAactual();
        if (resources < 100000){
            DialogFactory.showOkDialog(this, stage, "No hay fondos", "No dispone de los 100.000 F\n necesarios para la expansion", 0.4f, 0.35f);
            return;
        }
        this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MONEY).setOcupacionAactual(resources - 100000);
        int capacity;
        switch (actionIndex){
            case 1:
                capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.WATER).getCapacidadMaxima();
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.WATER).setCapacidadMaxima(capacity + 10000);
                break;
            case 2:
                capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MEAT).getCapacidadMaxima();
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MEAT).setCapacidadMaxima(capacity + 2000);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MILK).setCapacidadMaxima(capacity + 2000);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.EGG).setCapacidadMaxima(capacity + 2000);
                break;
            case 3:
                capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MEAT).getCapacidadMaxima();
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.HERBIZIDE).setCapacidadMaxima(capacity + 10000);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.MANURE).setCapacidadMaxima(capacity + 10000);
                break;
            case 4:
                capacity = this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.CORN).getCapacidadMaxima();
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.CORN).setCapacidadMaxima(capacity + 6000);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.STRAWBERRY).setCapacidadMaxima(capacity + 6000);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(TipoRecurso.POTATO).setCapacidadMaxima(capacity + 6000);
                break;
            default:

        }
        this.game.getUserController().saveUser();
        this.game.showStorageScreen();
    }


}
