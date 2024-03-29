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
public class BlackJackScene extends Scene{
    // This is my Blackjack Scene class which is used to display the window of 
    // the blackjack game. Additionally will handle all inputs done by the user
    // Singelton Use here to make sure we only have one instance of BlackJackScene
    private Texture img;
    private final BitmapFont font;
    
    private boolean playing = false;
    
    private BJButton startButton;
    private UIButton homeButton;
    private BJButton hitButton;
    private BJButton standButton;
    private BJButton doubleDButton;
    private BJButton splitButton;
    
    private Stage stage2;
    
    public static BlackJackScene scene; // Singelton Instance
    
    private BlackJackScene(){
        // Start the BlackJack Scene and load in all assets
        stage = new Stage(new ScreenViewport());
        stage2 = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        font = new BitmapFont();
         
      
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        
        Label playerTotal = new Label("Player Score: " + BlackJack.getPlayerTotal(),labelStyle);
        playerTotal.setSize(Gdx.graphics.getWidth(), 50);
        playerTotal.setPosition(0, 450);
        
        
        
        startButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),300,600);
        startButton.setColor(Color.BLUE);
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            playing = true;
            Gdx.input.setInputProcessor(stage2);
            stage2.addActor(playerTotal);
            startButton.setColor(Color.GREEN);
            return true;
        };
        });
        
        hitButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),200,300);
        
        hitButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BlackJack.givePlayerCard();
            return true;
        };
        });
        
        standButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),500,300);
        standButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            
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

        stage.addActor(homeButton);
         stage.addActor(startButton);
        stage2.addActor(hitButton);
        
       
        stage2.addActor(standButton);
        font.setColor(Color.BLACK);
        
        Gdx.input.setInputProcessor(stage);
    }
    private void begin(){
        BlackJack.start();
     
    }
    @Override
    public void draw(){
        // Where we draw the Window
        batch.begin();
        batch.draw(img, 0, 0);
        stage.act();
        if(playing == false) {
            // Wait for Bet
      
             font.draw(batch, "Place Your Bets", 200, 200);
             stage.draw();
          
        }
        else{
             // Bet has been Placed and plaing
            this.begin();
            stage2.act();
            font.draw(batch,"Your Total :" + BlackJack.getPlayerTotal(),400,200);
            if(BlackJack.getPlayerTotal() != 21 || BlackJack.getDealerTotal() != 21){
                // Choosing Options (Stand , Hit, Double Down, Split with Pairs
                
            }
            else{
                if(BlackJack.getPlayerTotal() == 21)
                    // Player Win
                    
                if(BlackJack.getDealerTotal() == 21)
                    // Dealer Win
                
                this.playing = false;
                
            }
            
            stage2.draw();
        }
        
        
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
