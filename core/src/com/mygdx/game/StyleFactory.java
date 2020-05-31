package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;



public class StyleFactory {



    public final static Color PRESSED_COLOR = new Color(0.07450981f,0.24705882f,0.21960784f,1);
    public final static Color BLUE_COLOR = new Color(0.30314961f,0.65354331f,1,1);
    public final static Color DARK_BLUE_COLOR = new Color(0,0.30314961f,0.60236220f,1);
    public static final Color ORANGE_COLOR = new Color(1,0.60236220f,0.2f,1);
    public static final Color DARK_ORANGE_COLOR = new Color(0.70472441f,0.35039370f,0,1);
    public static final Color DARK_GREY_BLUE_COLOR = new Color(0,0.8f,0.8f,0.9f);
    public static final Color GREY_BLUE_COLOR = new Color(0,0.6f,0.6f,0.9f);
    public static final Color RED_COLOR = new Color(0.6f,0,0,1);
    public static final Color DARK_RED_COLOR = new Color(0.4f,0,0,1);
    public static final Color WHITE_COLOR = new Color(1,1,1,1);


   public static final Skin skin = new Skin(Gdx.files.internal("skins.glassy/glassy-ui.json"));


    public static WindowStyle getStyle(Color color) {
        SpriteDrawable bgDrawble= new SpriteDrawable(new Sprite(createTexture(color)));
        WindowStyle windowStyle = new WindowStyle(new BitmapFont(), Color.GREEN, bgDrawble);
        return windowStyle;
    }

    public static TextButtonStyle getStyle(Color colorUp, Color colorDown) {
        SpriteDrawable bgDrawbleUp = new SpriteDrawable(new Sprite(createTexture(colorUp)));
        SpriteDrawable bgDrawbleDown = new SpriteDrawable(new Sprite(createTexture(colorDown)));
        TextButtonStyle textButtonStyle = new TextButtonStyle(bgDrawbleUp, bgDrawbleDown, bgDrawbleUp, skin.getFont("font"));
        return textButtonStyle;
    }

    private static Texture createTexture(Color col) {
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        Color color = col;
        pixmap.setColor(color.r, color.g, color.b, 1);
        pixmap.fillRectangle(0, 0, 100, 100);

        Texture pixmaptexture = new Texture(pixmap);
        return pixmaptexture;
    }


}
