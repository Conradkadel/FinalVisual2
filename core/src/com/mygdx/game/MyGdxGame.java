package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



// CASINO GAME
// My goal with this project is to create a well working "Gambling" Application with the ability
// to play BlackJack, Roulette or Baccarat
// Using LibGDX for Graphics
////// Time Table /////
// -- Started 22 Febuary 
// -- Getting Used to LibGDX and set everything up
// -- Making my first Scene class and starting the structure of the Game
// -- Creating Singeltons, Static and abstract classes, Globals
// -- First Logic for BlackJack
// -- Continue Working on BlackJack (User Interface and controlls

public class MyGdxGame extends ApplicationAdapter {
    // MAIN CLASS OF GAME
    // This Class controlls the game application itself and it controlls
    // 4 Game States
    // 1 - MAIN MENU
    // 2 - Blackjack
    //
    //
	
    private Globals globals;
  
    private BlackJackScene blackJackScene;
    private HomeScreen homeScreenScene;
    
    @Override
    public void create () {

            globals = Globals.getOrMakeInstance();
                   	

    }

    @Override
    public void render () {
            ScreenUtils.clear(1, 0, 0, 1);
          
            if(globals.getGameState() == GameStates.MENU){
                homeScreenScene = HomeScreen.getOrMakeInstance();
                homeScreenScene.draw();
            }
            else if(globals.getGameState() == GameStates.BLACKJACK){
                blackJackScene = BlackJackScene.getOrMakeInstance();
                blackJackScene.draw();
            }


    }

    @Override
    public void dispose () {
       blackJackScene.dispose();
       homeScreenScene.dispose();
        
    }
}
