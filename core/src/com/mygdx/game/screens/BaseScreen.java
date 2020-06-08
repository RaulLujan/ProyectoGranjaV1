package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MainGame;

public abstract class BaseScreen implements Screen {

    protected MainGame game;



    public BaseScreen(MainGame game) {

        this.game = game;
    }


    @Override
    public void show() {
        // This method is invoked when a screen is displayed.
    }

    @Override
    public void render(float delta) {
        // This method is invoked when a screen has to be rendered in a frame.
        // delta is the amount of seconds (order of 0.01 or so) between this and last frame.
    }

    @Override
    public void resize(int width, int height) {
        // This method is invoked when the game is resized (desktop).
    }

    @Override
    public void pause() {
        // This method is invoked when the game is paused.
    }

    @Override
    public void resume() {
        // This method is invoked when the game is resumed.
    }

    @Override
    public void hide() {
        // This method is invoked when the screen is no more displayed.
    }

    @Override
    public void dispose() {
        // This method is invoked when the game closes.
    }


    public abstract void disableAll(boolean enableDisable);
    public abstract void actions(int actionIndex);

    public MainGame getGame() {
        return game;
    }


}

