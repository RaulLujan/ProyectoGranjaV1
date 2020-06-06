package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;

import java.util.List;


public class PulseActor extends BaseActor {

    private Texture texture;
    private List<Texture> textures;
    private World world;
    private Body body;
    private Fixture fixture;
    private float fullAnimationTIme, changing;



    public PulseActor(World world, List<Texture> textures, Vector2 position) {

        this.world = world;
        this.textures = textures;
        fullAnimationTIme = 2f;
        changing = 0f;


        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("tractor");
        shape.dispose();

        setSize(2 * Constants.PIXELS_IN_METER, 2 * Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {

        //control de la animación en función de delta

        changing = changing + delta;

        if ( changing < 1)      texture = textures.get(2);
        else if ( changing < 1.15f)  texture = textures.get(1);
        else if ( changing <  1.55f) texture = textures.get(3);

        else changing = 0;

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 1) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 1) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
