package com.mygdx.game.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Constants;
import com.mygdx.game.screens.BaseScreen;

public class DialogFactory {

    public static void showOkDialog(final BaseScreen screen,
                                    Stage stage, String title,
                                    String messaje, Float width,
                                    Float height){
        screen.disableAll(true);
        Skin skin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        //Cuerpo de la ventana
        final Window window = new Window("", skin2, "dialog");
        window.setSize(Constants.DEVICE_WIDTH * width, Constants.DEVICE_HEIGHT * height);
        window.setPosition(Constants.DEVICE_WIDTH / 2 - (Constants.DEVICE_WIDTH * width / 2), Constants.DEVICE_HEIGHT / 2- (Constants.DEVICE_HEIGHT * height / 2));
        window.setTouchable(Touchable.disabled);
        stage.addActor(window);

        //boton de conformación
        final TextButton textButton= new TextButton("Aceptar", skin, "big");
        textButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.25f);
        textButton.setSize(Constants.DEVICE_WIDTH * 0.12f, Constants.DEVICE_HEIGHT * 0.08f);
        textButton.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.06f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.04f);
        textButton.setColor(Color.PURPLE);
        stage.addActor(textButton);

        //titulo
        final Label titleLabel = new Label(title, skin, "big");
        titleLabel.setFontScale(Constants.FONT_SIZE * 0.25f);
        titleLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.1f);
        stage.addActor(titleLabel);


        final Label textLabel = new Label(messaje, skin, "big-blue");
        textLabel.setFontScale(Constants.FONT_SIZE* 0.2f);
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
                                          Stage stage,
                                          String title,
                                          String message,
                                          Float width,
                                          Float height,
                                          final Integer okAction,
                                          final Integer cancelAction){
        screen.disableAll(true);
        Skin skin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("skins/skin/skin-composer-ui.json"));

        //Cuerpo de la ventana
        final Window window = new Window("", skin2, "dialog");
        window.setSize(Constants.DEVICE_WIDTH * width, Constants.DEVICE_HEIGHT * height);
        window.setPosition(Constants.DEVICE_WIDTH / 2 - (Constants.DEVICE_WIDTH * width / 2), Constants.DEVICE_HEIGHT / 2- (Constants.DEVICE_HEIGHT * height / 2));
        window.setTouchable(Touchable.disabled);
        stage.addActor(window);

        //boton de conformación
        final TextButton okButton= new TextButton("Aceptar", skin, "big");
        okButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.25f);
        okButton.setSize(Constants.DEVICE_WIDTH * 0.125f, Constants.DEVICE_HEIGHT * 0.08f);
        okButton.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.135f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.04f);

        stage.addActor(okButton);

        //boton de cancelación
        final TextButton cancelButton = new TextButton("Cancelar", skin, "big");
        cancelButton.getLabel().setFontScale(Constants.FONT_SIZE * 0.25f);

        cancelButton.setSize(Constants.DEVICE_WIDTH * 0.125f, Constants.DEVICE_HEIGHT * 0.08f);
        cancelButton.setPosition(Constants.DEVICE_WIDTH / 2 + Constants.DEVICE_WIDTH * 0.035f,
                Constants.DEVICE_HEIGHT /2 - Constants.DEVICE_HEIGHT * (height/2) + Constants.DEVICE_HEIGHT* 0.04f);
        cancelButton.setColor(Color.PURPLE);
        stage.addActor(cancelButton);

        //titulo
        final Label titleLabel = new Label(title, skin, "big");
        titleLabel.setFontScale(Constants.FONT_SIZE * 0.3f);
        titleLabel.setSize(Constants.DEVICE_WIDTH * 0.1f, Constants.DEVICE_HEIGHT * 0.08f);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(Constants.DEVICE_WIDTH / 2 - Constants.DEVICE_WIDTH * 0.05f,
                Constants.DEVICE_HEIGHT /2 + Constants.DEVICE_HEIGHT * (height/2) - Constants.DEVICE_HEIGHT* 0.1f);
        stage.addActor(titleLabel);


        final Label textLabel = new Label(message, skin, "big-blue");
        textLabel.setFontScale(Constants.FONT_SIZE* 0.22f);
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
