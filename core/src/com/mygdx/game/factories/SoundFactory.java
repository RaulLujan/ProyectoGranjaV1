package com.mygdx.game.factories;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.MainGame;


public class SoundFactory {

    private Sound road, step;
    private Sound[] RNDAmbientSounds;
    private Music introMusic, gameMusic;
    private float volume;
    private boolean music, effects;


    public SoundFactory(MainGame game, Preferences preferences) {

        road = game.getAssetManager().get("Sounds/roadnoise.wav");
        step = game.getAssetManager().get("Sounds/sandstep2.wav");
        introMusic =  game.getAssetManager().get("Sounds/Music/intro.mp3");
        gameMusic =  game.getAssetManager().get("Sounds/Music/game.mp3");

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

        this.volume = preferences.getFloat(game.VOLUME_KEY, 0.8f);
        this.music = preferences.getBoolean(game.MUSIC_KEY, true);
        this.effects = preferences.getBoolean(game.EFFECTS_KEY, true);
    }

    public void playRDNSound(){
        int i = (int) (Math.random() * RNDAmbientSounds.length);
        if (effects) RNDAmbientSounds[i].play(volume);
    }

    public void playIntroMusic(){
        gameMusic.stop();
        if (music){
            introMusic.setLooping(true);
            introMusic.setVolume(volume);
            introMusic.play();
        }else{
            introMusic.stop();
        }

    }
    public void playGameMusic(){
        introMusic.stop();
        if (music){
            gameMusic.setLooping(true);
            gameMusic.setVolume(volume);
            gameMusic.play();
        }else{
            gameMusic.stop();
        }

    }

    public void stopGameMusic(){ gameMusic.stop();}
    public void stopIntroMusic(){ introMusic.stop();}

    public void playRoadSound() {
        if (effects)road.loop(volume);
    }
    public void stopRoadSound()        { road.stop();    }

    public void playStepSound()        {
        if (effects) step.loop(volume);
    }
    public void stopStepSound()        { step.stop();    }

    public void stopAll(){
        road.stop();
        step.stop();
        for (Sound sound: RNDAmbientSounds) {
            sound.stop();
        }
    }





    // GETTERS & SEETERS

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isEffects() {
        return effects;
    }

    public void setEffects(boolean effects) {
        this.effects = effects;
    }
}
