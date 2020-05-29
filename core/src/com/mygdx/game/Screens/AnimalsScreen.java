package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Constants;
import com.mygdx.game.DialogFactory;
import com.mygdx.game.DomainMocker;
import com.mygdx.game.Dominio.Animal;
import com.mygdx.game.Dominio.Espacio;
import com.mygdx.game.Dominio.Precio;
import com.mygdx.game.Dominio.TipoRecurso;
import com.mygdx.game.Images.ImageClampToEdge;
import com.mygdx.game.MainGame;
import com.mygdx.game.StyleFactory;
import com.mygdx.game.control.AnimalController;
import com.mygdx.game.control.EspacioController;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
    private int selectedAnimalType;
    private ArrayList<Animal> animals;
    private Integer animalToSacrifice ,meatKgInSacrifice;
    private EspacioController espacioController;
    private ArrayList<Precio> prices;
    ArrayList<Espacio> espacios;
    private ImageClampToEdge backgroundImage;
    private Texture backgroundTexture;

    public AnimalsScreen(MainGame game) {
        super(game);
        this.stage = new Stage(new FitViewport(Constants.DEVICE_WIDTH, Constants.DEVICE_HEIGHT));
        this.world = new World(new Vector2(0, 0), true);

        this.selectedAnimalType = TipoRecurso.COW;
        espacioController = new EspacioController(this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios());
        animals = (ArrayList<Animal>)this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(this.selectedAnimalType).getAnimales();
        prices = (ArrayList<Precio>)this.game.getUsuario().getGranja().getPrecios();
        espacios = (ArrayList<Espacio>)this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios();

        // apariencias de los skins
        this.skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        backgroundTexture = game.getAssetManager().get("Textures/BackGrounds/animalBack.jpg");
        backgroundImage = new ImageClampToEdge(backgroundTexture, 0,0, Constants.DEVICE_WIDTH / Constants.PIXELS_IN_METER,
                Constants.DEVICE_HEIGHT / Constants.PIXELS_IN_METER);

        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();

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
        fundsLabel = new Label(String.format("Fondos: %s", recursos),skin, "required");
        capacityLabel = new Label("", skin, "custom_grey");
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
                cowsButton.setStyle(StyleFactory.getStyle(StyleFactory.DARK_GREY_BLUE_COLOR, StyleFactory.GREY_BLUE_COLOR));
                pigsButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                chickenButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                AnimalsScreen.this.selectedAnimalType = TipoRecurso.COW;
                fillTableData();
                setAllCheckboxToOff();
            }
        });
        pigsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pigsButton.setStyle(StyleFactory.getStyle(StyleFactory.DARK_GREY_BLUE_COLOR, StyleFactory.GREY_BLUE_COLOR));
                cowsButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                chickenButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                AnimalsScreen.this.selectedAnimalType = TipoRecurso.PIG;
                fillTableData();
                setAllCheckboxToOff();
            }
        });
        chickenButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chickenButton.setStyle(StyleFactory.getStyle(StyleFactory.DARK_GREY_BLUE_COLOR, StyleFactory.GREY_BLUE_COLOR));
                pigsButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                cowsButton.setStyle(StyleFactory.getStyle(StyleFactory.GREY_BLUE_COLOR, StyleFactory.DARK_GREY_BLUE_COLOR));
                AnimalsScreen.this.selectedAnimalType = TipoRecurso.CHICKEN;
                fillTableData();
                setAllCheckboxToOff();
            }
        });
        buyButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (animals.size() < 5) {
                    int price = (int)(prices.get(selectedAnimalType).getPrecio() * 1.1f);
                    DialogFactory.showOkCancelDialog(AnimalsScreen.this, stage,
                            "Comprar",
                            String.format("El nuevo animal le costara %s\nDesea completar la compra?", price),
                            0.4f,
                            0.35f,
                            3,
                            null);

                }else{
                    DialogFactory.showOkDialog(AnimalsScreen.this, stage,
                            "Espacio insuficiente",
                            "No queda espacio en el corral.\nNo es posible realizar la compra",
                            0.45f,
                            0.35f);
                }
            }
        });
        sellButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String names = "";
                int totalPrice = 0;
                boolean atLeastOneIsChecked = false;
                for (int i = 0; i < rows[0].length; i++){
                    if (checkBoxes[i].isChecked()){
                        atLeastOneIsChecked = true;
                        totalPrice += DomainMocker.getAllResorurcesList().get(selectedAnimalType).getPrecioMinimo()* 1f;
                        names = String.format( "%s, %s", names, animals.get(i).getNombre());
                    }
                }
                names = names.substring(1);
                if(atLeastOneIsChecked){
                    DialogFactory.showOkCancelDialog(AnimalsScreen.this, stage,
                            "Vender",
                            String.format("Quieres vender a:\n%s\npor %s F ?", names, totalPrice),
                            0.55f,
                            0.35f,
                            4,
                            5);
                }else{
                    DialogFactory.showOkDialog(AnimalsScreen.this, stage,
                            "Error",
                            String.format("No hay animales seleccionados!"),
                            0.4f,
                            0.35f);
                }
            }
        });

        reproduceSwitchButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //swich reproduction code
            }
        });

        //Tamaños de los elementos
        goBackButton.setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT *0.10f);
        buyButton.setSize(Constants.DEVICE_WIDTH * 0.12f, Constants.DEVICE_HEIGHT *0.06f);
        sellButton.setSize(Constants.DEVICE_WIDTH * 0.12f, Constants.DEVICE_HEIGHT *0.06f);
        fundsLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        capacityLabel.setSize(Constants.DEVICE_WIDTH *0.4f, Constants.DEVICE_HEIGHT * 0.10f);
        advertLabel.setSize(Constants.DEVICE_WIDTH *0.6f, Constants.DEVICE_HEIGHT * 0.10f);
        cowsButton.setSize(Constants.DEVICE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        chickenButton.setSize(Constants.DEVICE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        pigsButton.setSize(Constants.DEVICE_WIDTH *0.3f, Constants.DEVICE_HEIGHT * 0.13f);
        areaB.setSize(Constants.DEVICE_WIDTH *0.88f, Constants.DEVICE_HEIGHT * 0.7f);
        table.setSize(Constants.DEVICE_WIDTH *0.69f, Constants.DEVICE_HEIGHT * 0.44f);
        tabletop.setSize(Constants.DEVICE_WIDTH *0.69f, Constants.DEVICE_HEIGHT * 0.08f);
        reproduceSwitchButton.setSize(Constants.DEVICE_WIDTH *0.06f, Constants.DEVICE_HEIGHT * 0.04f);
        nameHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        typeHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);
        dateHeadLabel.setSize(Constants.DEVICE_WIDTH *0.15f, Constants.DEVICE_HEIGHT * 0.08f);

        //posiciones de los elementos
        goBackButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.87f);
        fundsLabel.setPosition(Constants.DEVICE_WIDTH * 0.03f, Constants.DEVICE_HEIGHT * 0.87f);
        cowsButton.setPosition(Constants.DEVICE_WIDTH * 0.05f, Constants.DEVICE_HEIGHT * 0.72f);
        pigsButton.setPosition(Constants.DEVICE_WIDTH * 0.35f, Constants.DEVICE_HEIGHT * 0.72f);
        chickenButton.setPosition(Constants.DEVICE_WIDTH * 0.65f, Constants.DEVICE_HEIGHT * 0.72f);
        areaB.setPosition(Constants.DEVICE_WIDTH * 0.06f, Constants.DEVICE_HEIGHT * 0.03f);
        tabletop.setPosition(Constants.DEVICE_WIDTH * 0.08f, Constants.DEVICE_HEIGHT * 0.45f);
        table.setPosition(Constants.DEVICE_WIDTH * 0.08f, Constants.DEVICE_HEIGHT * 0.03f);
        capacityLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.61f);
        advertLabel.setPosition(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.53f);
        buyButton.setPosition(Constants.DEVICE_WIDTH * 0.6f, Constants.DEVICE_HEIGHT * 0.63f);
        sellButton.setPosition(Constants.DEVICE_WIDTH * 0.75f, Constants.DEVICE_HEIGHT * 0.63f);
        reproduceSwitchButton.setPosition(Constants.DEVICE_WIDTH * 0.83f, Constants.DEVICE_HEIGHT * 0.555f);
        nameHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.16f, Constants.DEVICE_HEIGHT * 0.45f);
        typeHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.36f, Constants.DEVICE_HEIGHT * 0.45f);
        dateHeadLabel.setPosition(Constants.DEVICE_WIDTH * 0.52f, Constants.DEVICE_HEIGHT * 0.45f);

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
        stage.addActor(backgroundImage);
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

        //checkboxses & buttons
        for(int i = 0; i< checkBoxes.length;i++){
            checkBoxes[i] = new CheckBox("", skin);
            buttons[i] = new TextButton("Sacrificar",skin);
            buttons[i].getLabel().setFontScale(Constants.FONT_SIZE * 0.7f);
            checkBoxes[i].getCells().get(0).size(Constants.DEVICE_WIDTH * 0.025f, Constants.DEVICE_WIDTH * 0.025f);
            checkBoxes[i].getImage().setScaling(Scaling.fit);
            buttons[i].setSize(Constants.DEVICE_WIDTH * 0.10f, Constants.DEVICE_HEIGHT * 0.06f);
            checkBoxes[i].setPosition(Constants.DEVICE_WIDTH *( 0.12f ), Constants.DEVICE_HEIGHT * (0.395f - i * 0.08f));
            buttons[i].setPosition(Constants.DEVICE_WIDTH *( 0.805f ), Constants.DEVICE_HEIGHT * (0.365f - i * 0.08f));
            buttons[i].setStyle(StyleFactory.getStyle(StyleFactory.RED_COLOR, StyleFactory.DARK_RED_COLOR));
            final Integer finalI = i;

            buttons[i].addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    animalToSacrifice = finalI;

                    if(animals.get(finalI).getTipoAnimal().getId() == TipoRecurso.COW) meatKgInSacrifice = 300;
                    else if(animals.get(finalI).getTipoAnimal().getId() == TipoRecurso.PIG) meatKgInSacrifice = 150;
                    else meatKgInSacrifice = 2;
                    String mensaje = String.format("Seguro que deseas sacrificar a\n %s ? El sacrificio producira\n%s kilos de Carne",
                            animals.get(finalI).getNombre(),
                            meatKgInSacrifice);
                    DialogFactory.showOkCancelDialog(
                            AnimalsScreen.this, stage,
                            "Sacrificar animal",
                            mensaje,
                            0.35f,
                            0.4f,
                            1,
                            2);
                }
            });
            stage.addActor(checkBoxes[i]);
            stage.addActor(buttons[i]);
        }
        for(int i = 0; i< rows.length;i++) {
            for (int j = 0; j < rows[0].length; j++) {
                rows[i][j] = new Label("", skin, "required");
                rows[i][j].setFontScale(Constants.FONT_SIZE * 0.8f);
                rows[i][j].setAlignment(Align.center);
                rows[i][j].setSize(Constants.DEVICE_WIDTH * 0.15f, Constants.DEVICE_HEIGHT * 0.08f);
                rows[i][j].setPosition(Constants.DEVICE_WIDTH * (0.12f + i * 0.2f), Constants.DEVICE_HEIGHT * (0.36f - j * 0.08f));
                stage.addActor(rows[i][j]);
            }
        }
        //datos de la tabla
        fillTableData();
    }

    private void fillTableData() {
       animals = (ArrayList<Animal>)this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(this.selectedAnimalType).getAnimales();
       capacityLabel.setText(String.format("Ocupacion actual del corral: %s / 5", animals.size()));
        GregorianCalendar gc;
       for(int i = 0; i< rows.length;i++){
            for(int j = 0; j< rows[0].length;j++){
                String texto;
                if (j< animals.size()) {
                    gc = animals.get(j).getFechaNacimiento();
                    if (i == 0) {
                        texto = animals.get(j).getNombre();
                    } else if (i == 1) {
                        texto = "animal joven"; //create animalcontroller, give back text based on age
                    } else {
                        texto = String.format("%s / %s / %s",gc.get(5),gc.get(2),gc.get(1));
                    }
                }else {
                    texto = "";
                }
                rows[i][j].setText(texto);
                checkBoxes[j].setDisabled(false);
                buttons[j].setDisabled(false);
            }
        }
       for(int i = animals.size(); i< rows[0].length;i++){
           checkBoxes[i].setDisabled(true);
           buttons[i].setDisabled(true);
        }
        int recursos= this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(0).getOcupacionAactual();
       fundsLabel.setText(String.format("Fondos: %s", recursos));
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
        this.goBackButton.setDisabled(enableDisable);
        this.cowsButton.setDisabled(enableDisable);
        this.pigsButton.setDisabled(enableDisable);
        this.chickenButton.setDisabled(enableDisable);
        this.buyButton.setDisabled(enableDisable);
        this.sellButton.setDisabled(enableDisable);
        this.reproduceSwitchButton.setDisabled(enableDisable);
        for (int i = 0; i < 5; i++){
            checkBoxes[i].setDisabled(enableDisable);
            buttons[i].setDisabled(enableDisable);
        }
    this.fillTableData();
    }

    public void setAllCheckboxToOff(){
        for (int i = 0; i < rows[0].length; i++){
            checkBoxes[i].setChecked(false);
        }
    }

    public void actions(int actionIndex){

        switch (actionIndex){
            case 1:
                //Sacrificar animal
                if (animalToSacrifice != null){
                    if(espacioController.putIn(TipoRecurso.MEAT, meatKgInSacrifice)){
                        Animal animal = animals.get(animalToSacrifice);
                        this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(this.selectedAnimalType).getAnimales().remove(animal);

                        fillTableData();
                    }
                    else {
                        DialogFactory.showOkDialog(
                                this, stage,
                                "Espacio insuficiente",
                                "Ups, no hay espacio\n en tu camara frigorifica\npara la carne.",
                                0.4f,0.4f);
                    }
                }else{
                    DialogFactory.showOkDialog(this, stage,
                            "Error",
                            "Ups, algo ha fallado\n y no hemos podido llevar a\ncabo el sacrificio",
                            0.4f,0.4f);
                }
            case 2:
                //Cancelar sacrificio
                animalToSacrifice = null;
                meatKgInSacrifice = 0;
                break;
            case 3:
                //compar animal
                int price = (int)(prices.get(selectedAnimalType).getPrecio() * 1.1f);
                if (espacioController.putIn(TipoRecurso.MONEY, -price)){
                    Animal newAnimal = new Animal(
                            animals.size(),
                            AnimalController.getRNDName(),
                            new GregorianCalendar(),
                            DomainMocker.getAllResorurcesList().get(selectedAnimalType));
                    this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(this.selectedAnimalType).getAnimales().add(newAnimal);
                    fillTableData();
                }else{
                    DialogFactory.showOkDialog(
                            this, stage,
                            "Fondos Insuficientes",
                            "No dispones de direno\nsuficiente para realizar la compra",
                            0.4f,
                            0.35f
                            );
                }
                break;
            case 4:
                //vender anmales
                int totalPrice = 0;
                ArrayList<Animal> animalsToRemove = new ArrayList<>();
                for (int i = 0; i < rows[0].length; i++){
                    if (checkBoxes[i].isChecked()){
                        totalPrice += DomainMocker.getAllResorurcesList().get(selectedAnimalType).getPrecioMinimo() * 1f;
                        Animal animalToShell = animals.get(i);
                        animalsToRemove.add(animalToShell);
                    }
                }
                espacioController.putIn(TipoRecurso.MONEY, totalPrice);
                this.game.getUsuario().getGranja().getInfraestructuras().get(0).getEspacios().get(this.selectedAnimalType).getAnimales().removeAll(animalsToRemove);
                fillTableData();
                break;
            case 5:
                //Cancelar venta
                fillTableData();
                break;
            default:
        }
        setAllCheckboxToOff();
        this.game.getUserController().saveUser();
    }
}
