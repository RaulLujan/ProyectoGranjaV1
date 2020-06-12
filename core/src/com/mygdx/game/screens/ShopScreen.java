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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.dominio.Espacio;
import com.mygdx.game.dominio.Precio;
import com.mygdx.game.images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.factories.StyleFactory;
import com.mygdx.game.control.EspacioController;

import java.util.ArrayList;

public class ShopScreen extends BaseScreen {
    private Stage stage;
    private World world;

    private Skin skin, glassSkin;

    private TextButton goBackButton;
    private Window areaT, areaB;
    private Label fundsLabel, resourceNameHeadLabel, pricesHeadLabel, quantityHeadLabel;
    private Label[] [] rows;
    private Button[][] addButtons;
    private Label [] quantityLabels;
    private TextButton[][] actionButton;
    private EspacioController espacioController;
    private ArrayList<Precio> prices;
    private ArrayList<Espacio> espacios;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;



    public ShopScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);

        espacioController = new EspacioController(this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios());
        prices = (ArrayList<Precio>)this.game.getUsuario().getGranja().getPrecios();
        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();

        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));
        this.glassSkin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/shopBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);

        // inicialización de los elementos
        goBackButton = new TextButton("Volver", glassSkin, "big");
        areaT = new Window("", skin);
        areaB = new Window("", skin,"dialog");
        fundsLabel = new Label(String.format("Fondos: %s", recursos), glassSkin, "big");
        resourceNameHeadLabel = new Label("Recurso",glassSkin, "big-white");
        pricesHeadLabel = new Label("Precios V/C",glassSkin, "big-white");
        quantityHeadLabel = new Label("Cantidad",glassSkin, "big-white");
        rows = new Label[3][9];
        addButtons = new Button[2][rows[0].length];
        quantityLabels = new Label[rows[0].length];
        actionButton = new TextButton[2][rows[0].length];

        // Tamaño de la fuente
        goBackButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.3f);
        fundsLabel.setFontScale(Constants.FONT_SIZE* 0.25f);
        resourceNameHeadLabel.setFontScale(Constants.FONT_SIZE * 0.24f);
        pricesHeadLabel.setFontScale(Constants.FONT_SIZE * 0.24f);
        quantityHeadLabel.setFontScale(Constants.FONT_SIZE * 0.24f);

        //funcionalidades
        goBackButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ShopScreen.this.game.getSoundFactory().playPickUp();
                ShopScreen.this.game.showGameScreen();
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        areaT.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.08f);
        areaB.setSize(Constants.DEVICE_WIDTH *0.9f, Constants.DEVICE_HEIGHT * 0.72f);
        fundsLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);

        resourceNameHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        pricesHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        quantityHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);

        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVICE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        areaT.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.75f);
        areaB.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.03f);

        resourceNameHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.09f, Constants.DEVICE_HEIGHT * 0.75f);
        pricesHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.24f, Constants.DEVICE_HEIGHT * 0.75f);
        quantityHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.4f, Constants.DEVICE_HEIGHT * 0.75f);

        //estados
        areaT.setStyle(StyleFactory.getStyle(StyleFactory.BLUE_COLOR));
        areaT.setTouchable(Touchable.disabled);
        areaB.setTouchable(Touchable.disabled);
        goBackButton.setColor(Color.GREEN);
        areaB.setColor(1f,1f,1f,0.85f);
        areaT.setColor(1f,1f,1f,0.9f);

        //Se añaden los elementos
        stage.addActor(backgroundImage);
        stage.addActor(goBackButton);

        stage.addActor(areaB);
        stage.addActor(areaT);
        stage.addActor(fundsLabel);
        stage.addActor(resourceNameHeadLabel);
        stage.addActor(pricesHeadLabel);
        stage.addActor(quantityHeadLabel);


       //ESPACIOS
        espacios = (ArrayList<Espacio>)this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios();


        //datos de la tabla
        for(int i = 0; i< rows.length;i++){
            for(int j = 0; j< rows[i].length;j++){

                if (i == 0){
                    rows[i][j] = new Label(prices.get(j+1).getTipoRecurso().getNombre(),glassSkin,"big");
                }else if (i == 1){
                    int buy = prices.get(j+1).getPrecio();
                    int sell = (int)(prices.get(j+1).getPrecio() * 1.1);
                    String precioVC = String.format("%s / %s",buy,sell);
                    rows[i][j] = new Label(precioVC,glassSkin,"big");
                }else{
                    rows[i][j] = new Label(String.format("%s",espacios.get(j+1).getOcupacionAactual()) ,glassSkin,"big");
                }


                rows[i][j].setFontScale(Constants.FONT_SIZE * 0.22f);
                rows[i][j].setAlignment(Align.center);
                rows[i][j].setSize(Constants.DEVICE_WIDTH * 0.11f, Constants.DEVICE_HEIGHT * 0.08f);
                rows[i][j].setPosition(Constants.DEVICE_WIDTH *( 0.09f + i * 0.15f ), Constants.DEVICE_HEIGHT * (0.03f + j * 0.08f));
                stage.addActor(rows[i][j]);

            }
        }
        //botones de añadir-restar
        for(int i = 0; i< addButtons.length; i++) {
            for (int j = 0; j < addButtons[i].length; j++) {
                String type;
                if (i == 1) type = "spinner-plus-h"; else type = "spinner-minus-h";
                addButtons[i][j] = new Button(skin, type);
                addButtons[i][j].setSize(Constants.DEVICE_WIDTH * 0.04f, Constants.DEVICE_WIDTH * 0.03f);
                addButtons[i][j].setPosition(Constants.DEVICE_WIDTH *( 0.54f + i * 0.1f ), Constants.DEVICE_HEIGHT * (0.04f + j * 0.08f));

                //funcionalidades
                final int finalI = i;
                final int finalJ = j;
                addButtons[i][j].addCaptureListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        ShopScreen.this.game.getSoundFactory().playSelect3();
                        int actualQuantity = Integer.parseInt(quantityLabels[finalJ].getText().toString());
                        if (finalI == 0) {
                            if (actualQuantity < 101 ) addTo(quantityLabels[finalJ],-10);
                            else if (actualQuantity < 501 ) addTo(quantityLabels[finalJ],-50);
                            else if (actualQuantity < 2001 ) addTo(quantityLabels[finalJ],-100);
                            else if (actualQuantity < 5001 ) addTo(quantityLabels[finalJ],-500);
                            else addTo(quantityLabels[finalJ],-2000);
                        }
                        else {
                            if (actualQuantity < 100 ) addTo(quantityLabels[finalJ],10);
                            else if (actualQuantity < 500 ) addTo(quantityLabels[finalJ],50);
                            else if (actualQuantity < 2000 ) addTo(quantityLabels[finalJ],100);
                            else if (actualQuantity < 5000 ) addTo(quantityLabels[finalJ],500);
                            else addTo(quantityLabels[finalJ],2000);
                        }
                    }
                });
                stage.addActor(addButtons[i][j]);
            }
        }
        //cantidades a  comprar & vender
        for(int i = 0; i< quantityLabels.length;i++){
            quantityLabels[i] = new Label("000",glassSkin,"big-blue");
            quantityLabels[i].setFontScale(Constants.FONT_SIZE * 0.22f);
            quantityLabels[i].setAlignment(Align.center);
            quantityLabels[i].setSize(Constants.DEVICE_WIDTH * 0.04f, Constants.DEVICE_HEIGHT * 0.08f);
            quantityLabels[i].setPosition(Constants.DEVICE_WIDTH * 0.59f, Constants.DEVICE_HEIGHT * (0.025f +i * 0.08f));
            stage.addActor(quantityLabels[i]);

        }

        //botones de Comprar vender
        for(int i = 0; i< actionButton.length; i++) {
            for (int j = 0; j < actionButton[i].length; j++) {
                String text;
                if (i == 1) {
                    text = "Compar";
                    actionButton[i][j] = new TextButton(text ,glassSkin, "small");
                } else {
                    text = "Vender";
                    actionButton[i][j] = new TextButton(text ,glassSkin, "small");
                    actionButton[i][j].setColor(Color.PURPLE);
                }

                actionButton[i][j].getLabel().setFontScale(Constants.FONT_SIZE * 0.6f);

                actionButton[i][j].setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.06f);
                actionButton[i][j].setPosition(Constants.DEVICE_WIDTH *( 0.71f + i * 0.125f ), Constants.DEVICE_HEIGHT * (0.036f + j * 0.08f));

                //funcionalidades
                final int finalI = i;
                final int finalJ = j;
                actionButton[i][j].addCaptureListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        ShopScreen.this.game.getSoundFactory().playSelect2();
                        int quantity = Integer.parseInt(quantityLabels[finalJ].getText().toString());
                        int buyMoney = prices.get(finalJ+1).getPrecio();
                        int sellMoney = (int)(prices.get(finalJ+1).getPrecio() * 1.1);
                        if (finalI == 0) {
                            espacioController.putForMoney(finalJ+1, -quantity, -buyMoney * quantity);
                        }
                        else {
                            espacioController.putForMoney(finalJ+1, quantity, sellMoney * quantity);
                        }
                        quantityLabels[finalJ].setText("000");
                        fundsLabel.setText(String.format("Fondos: %s",
                                ShopScreen.this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual()));
                        rows[2][finalJ].setText(espacios.get(finalJ+1).getOcupacionAactual());
                        ShopScreen.this.game.getUserController().saveUser();
                    }
                });


                stage.addActor(actionButton[i][j]);
            }
        }
    }

    private void addTo(Label label, int quantity){
        int actual = Integer.parseInt(label.getText().toString());
        int result = actual + quantity;
        if (result > 0) label.setText(result);
        else label.setText(0);
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
        for (int i = 0; i < 9; i++){
            addButtons[0][i].setDisabled(enableDisable);
            addButtons[1][i].setDisabled(enableDisable);
            actionButton[0][i].setDisabled(enableDisable);
            actionButton[1][i].setDisabled(enableDisable);
        }
    }
    public void actions(int actionIndex){

    }
}
