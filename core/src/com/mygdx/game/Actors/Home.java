package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.SoundFactory;


public class Home extends BaseActor {

    private Texture texture;
    private World world;
    private Body body;
    private SoundFactory sounds;

    public Home(World world, Texture texture, Vector2 position, SoundFactory sounds){
        this.world = world;
        this.texture = texture;
        this.sounds = sounds;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        setSize(5* Constants.PIXELS_IN_METER, 5*Constants.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 2.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 2.5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    public void detach() {
        world.destroyBody(body);
    }


}
