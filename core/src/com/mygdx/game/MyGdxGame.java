package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MyGdxGame extends ApplicationAdapter {
    // MAIN CLASS OF GAME
    // This Class controlls the game application itself and it controlls
    // 4 Game States
    // 1 - MAIN MENU
    // 2 - Blackjack
    //
    //
	
	
  
        private Globals globals;
        private GameStates currentState;
        
        
        
        private String card= "";
	
	@Override
	public void create () {
                
                globals = Globals.getOrMakeInstance();
                currentState = globals.getGameState();
                             
                
//                UIButton button2 = new UIButton(mySkin,2);
//                UIButton button3 = new UIButton(mySkin,3);
//                UIButton button4 = new UIButton(mySkin,4);
                
//                stage.addActor(button2.button);
//                stage.addActor(button3.button);
//                stage.addActor(button4.button);
                	
           
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
       
                if(globals.getGameState() == GameStates.MENU){
                    HomeScreen homeScreenScene = HomeScreen.getOrMakeInstance();
                    homeScreenScene.draw();
                }
                else if(globals.getGameState() == GameStates.BLACKJACK){
                    BlackJackScene blackJackScene = BlackJackScene.getOrMakeInstance();
                    blackJackScene.draw();
                }
                       
                      
	}
	
	@Override
	public void dispose () {
		
	}
}
