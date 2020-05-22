package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Screens.AnimalsScreen;
import com.mygdx.game.Screens.FieldScreen;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.LoadingScreen;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PreLoadingScreen;
import com.mygdx.game.Screens.ShopScreen;
import com.mygdx.game.Screens.StorageScreen;

public class MainGame extends Game {

	private Preferences preferences;
	private static final String PREFERENCES = "FarmPreferences";
	private static final String HIGH_SCORE_KEY = "TopScore";

	private SoundFactory soundFactory;
	private AssetManager assetManager;

	private Screen gameScreen, loadingScreen, preloadingScreen, menuScreen, loginScreen, fieldScreen,
		shopScreen, storageScreen, animalsScreen;


	
	@Override
	public void create () {

		this.gameScreen = new GameScreen(this);
		this.loadingScreen = new LoadingScreen(this);
		this.preloadingScreen = new PreLoadingScreen(this);
		this.menuScreen = new MenuScreen(this);
		this.loginScreen = new LoadingScreen(this);
		this.fieldScreen = new FieldScreen(this);
		this.shopScreen =  new ShopScreen(this);
		this.storageScreen = new StorageScreen(this);
		this.animalsScreen = new AnimalsScreen(this);

		assetManager = new AssetManager();

		//texture for loading
		assetManager.load("Textures/tractor.png", Texture.class);


		//textures
		assetManager.load("Textures/Crop.png", Texture.class);
		assetManager.load("Textures/Truck1.png", Texture.class);
		assetManager.load("Textures/Truck2.png", Texture.class);



		assetManager.load("Textures/Buildings/Barn.png", Texture.class);
		assetManager.load("Textures/Buildings/ChickenCoop.png", Texture.class);
		assetManager.load("Textures/Buildings/House.png", Texture.class);
		assetManager.load("Textures/Buildings/Pigsty.png", Texture.class);
		assetManager.load("Textures/Buildings/Shop.png", Texture.class);
		assetManager.load("Textures/Buildings/Store.png", Texture.class);
        assetManager.load("Textures/Bush.png", Texture.class);
        assetManager.load("Textures/Ground.png", Texture.class);
        assetManager.load("Textures/Tree1.png", Texture.class);
        assetManager.load("Textures/Tree2.png", Texture.class);
        assetManager.load("Textures/Road.png", Texture.class);

        for (int i = 0; i < 21; i++) {
            String farmer = String.format("Textures/Farmer/Farmer%d.png", i);
            assetManager.load(farmer, Texture.class);
			String dog = String.format("Textures/Dog/Dog%d.png", i);
			assetManager.load(dog, Texture.class);
        }
	/*
		for (int i = 0; i < 7; i++) {
			String chicken = String.format("Textures/Chiken/Chiken%d.png", i);
			assetManager.load(chicken, Texture.class);
			String pig = String.format("Textures/Pig/Pig%d.png", i);
			//assetManager.load(pig, Texture.class);
			String cow = String.format("Textures/Cow/Cow%d.png", i);
			//assetManager.load(cow, Texture.class);
		}*/

        //Sounds
		assetManager.load("Sounds/roadnoise.wav", Sound.class);
		assetManager.load("Sounds/sandstep2.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/crow.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/Duck.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/parrot.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/chicken1.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/chicken2.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/chicken3.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/chicken4.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/cow.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/cow1.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/cow2.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/pig1.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/pig2.wav", Sound.class);
		assetManager.load("Sounds/RNDAmbient/pig3.wav", Sound.class);


		assetManager.finishLoading();

		this.soundFactory = new SoundFactory(this);
		this.setScreen(gameScreen);
	}





	// Guardado de preferencias
	public void savePreferences(int scoreToave){
		preferences = Gdx.app.getPreferences(PREFERENCES);

		//se añaden las preferencias
		preferences.putInteger(HIGH_SCORE_KEY, scoreToave);
		preferences.flush();

	}


	public void goBackMenuScreen(){
		this.setScreen(gameScreen);
	}


	//Obtiene las preferencias
	public Preferences getPreferences(){
		preferences = Gdx.app.getPreferences(PREFERENCES);


		return preferences;
	}

	public Screen getGameScreen() {
		return gameScreen;
	}

	public Screen getLoadingScreen() {
		return loadingScreen;
	}

	public Screen getPreloadingScreen() {
		return preloadingScreen;
	}

	public Screen getMenuScreen() {
		return menuScreen;
	}

	public Screen getLoginScreen() {
		return loginScreen;
	}

	public Screen getFieldScreen() {
		return fieldScreen;
	}

	public Screen getShopScreen() {
		return shopScreen;
	}

	public Screen getStorageScreen() {
		return storageScreen;
	}

	public Screen getAnimalsScreen() {
		return animalsScreen;
	}

	public SoundFactory getSoundFactory() {
		return soundFactory;
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}
}
