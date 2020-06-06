package com.mygdx.game.images;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Constants;

public class ImageClampToEdge extends Image {

    public ImageClampToEdge(Texture texture, float x, float y,float width,float height){
        texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        TextureRegion imgTextureRegion = new TextureRegion(texture);
        imgTextureRegion.setRegion(0,0,texture.getWidth(),texture.getHeight());

        TextureRegionDrawable imgTextureRegionDrawable = new TextureRegionDrawable(imgTextureRegion);
        setDrawable(imgTextureRegionDrawable);
        setSize(Constants.PIXELS_IN_METER*width ,Constants.PIXELS_IN_METER*height );
        setPosition(x * Constants.PIXELS_IN_METER, y * Constants.PIXELS_IN_METER);
    }

}