package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Constants;
import com.mygdx.game.SoundFactory;

public class AnimalsBuilding extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private SoundFactory sounds;

    public AnimalsBuilding(World world, Texture texture, Vector2 position, SoundFactory sounds){
        this.world = world;
        this.texture = texture;
        this.sounds = sounds;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2.5f, 2.5f);
        fixture = body.createFixture(shape, 5);
        fixture.setUserData("animalsBuilding");
        shape.dispose();

        setSize(5*Constants.PIXELS_IN_METER, 5*Constants.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 2.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 2.5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
