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
    private float fullAnimationTIme, changing, change;
    private int cicles;
    private boolean onlyForward;




    public PulseActor(World world, List<Texture> textures, Vector2 position, boolean onlyForward) {

        this.world = world;
        this.textures = textures;
        this.onlyForward = onlyForward;
        fullAnimationTIme = 2f;
        cicles = 180;

        change = 0.2f;
        changing = (float) (Math.random() * change * cicles);



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.75f, 0.75f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("tractor");
        shape.dispose();

        setSize(1.5f * Constants.PIXELS_IN_METER, 1.5f * Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {

        //control de la animación en función de delta

        changing = changing + delta;

        if (!onlyForward) {
            if (changing < change) {
                this.setVisible(true);
                texture = textures.get(0);
            } else if (changing < change * 2) texture = textures.get(1);
            else if (changing < change * 3) texture = textures.get(2);
            else if (changing < change * 4) texture = textures.get(3);
            else if (changing < change * 5) texture = textures.get(4);
            else if (changing < change * 6) texture = textures.get(5);
            else if (changing < change * 7) texture = textures.get(6);
            else if (changing < change * 8) texture = textures.get(7);
            else if (changing < change * 9) texture = textures.get(6);
            else if (changing < change * 10) texture = textures.get(5);
            else if (changing < change * 11) texture = textures.get(4);
            else if (changing < change * 12) texture = textures.get(3);
            else if (changing < change * 13) texture = textures.get(2);
            else if (changing < change * 14) texture = textures.get(1);
            else if (changing < change * 15) texture = textures.get(0);
            else if (changing < change * cicles) this.setVisible(false);


            else changing = 0;
        }else {
            if (changing < change) {
                this.setVisible(true);
                texture = textures.get(0);
            } else if (changing < change * 2) texture = textures.get(1);
            else if (changing < change * 3) texture = textures.get(2);
            else if (changing < change * 4) texture = textures.get(3);
            else if (changing < change * 5) texture = textures.get(4);
            else if (changing < change * 6) texture = textures.get(5);
            else if (changing < change * 8) texture = textures.get(6);
            else if (changing < change * 10) texture = textures.get(7);

            else if (changing < change * 12) texture = textures.get(3);
            else if (changing < change * 13) texture = textures.get(2);
            else if (changing < change * 14) texture = textures.get(1);
            else if (changing < change * 15) texture = textures.get(0);
            else if (changing < change * cicles) this.setVisible(false);
            else changing = 0;
        }


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.75f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.75f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
