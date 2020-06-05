package com.mygdx.game.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Constants;

public class ImageVerticalRepeat extends Image {

    public ImageVerticalRepeat(Texture texture, float x, float y, float width, int height, float scale){

        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion imgTextureRegion = new TextureRegion(texture);
        imgTextureRegion.setRegion(0,0,texture.getWidth(),texture.getHeight()*(height/2));

        TextureRegionDrawable imgTextureRegionDrawable = new TextureRegionDrawable(imgTextureRegion);
        setDrawable(imgTextureRegionDrawable);
        setSize(scale*Constants.PIXELS_IN_METER *width,scale*Constants.PIXELS_IN_METER*height );
        setPosition(x * Constants.PIXELS_IN_METER, y*Constants.PIXELS_IN_METER);
    }
}
