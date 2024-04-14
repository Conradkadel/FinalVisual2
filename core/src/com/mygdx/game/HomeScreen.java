/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 *
 * @author conradkadel
 */
public class HomeScreen extends Scene{
    // The HomeScreen Class which is extended from the abstact class Scene
    // THis Class will be used to display the home screen of the application
    // Also it will handle user input and event listerners
    // 4 Buttons that lead to other Games
    
    private Texture img;
    private BitmapFont font;
    private Skin mySkin;
    public static HomeScreen scene;
    
    private HomeScreen(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        font = new BitmapFont();
        font.setColor(Color.GOLD);
        
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        
        labelStyle.fontColor = Color.BLACK;
        
        

        UIButton button1 = new UIButton(mySkin,200,450,GameStates.BLACKJACK,"BlackJackText.png");
        button1.setColor(Color.ORANGE);
        button1.setName("BlackJack");
        UIButton button2 = new UIButton(mySkin,850,450,GameStates.BLACKJACK,"Roulette.png");
        button2.setName("Roulette");
        button2.setColor(Color.ORANGE);
        
       
        
        Label blackJackLabel = new Label("Blackjack",labelStyle);
        blackJackLabel.setSize(150, 50);
        blackJackLabel.setName("BlackJackLabel");
        blackJackLabel.setPosition(button1.getX(), button1.getY());
        
        Label roulettLable = new Label("Roulette",labelStyle);
        roulettLable.setSize(150, 50);
        roulettLable.setName("RouletteLabel");
        roulettLable.setPosition(button2.getX(), button2.getY());
    
       
    
      
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("casinoBackround.png"));
      
        img = new Texture(imgSmall);
        
    
        stage.addActor(button1);
        stage.addActor(button2);
       
        
  
        stage.addActor(blackJackLabel);
        stage.addActor(roulettLable);
       
        
          
        
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
    
    public static HomeScreen getOrMakeInstance(){
       
        if(scene == null){
            scene = new HomeScreen();
            return scene;
        }
        else{
            return scene;
        }
    }
    
    
}
