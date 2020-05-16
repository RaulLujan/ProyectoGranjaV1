package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class SoundFactory {

    private Sound road, step;
    private Sound[] RNDAmbientSounds;

    public SoundFactory(MainGame game) {

        road = game.getAssetManager().get("Sounds/roadnoise.wav");
        step = game.getAssetManager().get("Sounds/sandstep2.wav");

        RNDAmbientSounds = new Sound[3];
        RNDAmbientSounds[0] = game.getAssetManager().get("Sounds/RNDAmbient/crow.wav");
        RNDAmbientSounds[1] = game.getAssetManager().get("Sounds/RNDAmbient/Duck.wav");
        RNDAmbientSounds[2] = game.getAssetManager().get("Sounds/RNDAmbient/parrot.wav");


    }

    public void playRDNSound(){
        int i = (int) (Math.random() * RNDAmbientSounds.length);
        RNDAmbientSounds[i].play();
    }


    public void playRoadSound() {
       road.play();
       road.loop();
    }
    public void stopRoadSound()        { road.stop();    }

    public void playStepSound()        {
        step.play(0.1f);
        step.loop();
    }
    public void stopStepSound()        { step.pause();    }




}
