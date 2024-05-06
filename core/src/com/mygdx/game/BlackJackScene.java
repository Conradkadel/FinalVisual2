/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import static com.mygdx.game.BlackJackLogic.myScene;
import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */
public class BlackJackScene extends Scene{
    // This is my Blackjack Scene class which is used to display the window of 
    // the blackjack game. Additionally will handle all inputs done by the user
    // Singelton Use here to make sure we only have one instance of BlackJackScene
    
    public static BlackJackScene scene; // Singelton Instance
    
    private final Texture img;
    private final Texture betHolderImg;
    private final BitmapFont font;
    private boolean playing = false;
    private MyButton startButton;
    private final ChipHolder userBet;
    private final UIButton homeButton;
    private MyButton hitButton;
    private MyButton standButton;
    private final ArrayList<ChipSelector> chips;
    private Stage stage;
    private SpriteBatch batch;
    private MyLabel playerScore;
    private MyLabel dealerScore;
    private Sound clickSound;
    private float time;
    private boolean visable ;
    
    
    private BlackJackScene(){
        // Start the BlackJackLogic Scene and load in all assets
        this.stage = new Stage(new ScreenViewport()); 
        this.batch = new SpriteBatch();
        this.font = new BitmapFont(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/raw/font-title.fnt"));        
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/click.mp3"));
        this.visable = true;
        // MONEY UI Actor to display
        Sprite moneySprite = new Sprite(new Texture(new Pixmap(Gdx.files.internal("MoneyUI.png"))));
        moneySprite.setPosition(35,75);
        stage.addActor(new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
               moneySprite.draw(batch);
            }
        });
        
        // Add all the buttons
        
        startButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),800,500,"Start.png");
        startButton.setVisible(false);
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            playing = true;
           
            BlackJackLogic.allowBets(false);
            BlackJackLogic.start();
            clickSound.play();
            return true;
            };
        });
        
        hitButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1100,600,"Hit.png");
        hitButton.setVisible(false);
        hitButton.setSize(150, 85);
        hitButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BlackJackLogic.givePlayerCard(); 
            BlackJackLogic.waitTime = time + 5;
            
            clickSound.play();
            return true;
            };
        });
        
        standButton = new MyButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),1100,400,"Stand.png");
        standButton.setVisible(false);
        standButton.setSize(150, 85);
        standButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clickSound.play();
            activate(false);
            BlackJackLogic.giveHiddenCard();
            return true;
        };
        });
        
        img = new Texture(new Pixmap(Gdx.files.internal("bklackJackback.jpg")));
        homeButton = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),30,750,GameStates.MENU,"Menu.png"); 
        homeButton.setSize(125, 75);
        userBet = new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),350 ,870 ,100,100,null,1);
        betHolderImg = new Texture(Gdx.files.internal("betPLacer.png"));

        chips = AssetsLoader.getChipSelectors(600,100);
        
        for(int i = 0; i < 6;i++){
            stage.addActor(chips.get(i));    
        }
          
        playerScore = new MyLabel(new Texture(Gdx.files.internal("playerScore.png")),300,600," ","PLAYER");
        
        dealerScore = new MyLabel(new Texture(Gdx.files.internal("dealerScore.png")),300,300," ","DEALER");
        
        stage.addActor(userBet);
        stage.addActor(playerScore);
        stage.addActor(dealerScore);
        stage.addActor(homeButton);
        stage.addActor(startButton);
        stage.addActor(hitButton);
        stage.addActor(standButton);
        
        BlackJackLogic.myScene = this;
    }

    @Override
    public Stage getStage(){
        return stage;
    }
    
    public void update(float deltaTime){
        time += deltaTime;
        if(playing == true){
            playerScore.changeText(" " + BlackJackLogic.getPlayerTotal());
            dealerScore.changeText(" " + BlackJackLogic.getDealerTotal());
        }
        else{
            if(BlackJackLogic.lastWinner != null){
                if(BlackJackLogic.lastWinner.getName().matches("player")){
                    playerScore.changeText("WINNER");
                    dealerScore.changeText("LOOSER");
                }
                if(BlackJackLogic.lastWinner.getName().matches("dealer")){
                    dealerScore.changeText("WINNER");
                    playerScore.changeText("LOOSER");
                }
            }
        }
        
        boolean check = BlackJackLogic.update(deltaTime);
        
        if(check == true){ 
            playing = false;
            BlackJackLogic.allowBets(true);
            homeButton.setVisible(true);
            hitButton.setVisible(visable);
            standButton.setVisible(visable);
        }
        if(playing == true && check == false){
            startButton.setVisible(false);
            homeButton.setVisible(false);
            hitButton.setVisible(visable);
            standButton.setVisible(visable);
        }
        if(BlackJackLogic.isBetPlaced() && playing == false){
            startButton.setVisible(true);
        }
        else{
            startButton.setVisible(false);
        }
        
    }
    
    public void activate(boolean bool){
        visable = bool;
    }
        
    private void drawCards(ArrayList<Card> cards,SpriteBatch thisBatch,int showX, int showY){
        int counter = 1;
        for(Card card:cards){
            thisBatch.draw(card.getImageTexture(), showX +(75 * counter), showY,75,125);
            counter++;
        }
    }
 
    @Override
    public void draw(float deltaTime){
        // Where we draw the Window
      
        Gdx.input.setInputProcessor(stage);
        update(deltaTime);
        stage.act(deltaTime);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(betHolderImg, 330, 80,150,150);
        
        if(playing == false) {
            // Wait for Bet            
             font.draw(batch, "Place Your Bets", 175, 250);   
        }
        else{
             // Bet has been Placed and plaing
            drawCards(BlackJackLogic.getDealer(),batch,500,300);
            drawCards(BlackJackLogic.getPlayer(),batch,500,600);
    
        }
        
        if(playing == false){
            if(Player.getCurrentSelection() != null){
                batch.draw(Player.getCurrentSelection().getPictureTexture(),Gdx.input.getX() - 35 ,850 - Gdx.input.getY(),50,50);
            }
            if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
                Player.setCurrentSelection(null);
            }  
        }
        font.draw(batch,"" + Player.getMoney(), 102, 125);
        
        stage.draw();
        batch.end();
    }
    
    @Override 
    public void dispose(){
        stage.dispose();
        batch.dispose();
         
    }
    
    @Override
    public Scene returnScene(){
        return scene;
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
