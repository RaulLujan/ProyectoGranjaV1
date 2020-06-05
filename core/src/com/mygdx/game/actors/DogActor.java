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
import com.mygdx.game.factories.SoundFactory;


import java.util.List;

public class DogActor extends BaseActor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private enum Estate { STOPED, GOING_UP, GOING_DOWN, GOING_LEFT, GOING_RIGHT, SLEEPING}
    private  Estate estate;
    private final float MAX_X = 29.5f;
    private final float MAX_Y = 13;
    private final float MIN_Y = 9.5f;
    private final float MIN_X = 22.75f;
    private float timeInState, timeToBeat, animationTime;


    private List<Texture> textures;
    private SoundFactory sounds;

    public DogActor(World world, List<Texture> textures, Vector2 position, SoundFactory sounds) {

        this.world = world;
        this.textures = textures;
        this.sounds = sounds;
        this.estate = Estate.STOPED;
        this.timeToBeat = 3;
        this.timeInState = 0;
        this.animationTime = 0;


        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setGravityScale(0);



        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.45f, 0.6f);
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("dog");
        shape.dispose();

        setSize(0.9f*Constants.PIXELS_IN_METER, 1.2f*Constants.PIXELS_IN_METER);
    }

    @Override
    public void act(float delta) {
        this.timeInState += delta;
        this.animationTime += delta;

        if (this.estate == Estate.STOPED){
            this.body.setLinearVelocity(0, 0f);
            //stopped animation
            if     (this.animationTime < 0.3f) { this.texture = this.textures.get(18);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(19);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(20);}
            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }

        }else if (this.estate == Estate.GOING_UP){
            if (this.body.getPosition().y < this.MAX_Y){
                this.body.setLinearVelocity(0, Constants.FARMER_VELOCITY *3 );
            }else{
                this.body.setLinearVelocity(0, 0f);
                this.estate = this.getNewState();
            }
            //going up animation
            if     (this.animationTime < 0.2f) { this.texture = this.textures.get(8);}
            else if(this.animationTime < 0.4f) { this.texture = this.textures.get(9);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(10);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(11);}

            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }
        }else if (this.estate == Estate.GOING_LEFT){
            if (this.body.getPosition().x > MIN_X ){
                this.body.setLinearVelocity(-Constants.FARMER_VELOCITY *3, 0f);
            }else{
                this.body.setLinearVelocity(0, 0f);
                this.estate = this.getNewState();
            }

            //going left animation
            if     (this.animationTime < 0.2f) { this.texture = this.textures.get(12);}
            else if(this.animationTime < 0.4f) { this.texture = this.textures.get(13);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(14);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(15);}

            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }
        }else if (this.estate == Estate.GOING_DOWN){
            if (this.body.getPosition().y > MIN_Y ){
                this.body.setLinearVelocity(0, -Constants.FARMER_VELOCITY*3);
            }else{
                this.body.setLinearVelocity(0, 0f);
                this.estate = this.getNewState();
            }

            //going down animation
            if     (this.animationTime < 0.2f) { this.texture = this.textures.get(0);}
            else if(this.animationTime < 0.4f) { this.texture = this.textures.get(1);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(2);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(3);}

            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }
        }else if (this.estate == Estate.GOING_RIGHT){
            if (this.body.getPosition().x < this.MAX_X ){
                this.body.setLinearVelocity(Constants.FARMER_VELOCITY*3, 0f);
            }else{
                this.body.setLinearVelocity(0, 0f);
                this.estate = this.getNewState();
            }

            //going right animation
            if     (this.animationTime < 0.2f) { this.texture = this.textures.get(4);}
            else if(this.animationTime < 0.4f) { this.texture = this.textures.get(5);}
            else if(this.animationTime < 0.6f) { this.texture = this.textures.get(6);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(7);}

            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }
        }else if (this.estate == Estate.SLEEPING){
            this.body.setLinearVelocity(0, 0f);

            //going up animation
            if     (this.animationTime < 0.6f) { this.texture = this.textures.get(16);}
            else if(this.animationTime < 0.8f) { this.texture = this.textures.get(17);}

            else                               { this.animationTime = 0;      }

            if (timeInState > timeToBeat ){
                this.estate = this.getNewState();
                this.timeInState = 0;
            }

        }


    }

    private Estate getNewState() {
        Estate newEstate;
        do{
            int newIndex = (int) (Math.random() * 6 + 1);


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
                newEstate = Estate.SLEEPING;
            }
        }while (this.estate == newEstate);
        this.timeToBeat = (float) (Math.random() * 1.25f) + 0.25f;
        this.timeInState = 0;
        if (newEstate == Estate.STOPED){
            timeToBeat += 10;
        }

        if (newEstate == Estate.SLEEPING){
            timeToBeat += 15;
        }

        return newEstate;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.45f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.6f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
