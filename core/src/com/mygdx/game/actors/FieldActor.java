package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.TipoRecurso;
import com.mygdx.game.factories.SoundFactory;

import java.util.List;

public class FieldActor extends BaseActor {

    private Texture texture;
    private World world;
    private Body body;
    private SoundFactory sounds;
    private List<Texture> textures;
    private Campo field;

    public FieldActor(World world, List<Texture> textures, Vector2 position, SoundFactory sounds, Campo field){
        this.world = world;
        this.textures = textures;
        this.sounds = sounds;
        this.texture = textures.get(0);
        this.field = field;



        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        setSize(17*Constants.PIXELS_IN_METER, 10*Constants.PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 8.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        int textureIndex = field.getStage();
        if (field.getPlantedResourceType() != null) {
            if (field.getPlantedResourceType() == TipoRecurso.POTATO) textureIndex += 8;
            else if (field.getPlantedResourceType() == TipoRecurso.STRAWBERRY) textureIndex += 4;

        }
        texture = textures.get(textureIndex);
        //texture = textures.get(field.getStage());

    }


    public void detach() {
        world.destroyBody(body);
    }


    //SETTER & GETTER
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public SoundFactory getSounds() {
        return sounds;
    }

    public void setSounds(SoundFactory sounds) {
        this.sounds = sounds;
    }


}
