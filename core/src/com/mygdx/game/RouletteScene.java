/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author conradkadel
 */
public class RouletteScene extends Scene {
    
    private static RouletteScene scene;
    
    private Stage stage;
    private SpriteBatch batch;
    private Texture backroundImg;
    private Texture unselectImg;
    private ArrayList<ChipSelector> chipSelectors;
    private int lastWinningNumber;
    private BitmapFont font;
    private float time;
    private boolean isRotating = false; 
    private float degrees = 0;
    private float startTime;
    private Actor rouletteWh;
    private Sprite rouletteSprite;
    private Sprite rouletteArrow;
    private int waitTime = 0;
    public boolean isWaiting = true; 
    private Sound clickSound;
    private Sound rotatingSound;
    private MyButton startButton;
    
    public RouletteScene(){
        // ADD BASIC STAGES AND BATCHES FOR LATER USE
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        this.font = new BitmapFont(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/raw/font-title.fnt"));
        this.rotatingSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/roulletteSpin.mp3"));

        // Background
        backroundImg = new Texture(new Pixmap(Gdx.files.internal("RouletteBackroun.png")));
        unselectImg = new Texture(new Pixmap(Gdx.files.internal("unselect.png")));
         
        // BASIC UI
        UIButton buttonHome = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),30,750,GameStates.MENU,"Menu.png");
        buttonHome.setSize(125, 75);
        stage.addActor(buttonHome);

        chipSelectors = AssetsLoader.getChipSelectors(650,220);
        
        for(int i = 0; i < 6;i++){
            stage.addActor(chipSelectors.get(i)); // ADD chip selectors
        }
        
        int wBox = 56;
        int hBox = 90;
        int winningNumberTest = 1;
        for(int b = 0 ; b < 12 ; b++){
            for(int j = 0 ; j < 3 ; j++){
                int[] winningNumber = {winningNumberTest}; 
                stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (b * wBox),450 - (j*hBox),wBox,hBox,winningNumber,36));
                winningNumberTest++;
            }
        }
        // First , Second , THird   Thirds
        for(int k = 0 ; k <= 2 ; k++){
            int[] winningNumbers = {(1 + (12*k)),(2 + (12*k)),(3 + (12*k)),(4 + (12*k)),(5 + (12*k)),(6 + (12*k)),(7 + (12*k)),(8 + (12*k))
             ,(9 + (12*k)),(10 + (12*k)),(11 + (12*k)),(12 + (12*k))};
            stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (k*(4*wBox)),510, 4*wBox,60,winningNumbers,3));
        }
        
        // Other first Scond and thrid rows
        for(int l = 1 ; l <= 3 ; l++){
            int[] winningNumbers = new int[12];
            for(int b = 0 ; b <= 11 ; b++){
                winningNumbers[b] = l + (3*b);
            }
           stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (12 * wBox),450 - ((l - 1)*hBox) , wBox,hBox,winningNumbers,3));
          
        }
        
        // ALL Double Bets
        int[] winningNumbersLower = new int[18];
        int[] winningNumbersHiger = new int[18];
        int[] winningNumbersEven = new int[18];
        int[] winningNumbersOdd = new int[18];
        for(int l = 0 ; l <= 35 ; l++){
            if((l % 2) == 0){
                winningNumbersOdd[l / 2] = l + 1;
            }
            if((l % 2) == 1){
                winningNumbersEven[l / 2] = l + 1;
            }
            if(l <= 17){
                winningNumbersLower[l] = l+1;
            }
            if(l > 17){
                winningNumbersHiger[l % 18] = l+1;
            }
        }
        int[] winningNumbersBlack = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
        int[] winningNumbersRed = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
       
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 ,570, wBox * 2,60,winningNumbersLower,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (10 * wBox),570, wBox * 2,60,winningNumbersHiger,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (2 * wBox),570, wBox * 2,60,winningNumbersEven,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (8 * wBox),570, wBox * 2,60,winningNumbersOdd,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (4 * wBox),570, wBox * 2,60,winningNumbersRed,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (6 * wBox),570, wBox * 2,60,winningNumbersBlack,2));
       
        int[] winningNumbersZero = {0};   
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),610 ,320, wBox,hBox *3,winningNumbersZero,36));

        
        // MONEY UI Actor to display
        Sprite moneySprite = new Sprite(new Texture(new Pixmap(Gdx.files.internal("MoneyUI.png"))));
        moneySprite.setPosition(35,75);
        stage.addActor(new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
               moneySprite.draw(batch);
            }
        });
        
        // ROulette Wheel actor that handels the spinning etc...
        rouletteSprite = new Sprite(new Texture(new Pixmap(Gdx.files.internal("RouletteWheel.png"))));
        rouletteSprite.setPosition(115,180);
        Integer[] rouletteNumber = { 0 , 26 , 3 , 35 , 12 , 28 , 7 , 29 , 18 , 22 , 9 ,
             31 , 14 , 20 , 1 , 33 , 18 , 24 , 5 , 10 , 23 , 8 , 30 , 11 , 36 , 13 , 
             27 , 6 , 34 , 17 , 25 , 2 , 21 , 4 , 19 , 15 , 32
        };
        rouletteWh = new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
                if(isRotating){
                    rouletteSprite.rotate(-1);
                }
                else{
                    rouletteSprite.setRotation(0);
                    rouletteSprite.rotate(-degrees);
                }
                rouletteSprite.draw(batch);
               
            }
        };
        stage.addActor(rouletteWh);
        
        rouletteArrow = new Sprite(new Texture(new Pixmap(Gdx.files.internal("GreenArrow.png"))));
        Actor rouletteArrowActor = new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
                rouletteArrow.draw(batch);
               
            }
        };
        rouletteArrow.setPosition(300, 630);
        stage.addActor(rouletteArrowActor);
        
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/click.mp3"));
        
        // CREATES THE WINNING NUMBER BY PRESSING THE BUTTON
        // ALSO HANDLES WHAT TO DO NEXT
        
        lastWinningNumber = 0;
        Random random = new Random();
        Skin mySkinTwo = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        startButton = new MyButton(mySkinTwo,900,50,"Spin.png");
        startButton.setVisible(true);
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            float randomNumber = random.nextInt(0, 36);
            double oneNumber = 360.0 / 37.0 ;
            // no more bets
            RouletteLogic.setActivated(false);
            rotatingSound.play();
            clickSound.play();
            degrees = randomNumber * (float)oneNumber;
            isRotating = true;
            startTime = time;
            isWaiting = false;
            lastWinningNumber = rouletteNumber[(int)randomNumber];
            return true;
        }});
        stage.addActor(startButton);
        
    }
    
    public void update(float deltaTime){
 
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            Player.setCurrentSelection(null);
        }
        
        // CHECKS IF ROTATING AND TIMER 
        // HAdnels what to do when the roulette wheel is done spinning
        startButton.setVisible(isWaiting);
        if(isWaiting == false){
            if(isRotating == true){
                rouletteWh.rotateBy(1);
                if(startTime + 3 < time){
                    if((int)degrees == (int)rouletteWh.getRotation()){
                        rouletteWh.setRotation(degrees);
                        RouletteLogic.toggleWinner(lastWinningNumber);
                        isRotating = false;
                        waitTime = (int)time + 5;                       
                    }
                }
            }
            else {  
                if(waitTime < (int)time && waitTime != 0){
                   isWaiting = true;
                   RouletteLogic.toggleWinner(lastWinningNumber);
                   RouletteLogic.finishBet(lastWinningNumber);
               }
            }
        }
    
    }

    @Override
    public void draw(float deltaTime) {
        Gdx.input.setInputProcessor(stage);
        // Overall timer
        time += deltaTime;
        this.update(deltaTime);
        stage.act(deltaTime);
        batch.begin();
        // background
        batch.draw(backroundImg, 0, 0); 
        batch.draw(unselectImg,800,800,500,100);
        // DRAWS THE CHIP AT THE MOUSE IF SELECTED
    
        if(Player.getCurrentSelection() != null){
            batch.draw(Player.getCurrentSelection().getPictureTexture(),Gdx.input.getX() - 35 ,850 - Gdx.input.getY(),50,50);
        }
        font.draw(batch,"" + Player.getMoney(), 102, 125);

        stage.draw();
        batch.end(); 
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {

    }

    @Override
    public Scene returnScene(){
        return scene;
    }
   
    
    public static RouletteScene getOrMakeInstance(){
         if(scene == null){
            scene = new RouletteScene();
            return scene;
        }
        else{
            return scene;
        }
    }
    
}
