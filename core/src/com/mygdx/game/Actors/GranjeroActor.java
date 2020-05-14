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

import java.util.List;

public class GranjeroActor extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private enum Estate { STOPED, GOING_UP, GOING_DOWN, GOING_LEFT, GOING_RIGHT, ON_KEES};
    private  Estate estate;
    private final float MAX_X = 16.5f;
    private final float MAX_Y = 12;
    private float timeInState, timeToBeat, animationTime;


    private List<Texture> textures;
    private SoundFactory sounds;

    public GranjeroActor(World world, List<Texture> textures, Vector2 position, SoundFactory sounds) {

        this.world = world;
        this.textures = textures;
        this.sounds = sounds;
        this.estate = Estate.STOPED;
        this.timeInState = 0;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.6f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("granjero");
        shape.dispose();

        setSize(Constants.PIXELS_IN_METER, 1.2f*Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {
        this.timeInState += delta;
        this.animationTime += delta;

        if (this.estate == Estate.STOPED){
            //stopped animation
            if     (this.animationTime < 0.3f) { this.texture = this.textures.get(19);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(18);}
            else                               { this.timeInState = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }

        }else if (this.estate == Estate.GOING_UP){
            this.body.setLinearVelocity(0, 1f);
            //going up animation
            if     (this.animationTime < 0.2f) { this.texture = this.textures.get(8);}
            else if(this.animationTime < 0.4f) { this.texture = this.textures.get(9);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(10);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(11);}

            else                               { this.timeInState = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }
        }else if (this.estate == Estate.GOING_LEFT){
            this.body.setLinearVelocity(-1, 0f);
        }else if (this.estate == Estate.GOING_DOWN){
            this.body.setLinearVelocity(0, -1f);
        }else if (this.estate == Estate.GOING_RIGHT){
            this.body.setLinearVelocity(0, -1f);
        }else if (this.estate == Estate.ON_KEES){


        }


    }

    private Estate getNewState() {
        Estate newEstate;
        do{
            int newIndex = (int) (Math.random() * 6) + 1;
            if (newIndex == 1 ){
                newEstate = Estate.STOPED;
            }else if (newIndex == 2 ){
                newEstate = Estate.GOING_UP;
            }else if (newIndex == 3 ){
                newEstate = Estate.GOING_LEFT;
            }else if (newIndex == 4 ){
                newEstate = Estate.GOING_DOWN;
            }else if (newIndex == 5 ){
                newEstate = Estate.GOING_RIGHT;
            }else{
                newEstate = Estate.ON_KEES;
            }
        }while (this.estate != newEstate);
        return newEstate;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.6f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
