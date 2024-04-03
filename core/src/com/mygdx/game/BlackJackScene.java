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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
public class BlackJackScene extends Scene{
    // This is my Blackjack Scene class which is used to display the window of 
    // the blackjack game. Additionally will handle all inputs done by the user
    // Singelton Use here to make sure we only have one instance of BlackJackScene
    private Texture img;
    private final BitmapFont font;
    
    private boolean playing = false;
    private boolean dealerHit = false;
    
    private long time;
    private BJButton startButton;
    private UIButton homeButton;
    private BJButton hitButton;
    private BJButton standButton;
    private BJButton doubleDButton;
    private BJButton splitButton;
    private Label labelOne;
    private Label labelTwo;
    
    public static BlackJackScene scene; // Singelton Instance
    
    private float timeStamp;
    
    private BlackJackScene(){
        // Start the BlackJack Scene and load in all assets
        stage = new Stage(new ScreenViewport());
       
        batch = new SpriteBatch();
        font = new BitmapFont();
         
      
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        
        Label playerTotal = new Label("Player Score: " + BlackJack.getPlayerTotal(),labelStyle);
        playerTotal.setSize(Gdx.graphics.getWidth(), 50);
        playerTotal.setPosition(0, 450);
        
        
        
        startButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),"Start",300,600);
        startButton.setColor(Color.BLUE);
        startButton.setName("StartBUtton");
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            playing = true;
            BlackJack.start();
            labelOne.setVisible(false);
            labelTwo.setVisible(false);
            startButton.setVisible(false);
            homeButton.setVisible(false);
            hitButton.setVisible(true);
            standButton.setVisible(true);
            startButton.setColor(Color.GREEN);
            return true;
        };
        });
        
        hitButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),"Hit",200,300);
        hitButton.setVisible(false);
        hitButton.setName("hitButton");
        hitButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BlackJack.givePlayerCard();
            
            timeStamp = Gdx.graphics.getDeltaTime();
            return true;
        };
        });
        
        standButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),"Stand",500,300);
        standButton.setVisible(false);
        standButton.setName("standButton");
        standButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            timeStamp = Gdx.graphics.getDeltaTime();
            dealerHit = true;
            BlackJack.giveHiddenCard();
            return true;
        };
        });
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("bklackJackback.jpg"));
        Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        img = new Texture(imgBig);
        
        
        homeButton = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),600,200,GameStates.MENU);

        labelOne = new Label("Player Wins !",labelStyle);
        labelOne.setName("labelOne");
        labelOne.setPosition(300, 800);
        labelOne.setVisible(false);
        labelTwo = new Label("Dealer Wins !",labelStyle);
        labelTwo.setPosition(300, 800);
        labelTwo.setName("labelTwo");
        labelTwo.setVisible(false);
        
        stage.addActor(labelOne);
        stage.addActor(labelTwo);
        stage.addActor(homeButton);
        stage.addActor(startButton);
        stage.addActor(hitButton);
        stage.addActor(standButton);
        font.setColor(Color.BLACK);
        
       
    }
    private void begin(){
        BlackJack.start();
    }
    
    private boolean wait(float time){
        if(Gdx.graphics.getDeltaTime() >= time){
            return true;
        }
        else
            return false;
    } 
    @Override
    public Stage getStage(){
        return stage;
    }
    
    @Override
    public void update(float deltaTime){
        time += Gdx.graphics.getDeltaTime();
        boolean check = BlackJack.update();
        if(check = true){
            playing = false;
            labelOne.setVisible(true);
            startButton.setVisible(true);
             homeButton.setVisible(true);
             hitButton.setVisible(false);
             standButton.setVisible(false);
        }
        else{
            
        }
        
    }
 
    @Override
    public void draw(float deltaTime){
        // Where we draw the Window
        update(deltaTime);
        stage.act(deltaTime);
        batch.begin();
        batch.draw(img, 0, 0);
        
        if(playing == false) {
            // Wait for Bet
            
             font.draw(batch, "Place Your Bets", 200, 200);
            
          
        }
        else{
             // Bet has been Placed and plaing
            
            font.draw(batch,"Your Total :" + BlackJack.getPlayerTotal(),400,200);
            font.draw(batch,"Dealer Total :" + BlackJack.getDealerTotal(),400,100);
            
                
        }
        
        stage.draw();
        batch.end();
    }
    
    @Override 
    public void dispose(){
        stage.dispose();
        batch.dispose();
        
    }
    
    public static BlackJackScene getOrMakeInstance(){
        if(scene == null){
            scene = new BlackJackScene();
            return scene;
        }
        else{
            return scene;
        }
    }
}
