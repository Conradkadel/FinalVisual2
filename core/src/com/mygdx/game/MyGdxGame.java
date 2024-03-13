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
	private SpriteBatch batch;
	private Texture img;
        private BitmapFont font;
        private Globals globals;
        private GameStates currentState;
        private Stage stage;
        private Skin mySkin;
        
        private String card= "";
	
	@Override
	public void create () {
                stage = new Stage(new ScreenViewport());
                globals = Globals.getOrMakeInstance();
                currentState = globals.getGameState();
                Gdx.input.setInputProcessor(stage);
                mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
              
                UIButton button1 = new UIButton(mySkin,1);
//                UIButton button2 = new UIButton(mySkin,2);
//                UIButton button3 = new UIButton(mySkin,3);
//                UIButton button4 = new UIButton(mySkin,4);
                stage.addActor(button1.button);
//                stage.addActor(button2.button);
//                stage.addActor(button3.button);
//                stage.addActor(button4.button);
                
           
		batch = new SpriteBatch();
                Pixmap imgSmall = new Pixmap(Gdx.files.internal("casino.png"));
                Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
                imgBig.drawPixmap(imgSmall,
                        0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                        0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		img = new Texture(imgBig);
                
                font = new BitmapFont();
                font.setColor(Color.RED);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
       
                if(globals.getGameState() == GameStates.MENU){
                    stage.act();
                    batch.begin();
                    
                    batch.draw(img, 0, 0);
                    font.draw(batch, card, 200, 200);
                    batch.end();
                    stage.draw();
                }
                else if(globals.getGameState() == GameStates.BLACKJACK){
                    BlackJackScene scene = BlackJackScene.getOrMakeInstance();
                    scene.draw();
                }
                       
                      
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
