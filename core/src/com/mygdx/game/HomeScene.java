/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 *
 * @author conradkadel
 */
public class HomeScene extends Scene{
    // The HomeScene Class which is extended from the abstact class Scene
    // THis Class will be used to display the home screen of the application
    // Also it will handle user input and event listerners
    // 4 Buttons that lead to other Games
    private Stage stage;
    private Batch batch;
    private Texture img;
    private BitmapFont font;
    private Skin mySkin;
    public static HomeScene scene;
    
    private HomeScene(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        font = new BitmapFont();
        font.setColor(Color.GOLD);
        
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        
        labelStyle.fontColor = Color.BLACK;
        
        

        
        Skin mySkin2 = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        UIButton button2 = new UIButton(mySkin,850,450,GameStates.BLACKJACK,"BlackJackText.png");
        button2.setColor(Color.ORANGE);
        button2.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(GameStates.BLACKJACK);
           
            return true;
        };
        }); 
        
        UIButton button1 = new UIButton(mySkin2,200,450,GameStates.BLACKJACK,"Roulette.png");
        button1.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(GameStates.ROULETTE);
           
            return true;
        };
        }); 

        Pixmap imgSmall = new Pixmap(Gdx.files.internal("casinoBackround.png"));
      
        img = new Texture(imgSmall);
        
    
        stage.addActor(button1);
        stage.addActor(button2);

        
    }
   
    @Override
    public void update(float deltaTime){
        
    }
  
    @Override
    public void draw(float deltaTime){
        Gdx.input.setInputProcessor(stage);
        stage.act(deltaTime);
        batch.begin();
        batch.draw(img, 0, 0);
        
        font.draw(batch, "Blackjack", 400, 550);
        font.draw(batch, "X" + Gdx.input.getX(), 200, 550);
        if(Gdx.input.isButtonJustPressed(1) == true && Gdx.input.getX() > 200 && Gdx.input.getX() < 400 && Gdx.input.getY() >= 600 && Gdx.input.getY() <= 800){
           
        }
        stage.draw();
        batch.end();
    }
    
    @Override 
    public void dispose(){
        stage.dispose();
        batch.dispose();
        
    }
    @Override
    public Stage getStage(){
        return stage;
    }
    
    public static HomeScene getOrMakeInstance(){
       
        if(scene == null){
            scene = new HomeScene();
            return scene;
        }
        else{
            return scene;
        }
    }
    
    
}
