package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashMap;





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


/*
    Timetable of Work done on this Project:
    This isnt always accurate as sometiemes its just minutes which is hard to record
    Goal for this Project is 60 H +
    
    Started 22 Feb First Week 
        - Getting Used to LibGDX and set everything up
        - Making my first Scene class and starting the structure of the Game
        - Creating Singeltons, Static and abstract classes, Globals

    Second Week 29 Febuary 
        - First Logic for BlackJack
        - Continue Working on BlackJack (User Interface and controlls);
        - Creating Buttons
        - Looking at API and Connection
    
    Third Week 7 March
        - Completed the API connection and created the sturcture for that
        - Completion of all the classes needed
        - Figuring out how to seperate Logic from display for blackjack but still making it connected

    Fourth Week 14 March 
        - Trying to display Blackjack and make it appear on screen
        - Figuring our problems with logic that are occuring in blackjack
    
    Fifth Week 21 March - Spring Break
    
    Sixth Week 28 March - First Check IN

         - Probelms with changings of Scenes and connection all Gamestates
         - Continueing on Blackjack

    Seventh Week 4 April 

        - Loading Images from API and really starting to get Blackjack to work
        - Figuring how to sepearte assets from logic...
        - Working on Bugs
        
    Eigth Week 11 April

        - Creating Visual Homescreen and working on Visuals making it look neat
        - 

*/

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
    private HomeScene homeScreenScene;
    private RouletteScene rouletteScene;
    private Stage globalStage;
    
    private HashMap<GameStates,Scene> list = new HashMap<GameStates,Scene>();
    
    private float deltaTime;
    
    @Override
    public void create () {

            globals = Globals.getOrMakeInstance();
            
            homeScreenScene = HomeScene.getOrMakeInstance();
            blackJackScene = BlackJackScene.getOrMakeInstance();
            rouletteScene = RouletteScene.getOrMakeInstance();
            
            list.put(GameStates.ROULETTE, rouletteScene);
            list.put(GameStates.BLACKJACK, blackJackScene);
            list.put(GameStates.MENU, homeScreenScene);
    }


    @Override
    public void render() {
            System.out.println("Running");
            ScreenUtils.clear(1, 0, 0, 1);    
            list.get(globals.getGameState()).draw(Gdx.graphics.getDeltaTime());
            System.out.println("Running Over");
    }

    @Override
    public void dispose() {
       System.out.println("Running Over Fully");
       blackJackScene.dispose();
       homeScreenScene.dispose();
       
        
    }
}
