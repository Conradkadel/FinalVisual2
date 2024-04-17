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
    private BJButton startButton;
    private UIButton homeButton;
    private BJButton hitButton;
    private BJButton standButton;
    private BJButton doubleDButton;
    private BJButton splitButton;
    private Label labelOne;
    private Label labelTwo;
    private Label playerTotal;
    
    public static BlackJackScene scene; // Singelton Instance
    
    private float timeStamp;
    private Stage stage;
    private Batch batch;
    
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

        startButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),450,500,"Start.png");
      
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
        
        hitButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1000,700,"Hit.png");
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
        
        standButton = new BJButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1000,400,"Stand.png");
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
        homeButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(GameStates.MENU);
           
            return true;
        }});
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
    
    @Override
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
    
    private void drawCards(ArrayList<Card> cards,SpriteBatch thisBatch,int showX, int showY){
        int counter = 1;
        for(Card card:cards){
            BufferedImage bufferedImage = null;
            java.awt.Image scaleVersion;
            Texture texture;
            
            try {
                // ONLINE SOURCE AND HELP USED FOR THIS 
                // Load image from URL
                URL url = new URL(card.getImage());
                bufferedImage = ImageIO.read(url);
                scaleVersion = bufferedImage.getScaledInstance(75, 125, 1);
                
                // Create texture from BufferedImage
                // Convert BufferedImage to Pixmap
                Pixmap pixmap = new Pixmap(bufferedImage.getWidth(), bufferedImage.getHeight(), Pixmap.Format.RGBA8888);
                for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    int argb = bufferedImage.getRGB(x, y);
                    int rgba8888 = ((argb & 0xff000000) >>> 24) | // Alpha
                                   ((argb & 0xff) << 16) |           // Red
                                   ((argb & 0xff00)) |               // Green
                                   ((argb & 0xff0000) >>> 16);       // Blue
                    pixmap.drawPixel(x, y, rgba8888);
                    }
                }
                Pixmap scalePixmap = new Pixmap(75, 125, pixmap.getFormat());
                scalePixmap.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, 75, 125);
                
                
                // Create texture from Pixmap
                texture = new Texture(scalePixmap);
                scalePixmap.dispose();
                pixmap.dispose();
                
               
            } catch (IOException e) {
                e.printStackTrace();
                texture = null;
            }

            thisBatch.draw(texture, showX * counter, showY);
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
