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
import static com.mygdx.game.HomeScene.scene;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
    private MyButton startButton;
    private UIButton homeButton;
    private MyButton hitButton;
    private MyButton standButton;
    private MyButton doubleDButton;
    private MyButton splitButton;
    private Label labelOne;
    private Label labelTwo;
    private Label playerTotal;
    
    public static BlackJackScene scene; // Singelton Instance
    
    private float timeStamp;
    private Stage stage;
    private SpriteBatch batch;
    private static Chip currentSelection;
    
    
    private BlackJackScene(){
        // Start the BlackJackLogic Scene and load in all assets
        stage = new Stage(new ScreenViewport());
        
        batch = new SpriteBatch();
        font = new BitmapFont();        
      
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;

        playerTotal = new Label("Player Score: " + BlackJackLogic.getPlayerTotal(),labelStyle);
        playerTotal.setSize(Gdx.graphics.getWidth(), 50);
        playerTotal.setPosition(0, 450);

        startButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),450,500,"Start.png");
      
        startButton.setName("StartBUtton");
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            playing = true;
            BlackJackLogic.start();
            System.out.print("startButton Pressed ");
            return true;
        };
        });
        
        hitButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1000,700,"Hit.png");
        hitButton.setVisible(false);
        hitButton.setName("hitButton");
        hitButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BlackJackLogic.givePlayerCard();
            System.out.print("HitButton Pressed");
            timeStamp = Gdx.graphics.getDeltaTime();
            return true;
        };
        });
        
        standButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1000,400,"Stand.png");
        standButton.setVisible(false);
        standButton.setName("standButton");
        standButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            timeStamp = Gdx.graphics.getDeltaTime();
            dealerHit = true;
            BlackJackLogic.giveHiddenCard();
            return true;
        };
        });
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("bklackJackback.jpg"));
        Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        img = new Texture(imgBig);
        
        homeButton = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),0,800,GameStates.MENU,"Menu.png");
        
        homeButton.setSize(100, 65);
        labelOne = new Label("Player Wins !",labelStyle);
        labelOne.setName("labelOne");
        labelOne.setPosition(300, 800);
        labelOne.setVisible(false);
        
        
        stage.addActor(labelOne);
        stage.addActor(playerTotal);
        stage.addActor(homeButton);
        stage.addActor(startButton);
        stage.addActor(hitButton);
        stage.addActor(standButton);
        font.setColor(Color.BLACK);
        
       
    }
    private void begin(){
        BlackJackLogic.start();
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
    
    public void update(float deltaTime){
        time += Gdx.graphics.getDeltaTime();
        boolean check = BlackJackLogic.update();
        System.out.println("check =" + check);
        System.out.println("Playing =" + playing);
        if(check == true){ 
            playing = false;
            System.out.println("Check 2");
            labelOne.setText(BlackJackLogic.lastWinner.getName());
            labelOne.setVisible(true);
            
            startButton.setVisible(true);
            homeButton.setVisible(true);
            hitButton.setVisible(false);
            standButton.setVisible(false);
        }
        if(playing == true && check == false){
            System.out.println("Check1" );
            labelOne.setVisible(false);
            startButton.setVisible(false);
            homeButton.setVisible(false);
            hitButton.setVisible(true);
            standButton.setVisible(true);
        }
        
    }
    
    @Override
    public Scene returnScene(){
        return scene;
    }
    
    public static void setCurrentSelection(Chip c){
        currentSelection = c;
    }
    
    private void drawCards(ArrayList<Card> cards,SpriteBatch thisBatch,int showX, int showY){
        int counter = 1;
        for(Card card:cards){
            thisBatch.draw(card.getImageTexture(), showX * counter, showY);
            counter++;
        }
    }
 
    @Override
    public void draw(float deltaTime){
        // Where we draw the Window
        System.out.println("CHECK 1");
        Gdx.input.setInputProcessor(stage);
        update(deltaTime);
        
        stage.act(deltaTime);
        batch.begin();
        batch.draw(img, 0, 0);
         System.out.println("CHECK 2");
        
        if(playing == false) {
            // Wait for Bet            
             font.draw(batch, "Place Your Bets", 200, 200);

        }
        else{
             // Bet has been Placed and plaing
           
            drawCards(BlackJackLogic.getDealer(),batch,400,200);
            drawCards(BlackJackLogic.getPlayer(),batch,400,600);
            font.draw(batch,"Your Total :" + BlackJackLogic.getPlayerTotal(),400,200);
            font.draw(batch,"Dealer Total :" + BlackJackLogic.getDealerTotal(),400,100);   
        }
         
        stage.draw();
        batch.end();
        System.out.println("CHECK 3");
    }
    
    @Override 
    public void dispose(){
        stage.dispose();
        batch.dispose();
        System.out.println("CHECK 4");
        
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
