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
import com.mygdx.game.SoundFactory;

public class TruckActor extends BaseActor {
    private Texture texture, texture1, texture2;
    private World world;
    private Body body;
    private Fixture fixture;
    private enum Estate { STOPED, RUNNING}
    private TruckActor.Estate estate;
    private boolean isSoundPlaying;

    private float timeStoped, timeToBeat;


    private SoundFactory sounds;

    public TruckActor(World world, Texture texture1,Texture texture2, Vector2 position, SoundFactory sounds) {

        this.world = world;
        this.texture = texture1;
        this.texture1 = texture1;
        this.texture2 = texture2;
        this.sounds = sounds;
        this.estate = TruckActor.Estate.STOPED;

        this.timeStoped = 0;
        this.timeToBeat = (float) (Math.random() * 5) + 1;
        this.isSoundPlaying = false;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 2f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("truck");
        shape.dispose();

        setSize(2*Constants.PIXELS_IN_METER, 4*Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {
        if (this.estate == Estate.RUNNING) {
            this.body.setLinearVelocity(0, -Constants.TRUCK_VELOCITY);
        }else if (this.estate == Estate.STOPED) {
            this.timeStoped += delta;
            this.body.setLinearVelocity(0, 0);

            if(this.timeStoped > this.timeToBeat){
                this.estate = Estate.RUNNING;
                if (!this.isSoundPlaying){
                    this.isSoundPlaying = true;
                    this.sounds.playRoadSound();
                }
            }
        }
        if(this.body.getPosition().y < -5){
            this.body.setTransform(this.body.getPosition().x, 25, 0);
            if((int)(Math.random() * 2) == 0){
                this.texture = texture1;
            }else{
                this.texture = texture2;
            }

            this.estate = Estate.STOPED;
            this.isSoundPlaying = false;
            this.sounds.stopRoadSound();
            this.timeStoped = 0;
            this.timeToBeat = (float) (Math.random() * 15) + 20;

        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 1) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 2) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
