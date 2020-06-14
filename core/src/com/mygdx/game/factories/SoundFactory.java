package com.mygdx.game.factories;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.MainGame;


public class SoundFactory {

    private Sound road, step, deSelect, select, coins, pickUp, complete, select2, select3, dog, car, man;
    private Sound[] RNDAmbientSounds;
    private Music introMusic, gameMusic;
    private float musicVolume, effectsVolume;
    private boolean music, effects;

    public SoundFactory(MainGame game, Preferences preferences) {

        this.road = game.getAssetManager().get("Sounds/roadnoise.wav");
        this.step = game.getAssetManager().get("Sounds/sandstep2.wav");
        this.introMusic =  game.getAssetManager().get("Sounds/Music/intro.mp3");
        this.gameMusic =  game.getAssetManager().get("Sounds/Music/game.mp3");

        this.RNDAmbientSounds = new Sound[13];
        this.RNDAmbientSounds[0] = game.getAssetManager().get("Sounds/RNDAmbient/crow.wav");
        this.RNDAmbientSounds[1] = game.getAssetManager().get("Sounds/RNDAmbient/Duck.wav");
        this.RNDAmbientSounds[2] = game.getAssetManager().get("Sounds/RNDAmbient/parrot.wav");
        this.RNDAmbientSounds[3] = game.getAssetManager().get("Sounds/RNDAmbient/chicken1.wav");
        this.RNDAmbientSounds[4] = game.getAssetManager().get("Sounds/RNDAmbient/chicken2.wav");
        this.RNDAmbientSounds[5] = game.getAssetManager().get("Sounds/RNDAmbient/chicken3.wav");
        this.RNDAmbientSounds[6] = game.getAssetManager().get("Sounds/RNDAmbient/chicken4.wav");
        this.RNDAmbientSounds[7] = game.getAssetManager().get("Sounds/RNDAmbient/cow.wav");
        this.RNDAmbientSounds[8] = game.getAssetManager().get("Sounds/RNDAmbient/cow1.wav");
        this.RNDAmbientSounds[9] = game.getAssetManager().get("Sounds/RNDAmbient/cow2.wav");
        this.RNDAmbientSounds[10] = game.getAssetManager().get("Sounds/RNDAmbient/pig1.wav");
        this.RNDAmbientSounds[11] = game.getAssetManager().get("Sounds/RNDAmbient/pig2.wav");
        this.RNDAmbientSounds[12] = game.getAssetManager().get("Sounds/RNDAmbient/pig3.wav");

        this.select = game.getAssetManager().get("Sounds/UISounds/bigSelect.wav");
        this.deSelect = game.getAssetManager().get("Sounds/UISounds/bigDeSelect.wav");
        this.coins = game.getAssetManager().get("Sounds/UISounds/coins.wav");
        this.pickUp = game.getAssetManager().get("Sounds/UISounds/pickUpItem.wav");
        this.complete = game.getAssetManager().get("Sounds/UISounds/questcomplete.wav");
        this.select2 = game.getAssetManager().get("Sounds/UISounds/select.wav");
        this.select3 = game.getAssetManager().get("Sounds/UISounds/smallSelect.wav");
        this.dog = game.getAssetManager().get("Sounds/dog.wav");
        this.car = game.getAssetManager().get("Sounds/carHorn.mp3");
        this.man = game.getAssetManager().get("Sounds/man.wav");

        this.musicVolume = preferences.getFloat(game.MUSIC_VOLUME_KEY, 0.6f);
        this.effectsVolume = preferences.getFloat(game.EFFECTS_VOLUME_KEY, 0.6f);
        this.music = preferences.getBoolean(game.MUSIC_KEY, true);
        this.effects = preferences.getBoolean(game.EFFECTS_KEY, true);
    }
    public void playMan(){
        if (effects) man.play(effectsVolume * 0.25f);
    }
    public void playCar(){
        if (effects) car.play(effectsVolume);
    }
    public void playSelect(){
        if (effects) select.play(effectsVolume);
    }
    public void playDeSelect(){
        if (effects) deSelect.play(effectsVolume);
    }
    public void playCoins(){
        if (effects) coins.play(effectsVolume);
    }
    public void playPickUp(){
        if (effects) pickUp.play(effectsVolume);
    }
    public void playComplete(){
        if (effects) complete.play(effectsVolume);
    }
    public void playSelect2(){
        if (effects) select2.play(effectsVolume);
    }
    public void playSelect3(){
        if (effects) select3.play(effectsVolume);
    }
    public void playDog(){
        if (effects) dog.play(effectsVolume);
    }

    public void playRDNSound(){
        int i = (int) (Math.random() * RNDAmbientSounds.length);
        if (effects) RNDAmbientSounds[i].play(effectsVolume);
    }

    public void playIntroMusic(){
        gameMusic.stop();
        if (music){
            introMusic.setLooping(true);
            introMusic.setVolume(musicVolume);
            introMusic.play();
        }else{
            introMusic.stop();
        }
    }

    public void playGameMusic(){
        introMusic.stop();
        if (music){
            gameMusic.setLooping(true);
            gameMusic.setVolume(musicVolume);
            gameMusic.play();
        }else{
            gameMusic.stop();
        }
    }

    public void stopGameMusic(){ gameMusic.stop();}
    public void stopIntroMusic(){ introMusic.stop();}
    public void playRoadSound() {
        if (effects)road.loop(effectsVolume);
    }
    public void stopRoadSound()        { road.stop();    }
    public void playStepSound()        {
        if (effects) step.loop(effectsVolume);
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
    public float getMusicVolume() {
        return musicVolume;
    }
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
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
    public float getEffectsVolume() {
        return effectsVolume;
    }
    public void setEffectsVolume(float effectsVolume) {
        this.effectsVolume = effectsVolume;
    }
}