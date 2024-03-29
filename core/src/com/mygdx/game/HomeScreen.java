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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    
    public HomeScreen(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        font = new BitmapFont();
        font.setColor(Color.GOLD);
        
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        
        labelStyle.fontColor = Color.BLACK;
        
        

        UIButton button1 = new UIButton(mySkin,Gdx.graphics.getWidth() / 2 - 350,500,GameStates.BLACKJACK);
        button1.setName("BlackJack");
        button1.setColor(Color.BLUE);
        UIButton button2 = new UIButton(mySkin,Gdx.graphics.getWidth() / 2 - 350,200,GameStates.BLACKJACK);
        button2.setColor(Color.RED);
        UIButton button3 = new UIButton(mySkin,Gdx.graphics.getWidth() / 2 + 100,500,GameStates.BLACKJACK);
        button3.setColor(Color.GREEN);
        UIButton button4 = new UIButton(mySkin,Gdx.graphics.getWidth() / 2 + 100,200,GameStates.BLACKJACK);
        button4.setColor(Color.PURPLE);
        
        Label blackJackLabel = new Label("Blackjack",labelStyle);
        blackJackLabel.setSize(150, 50);
        blackJackLabel.setPosition(button1.getX(), button1.getY());
        
        Label roulettLable = new Label("Roulette",labelStyle);
        blackJackLabel.setSize(150, 50);
        blackJackLabel.setPosition(button2.getX(), button2.getY());
        
        Label baccarattLabel = new Label("Baccaratt",labelStyle);
        blackJackLabel.setSize(150, 50);
        blackJackLabel.setPosition(button3.getX(), button4.getY());
       
       Label title = new Label("CASINO",labelStyle);
       title.setColor(Color.GOLD);
       title.setSize(400,400);
       title.setPosition(400, 600);
       
       stage.addActor(title);
       
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
        
      
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("casino.png"));
        Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        img = new Texture(imgBig);
  
        stage.addActor(blackJackLabel);
        stage.addActor(roulettLable);
        stage.addActor(baccarattLabel);
        
        Gdx.input.setInputProcessor(stage);
          
        
}
    @Override
    public void draw(){
        this.stage.act();
        batch.begin();
        batch.draw(img, 0, 0);
        
        font.draw(batch, "Blackjack", 400, 550);
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
