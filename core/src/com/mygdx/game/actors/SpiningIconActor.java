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


public class SpiningIconActor extends BaseActor {

    private Texture texture;
    private List<Texture> textures;
    private World world;
    private Body body;
    private Fixture fixture;
    private float fullAnimationTIme, changing, cambio;



    public SpiningIconActor(World world, List<Texture> textures, Vector2 position) {

        this.world = world;
        this.textures = textures;
        fullAnimationTIme = 2f;
        changing = 0f;
        cambio = 0.18f;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5f, 5f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("tractor");
        shape.dispose();

        setSize(10f* Constants.PIXELS_IN_METER, 10f*Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {

        //control de la animación en función de delta

        changing = changing + delta;
        if ( changing <cambio * 1)      texture = textures.get(0);
        else if ( changing < cambio * 2)  texture = textures.get(1);
        else if ( changing < cambio * 3) texture = textures.get(2);
        else if ( changing < cambio * 4)  texture = textures.get(3);
        //else if ( changing < cambio * 5) texture = textures.get(4);
        else if ( changing < cambio * 5)  texture = textures.get(5);
        else if ( changing < cambio * 6)  texture = textures.get(6);
        else if ( changing < cambio * 7)  texture = textures.get(7);
        else if ( changing < cambio * 8)  texture = textures.get(8);
        else if ( changing < cambio * 9)  texture = textures.get(9);
        else if ( changing < cambio * 10)  texture = textures.get(10);
        //else if ( changing < cambio * 12)  texture = textures.get(11);
        else if ( changing < cambio * 11)  texture = textures.get(12);
        else if ( changing < cambio * 12)  texture = textures.get(13);


        else changing = 0;


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
