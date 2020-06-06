package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.Infraestructura;
import com.mygdx.game.dominio.Usuario;
import com.mygdx.game.screens.AnimalsScreen;
import com.mygdx.game.screens.FieldScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.LoadingScreen;
import com.mygdx.game.screens.LoginScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.OptionsScreen;
import com.mygdx.game.screens.PreLoadingScreen;
import com.mygdx.game.screens.ShopScreen;
import com.mygdx.game.screens.StorageScreen;
import com.mygdx.game.control.FieldController;
import com.mygdx.game.control.UserController;
import com.mygdx.game.factories.SoundFactory;

public class MainGame extends Game {

	private Preferences preferences;
	public final String PREFERENCES = "FarmerDayPreferences";
    public final String LOGIN_KEY = "loginKey";
    public final String PASS_KEY = "passKey";
    public final String MUSIC_KEY = "musicKey";
    public final String EFFECTS_KEY = "effectsKey";
    public final String MUSIC_VOLUME_KEY = "musicVolumeKey";
    public final String EFFECTS_VOLUME_KEY = "effectsVolumeKey";
    private boolean userLogged, loginFailed;

	private SoundFactory soundFactory;
	private AssetManager miniAssetManager, assetManager;

	private Screen gameScreen, loadingScreen, preloadingScreen, menuScreen, loginScreen, fieldScreen,
		shopScreen, storageScreen, animalsScreen, optionScreen;

	private Usuario usuario;
	private UserController userController;
	private FieldController fieldController;



	
	@Override
	public void create () {

        usuario = DomainMocker.getMockedUser();
        userController = new UserController(usuario);

		miniAssetManager = new AssetManager();
		assetManager = new AssetManager();
		loginFailed = false;

		//texture for loading

		miniAssetManager.load("Textures/tractor.png", Texture.class);
		for (int i = 0; i < 16; i++) {
			String tractor = String.format("Textures/tractor/Tractor%d.png", i);
			miniAssetManager.load(tractor, Texture.class);
		}

		//textures
		assetManager.load("Textures/field/empty.png", Texture.class);
		assetManager.load("Textures/field/Corn1.png", Texture.class);
		assetManager.load("Textures/field/Corn2.png", Texture.class);
		assetManager.load("Textures/field/Corn3.png", Texture.class);
		assetManager.load("Textures/field/Corn4.png", Texture.class);
		assetManager.load("Textures/field/Strawberry1.png", Texture.class);
		assetManager.load("Textures/field/Strawberry2.png", Texture.class);
		assetManager.load("Textures/field/Strawberry3.png", Texture.class);
		assetManager.load("Textures/field/Strawberry4.png", Texture.class);
		assetManager.load("Textures/Truck1.png", Texture.class);
		assetManager.load("Textures/Truck2.png", Texture.class);
		assetManager.load("Textures/Mailbox.png", Texture.class);
		assetManager.load("Textures/bike.png", Texture.class);
		assetManager.load("Textures/Tree3.png", Texture.class);
		assetManager.load("Textures/drinker.png", Texture.class);
		assetManager.load("Textures/flower1.png", Texture.class);
		assetManager.load("Textures/flower2.png", Texture.class);
		assetManager.load("Textures/flower3.png", Texture.class);
		assetManager.load("Textures/flowerpot1.png", Texture.class);
		assetManager.load("Textures/rock1.png", Texture.class);
		assetManager.load("Textures/rock2.png", Texture.class);
		assetManager.load("Textures/stone1.png", Texture.class);
		assetManager.load("Textures/stone2.png", Texture.class);
		assetManager.load("Textures/Scarecrow.png", Texture.class);
		assetManager.load("Textures/rotengrass1.png", Texture.class);
		assetManager.load("Textures/rotengrass2.png", Texture.class);
		assetManager.load("Textures/rotengrass3.png", Texture.class);

		assetManager.load("Textures/BackGrounds/menuBack.png", Texture.class);
		assetManager.load("Textures/BackGrounds/animalBack.jpg", Texture.class);
		assetManager.load("Textures/BackGrounds/fieldBack.jpg", Texture.class);
		assetManager.load("Textures/BackGrounds/loginBack.jpg", Texture.class);
		assetManager.load("Textures/BackGrounds/optionBack.jpg", Texture.class);
		assetManager.load("Textures/BackGrounds/shopBack.jpg", Texture.class);
		assetManager.load("Textures/BackGrounds/storageBack.jpg", Texture.class);

		assetManager.load("Textures/Buildings/Barn.png", Texture.class);
		assetManager.load("Textures/Buildings/ChickenCoop.png", Texture.class);
		assetManager.load("Textures/Buildings/House.png", Texture.class);
		assetManager.load("Textures/Buildings/Pigsty.png", Texture.class);
		assetManager.load("Textures/Buildings/Shop.png", Texture.class);
		assetManager.load("Textures/Buildings/Store.png", Texture.class);
        assetManager.load("Textures/Bush.png", Texture.class);
        assetManager.load("Textures/bush2.png", Texture.class);
        assetManager.load("Textures/Ground.png", Texture.class);
        assetManager.load("Textures/Tree1.png", Texture.class);
        assetManager.load("Textures/Tree2.png", Texture.class);
        assetManager.load("Textures/Road.png", Texture.class);
        assetManager.load("Textures/WoodPath.png", Texture.class);
        assetManager.load("Textures/WoodPath2.png", Texture.class);
        assetManager.load("Textures/WoodPath3.png", Texture.class);

        for (int i = 0; i < 21; i++) {
            String farmer = String.format("Textures/Farmer/Farmer%d.png", i);
            assetManager.load(farmer, Texture.class);
			String dog = String.format("Textures/Dog/Dog%d.png", i);
			assetManager.load(dog, Texture.class);
        }
		for (int i = 0; i < 4; i++) {
			String chicken = String.format("Textures/Chicken/Chiken%d.png", i);
			assetManager.load(chicken, Texture.class);
		}


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

		//Music
		assetManager.load("Sounds/Music/game.mp3", Music.class);
		assetManager.load("Sounds/Music/intro.mp3", Music.class);

		this.preloadingScreen = new PreLoadingScreen(this);
		this.setScreen(preloadingScreen);




	}

	public void finishLoading() {
		this.actualizePreferences();
		this.soundFactory = new SoundFactory(this, Gdx.app.getPreferences(PREFERENCES));


		if ( validate(preferences.getString(LOGIN_KEY, ""), preferences.getString(PASS_KEY,"")) ){
			this.userLogged = true;
		}else{
			this.userLogged = false;
		}
		this.fieldController = new FieldController((Campo)usuario.getGranja().getInfraestructuras().get(Infraestructura.FIELD));
	}


	public void showAnimalsScreen(int animalType){
		this.animalsScreen = new AnimalsScreen(this, animalType);
		this.setScreen(animalsScreen);
	}

	public void showFieldScreen(){
		this.fieldScreen = new FieldScreen(this);
		this.setScreen(fieldScreen);
	}
	public void showMenuScreen(){
		this.menuScreen = new MenuScreen(this);
		this.setScreen(menuScreen);
	}
	public void showLoginScreen(){
		this.loginScreen = new LoginScreen(this);
		this.setScreen(loginScreen);
	}
	public void showOptionsScreen(){
		this.optionScreen = new OptionsScreen(this);
		this.setScreen(optionScreen);
	}
	public void showStorageScreen(){
		this.storageScreen = new StorageScreen(this);
		this.setScreen(storageScreen);
	}
	public void showShopScreen(){
		this.shopScreen = new ShopScreen(this);
		this.setScreen(shopScreen);
	}

	public void showGameScreen(){
		if (this.userLogged) {
			this.gameScreen = new GameScreen(this);
			this.setScreen(gameScreen);
		}else{
			showLoginScreen();
		}
	}
	public void showLoadingScreen() {
		this.loadingScreen = new LoadingScreen(this);
		this.setScreen(loadingScreen);
	}

    // Guardado de preferencias
    public void saveUserPreferences(String login, String pass) {
        preferences = Gdx.app.getPreferences(PREFERENCES);
        if (login != null) preferences.putString(LOGIN_KEY, login);
        if (pass != null) preferences.putString(PASS_KEY, pass);
		preferences.flush();
    }

    public void saveSoundPreferences(){
        preferences = Gdx.app.getPreferences(PREFERENCES);
        preferences.putBoolean(MUSIC_KEY, this.soundFactory.isMusic());
        preferences.putBoolean(EFFECTS_KEY, this.soundFactory.isEffects());
        preferences.putFloat(MUSIC_VOLUME_KEY, this.soundFactory.getMusicVolume());
        preferences.putFloat(EFFECTS_VOLUME_KEY, this.soundFactory.getEffectsVolume());

        preferences.flush();

    }

	//Obtiene las preferencias
	public void actualizePreferences(){
		preferences = Gdx.app.getPreferences(PREFERENCES);
	}


	public boolean validate (String login, String pass){

	    return (login.equals(this.usuario.getLogin()) && pass.equals(this.usuario.getPass()));

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

	public Screen getOptionScreen() {
		return optionScreen;
	}

	public boolean isUserLogged() {
		return userLogged;
	}

	public void setUserLogged(boolean userLogged) {
		this.userLogged = userLogged;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public AssetManager getMiniAssetManager() {
		return miniAssetManager;
	}

	public void setMiniAssetManager(AssetManager miniAssetManager) {
		this.miniAssetManager = miniAssetManager;
	}

	public FieldController getFieldController() {
		return fieldController;
	}

	public void setFieldController(FieldController fieldController) {
		this.fieldController = fieldController;
	}

	public boolean isLoginFailed() {
		return loginFailed;
	}

	public void setLoginFailed(boolean loginFailed) {
		this.loginFailed = loginFailed;
	}
}
