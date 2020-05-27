package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Screens.BaseScreen;

public class DialogFactory {

    public static void showOkDialog(final BaseScreen screen,
                                    Stage stage, String title,
                                    String messaje, Float width,
                                    Float height){
        screen.disableAll(true);
        Skin skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        //Cuerpo de la ventana
        final Window window = new Window("", skin, "dialog");
        window.setSize(Constants.DEVICE_WIDTH * width, Constants.DEVICE_HEIGHT * height);
        window.setPosition(Constants.DEVICE_WIDTH / 2 - (Constants.DEVICE_WIDTH * width / 2), Constants.DEVICE_HEIGHT / 2- (Constants.DEVICE_HEIGHT * height / 2));
        window.setTouchable(Touchable.disabled);
        stage.addActor(window);

        //boton de conformación
        final TextButton textButton= new TextButton("Aceptar", skin);
        textButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        textButton.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        textButton.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.05f);
        textButton.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        stage.addActor(textButton);

        //titulo
        final Label titleLabel = new Label(title, skin, "required");
        titleLabel.setFontScale(Constants.FONT_SIZE);
        titleLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.1f);
        stage.addActor(titleLabel);


        final Label textLabel = new Label(messaje, skin, "custom_blue");
        textLabel.setFontScale(Constants.FONT_SIZE* 0.8f);
        textLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        textLabel.setAlignment(Align.center);
        textLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.2f);
        stage.addActor(textLabel);




        //funcionalidades
        textButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               //DIMISS ALL
                textButton.remove();
                window.remove();
                textLabel.remove();
                titleLabel.remove();
                screen.disableAll(false);
            }
        });
    }


    public static void showOkCancelDialog(final BaseScreen screen,
                                          Stage stage, String title,
                                          String messaje,
                                          Float width,
                                          Float height,
                                          final Integer okAction,
                                          final Integer cancelAction){
        screen.disableAll(true);
        Skin skin = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        //Cuerpo de la ventana
        final Window window = new Window("", skin, "dialog");
        window.setSize(Constants.DEVICE_WIDTH * width, Constants.DEVICE_HEIGHT * height);
        window.setPosition(Constants.DEVICE_WIDTH / 2 - (Constants.DEVICE_WIDTH * width / 2), Constants.DEVICE_HEIGHT / 2- (Constants.DEVICE_HEIGHT * height / 2));
        window.setTouchable(Touchable.disabled);
        stage.addActor(window);

        //boton de conformación
        final TextButton okButton= new TextButton("Aceptar", skin);
        okButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        okButton.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        okButton.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.115f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.05f);
        okButton.setStyle(StyleFactory.BLUE_TEXT_BUTTON_STYLE());
        stage.addActor(okButton);

        //boton de cancelación
        final TextButton cancelButton = new TextButton("Cancelar", skin);
        cancelButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.8f);
        cancelButton.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        cancelButton.setPosition(Constants.DEVICE_WIDTH / 2 + Constants.DEVICE_WIDTH * 0.015f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.05f);
        cancelButton.setStyle(StyleFactory.ORANGE_TEXT_BUTTON_STYLE());
        stage.addActor(cancelButton);

        //titulo
        final Label titleLabel = new Label(title, skin, "required");
        titleLabel.setFontScale(Constants.FONT_SIZE);
        titleLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.1f);
        stage.addActor(titleLabel);


        final Label textLabel = new Label(messaje, skin, "custom_blue");
        textLabel.setFontScale(Constants.FONT_SIZE* 0.8f);
        textLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        textLabel.setAlignment(Align.center);
        textLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.2f);
        stage.addActor(textLabel);




        //funcionalidades
        okButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //DIMISS ALL
                okButton.remove();
                cancelButton.remove();
                window.remove();
                textLabel.remove();
                titleLabel.remove();
                screen.disableAll(false);
                if (okAction != null)screen.actions(okAction);
            }
        });

        cancelButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //DIMISS ALL
                okButton.remove();
                cancelButton.remove();
                window.remove();
                textLabel.remove();
                titleLabel.remove();
                screen.disableAll(false);
                if(cancelAction != null) screen.actions(cancelAction);
            }
        });
    }

}
