package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class SoundFactory {

    private Sound road, step;
    private Sound[] RNDAmbientSounds;

    public SoundFactory(MainGame game) {

        road = game.getAssetManager().get("Sounds/roadnoise.wav");
        step = game.getAssetManager().get("Sounds/sandstep2.wav");

        RNDAmbientSounds = new Sound[13];
        RNDAmbientSounds[0] = game.getAssetManager().get("Sounds/RNDAmbient/crow.wav");
        RNDAmbientSounds[1] = game.getAssetManager().get("Sounds/RNDAmbient/Duck.wav");
        RNDAmbientSounds[2] = game.getAssetManager().get("Sounds/RNDAmbient/parrot.wav");
        RNDAmbientSounds[3] = game.getAssetManager().get("Sounds/RNDAmbient/chicken1.wav");
        RNDAmbientSounds[4] = game.getAssetManager().get("Sounds/RNDAmbient/chicken2.wav");
        RNDAmbientSounds[5] = game.getAssetManager().get("Sounds/RNDAmbient/chicken3.wav");
        RNDAmbientSounds[6] = game.getAssetManager().get("Sounds/RNDAmbient/chicken4.wav");
        RNDAmbientSounds[7] = game.getAssetManager().get("Sounds/RNDAmbient/cow.wav");
        RNDAmbientSounds[8] = game.getAssetManager().get("Sounds/RNDAmbient/cow1.wav");
        RNDAmbientSounds[9] = game.getAssetManager().get("Sounds/RNDAmbient/cow2.wav");
        RNDAmbientSounds[10] = game.getAssetManager().get("Sounds/RNDAmbient/pig1.wav");
        RNDAmbientSounds[11] = game.getAssetManager().get("Sounds/RNDAmbient/pig2.wav");
        RNDAmbientSounds[12] = game.getAssetManager().get("Sounds/RNDAmbient/pig3.wav");

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

    public void stopAll(){
        road.stop();
        step.stop();
        for (Sound sound: RNDAmbientSounds) {
            sound.stop();
        }
    }


}
