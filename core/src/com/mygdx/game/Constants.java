package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Constants {

    public static final float DEVICE_WIDTH = Gdx.graphics.getWidth();

    public static final float DEVICE_HEIGHT = Gdx.graphics.getHeight();

    public static final float FONT_SIZE = DEVICE_HEIGHT*0.003f;

    //change this to get a diferent in-game scale
    public static final float PIXELS_IN_METER = (DEVICE_WIDTH / 30) * 0.94f;

    public static final float FARMER_VELOCITY = 0.7f;

    public static final float TRUCK_VELOCITY = 3.5f;




}
