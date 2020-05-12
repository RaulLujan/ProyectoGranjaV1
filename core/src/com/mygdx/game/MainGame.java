package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.LoadingScreen;
import com.mygdx.game.Screens.PreLoadingScreen;

public class MainGame extends Game {

	private Preferences preferences;
	private static final String PREFERENCES = "FarmPreferences";
	private static final String HIGH_SCORE_KEY = "TopScore";

	private SoundFactory soundFactory;

	private Screen gameScreen, loadingScreen, preloadingScreen;


	
	@Override
	public void create () {
		this.soundFactory = new SoundFactory(this);
		this.gameScreen = new GameScreen(this);
		this.loadingScreen = new LoadingScreen(this);
		this.preloadingScreen = new PreLoadingScreen(this);





		this.setScreen(gameScreen);
	}





	// Guardado de preferencias
	public void savePreferences(int scoreToave){
		preferences = Gdx.app.getPreferences(PREFERENCES);

		//se añaden las preferencias
		preferences.putInteger(HIGH_SCORE_KEY, scoreToave);
		preferences.flush();

	}

	//Obtiene las preferencias
	public Preferences getPreferences(){
		preferences = Gdx.app.getPreferences(PREFERENCES);


		return preferences;
	}
}
