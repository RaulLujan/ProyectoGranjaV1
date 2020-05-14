package com.mygdx.game.Images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Constants;

public class ImageRepeart extends Image {

    public ImageRepeart(Texture texture, int x, int y, int width, int height){

        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion imgTextureRegion = new TextureRegion(texture);
        imgTextureRegion.setRegion(0,0,texture.getWidth()*width,texture.getHeight()*height);

        TextureRegionDrawable imgTextureRegionDrawable = new TextureRegionDrawable(imgTextureRegion);
        setDrawable(imgTextureRegionDrawable);
        setSize(Constants.PIXELS_IN_METER *width,Constants.PIXELS_IN_METER*height );
        setPosition(x * Constants.PIXELS_IN_METER, y*Constants.PIXELS_IN_METER);
    }
}
