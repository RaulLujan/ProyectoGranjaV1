package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;


public class TractorActor extends BaseActor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;


    public TractorActor(World world, Texture textures, Vector2 position) {

        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("tractor");
        shape.dispose();

        setSize(1f* Constants.PIXELS_IN_METER, 1f*Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {



    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
