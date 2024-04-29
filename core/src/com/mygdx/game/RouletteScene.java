/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Chip> chips;
    private static Chip currentSelection;
    private boolean startable;
    private int lastWinningNumber;
   
    private BitmapFont font;
   
    private float time;
    private boolean isRotating = false; 
    private float degrees = 0;
    private float startTime;
    private int rotatingSpeed;
    private ArrayList<ChipHolder> holders;
    
    private Actor rouletteWh;
    
    /// LIST ON IDEA
    // FOR TMR
    // FINISH ROULETTE BY ADDING FINAL POINTER, MAKING THE FIELDS POP UP WHEN WIN
    // TRY TO GET ROTATION GOING
    
    public RouletteScene(){
        // ADD BASIC STAGES AND BATCHES FOR LATER USE
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        font = new BitmapFont();
        
        // Background
        backroundImg = new Texture(new Pixmap(Gdx.files.internal("RouletteBackroun.png")));
         
        // BASIC UI
        UIButton buttonHome = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),0,750,GameStates.MENU,"Menu.png");
        buttonHome.setColor(Color.ORANGE);
        buttonHome.setSize(125, 75);
        buttonHome.setPosition(30, 700);
        stage.addActor(buttonHome);
       
        
        // LISTS FOR CHIPS AND PICTURES
        ArrayList<Integer> chipsValue = new ArrayList<Integer>();
        chipsValue.add(5);
        chipsValue.add(10);
        chipsValue.add(25);
        chipsValue.add(100);
        chipsValue.add(500);
        chipsValue.add(1000);
        
        ArrayList<String> pictures = new ArrayList<String>();
        pictures.add("5DollarChip.png");
        pictures.add("10DollarChip.png");
        pictures.add("25DollarChip.png");
        pictures.add("100DollarChip.png");
        pictures.add("500DollarChip.png");
        pictures.add("1KChip.png");
        
        chips = new ArrayList<Chip>();
        
        for(int i = 0; i < 6;i++){
            Chip chip = new Chip(chipsValue.get(i),pictures.get(i));
            ChipSelector selector = new ChipSelector(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"))
                    ,650 + (i *125),220,chip);
            selector.setSize(125, 75);
            chips.add(chip);
            stage.addActor(selector);
        }
        
        // UI WITH CHIPS ( HOLDERS AND MOVERS)
        
        
        int wBox = 56;
        int hBox = 90;
        for(int j = 0 ; j < 3 ; j++){
            for(int b = 0 ; b < 12 ; b++){
                int[] winningNumbers = {(j + 1) *(b + 1)}; 
                stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (b * wBox),400 - (j*hBox),wBox,hBox,winningNumbers,36));
            }
        }
        // First , Second , THird       Thirds
        for(int k = 0 ; k <= 2 ; k++){
            int[] winningNumbers = {(1 + (12*k)),(2 + (12*k)),(3 + (12*k)),(4 + (12*k)),(5 + (12*k)),(6 + (12*k)),(7 + (12*k)),(8 + (12*k))
             ,(9 + (12*k)),(10 + (12*k)),(11 + (12*k)),(12 + (12*k))};
            stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (k*(4*wBox)),460,4*wBox,hBox - 30,winningNumbers,3));
        }
        
        // Other first Scond and thrid rows
        for(int l = 1 ; l <= 3 ; l++){
            int[] winningNumbers = new int[12];
            for(int b = 0 ; b <= 11 ; b++){
                winningNumbers[b] = l + (3*b);
            }
           stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (12 * wBox),400 - ((l - 1)*hBox) , wBox,hBox,winningNumbers,3));
          
        }
        
        // ALL Double Bets
        int[] winningNumbersLower = new int[18];
        int[] winningNumbersHiger = new int[18];
        int[] winningNumbersEven = new int[18];
        int[] winningNumbersOdd = new int[18];
        for(int l = 0 ; l <= 35 ; l++){
            if((l % 2) == 0){
                winningNumbersEven[l / 2] = l + 1;
            }
            if((l % 2) == 1){
                winningNumbersOdd[l / 2] = l + 1;
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
       
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 ,520, wBox * 2,hBox - 30,winningNumbersLower,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (10 * wBox),520, wBox * 2,hBox - 30,winningNumbersHiger,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (2 * wBox),520, wBox * 2,hBox - 30,winningNumbersEven,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (8 * wBox),520, wBox * 2,hBox - 30,winningNumbersOdd,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (4 * wBox),520, wBox * 2,hBox - 30,winningNumbersBlack,2));
        stage.addActor(new ChipHolder(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),665 + (6 * wBox),520, wBox * 2,hBox - 30,winningNumbersRed,2));
       
        
        // 0
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
        
        Texture rouletteWheel = new Texture(new Pixmap(Gdx.files.internal("RouletteWheel.png")));
        Sprite rouletteSprite = new Sprite(rouletteWheel);
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
        // CREATES THE WINNING NUMBER BY PRESSING THE BUTTON
        // ALSO HANDLES WHAT TO DO NEXT
        
        lastWinningNumber = 0;
        Random random = new Random();
        Skin mySkinTwo = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        MyButton startButton = new MyButton(mySkinTwo,900,50,"Spin.png");
        startButton.setVisible(true);
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            float randomNumber = random.nextInt(0, 36);
            double oneNumber = 360.0 / 37.0 ;
            degrees = randomNumber * (float)oneNumber;
            rotatingSpeed = 10;
            isRotating = true;
            startTime = time;
           
            lastWinningNumber = rouletteNumber[(int)randomNumber];
            return true;
        }});
        stage.addActor(startButton);
        
    }

    @Override
    public void draw(float deltaTime) {
        Gdx.input.setInputProcessor(stage);
        // Overall timer
        time += deltaTime;
        stage.act(deltaTime);
        batch.begin();
        // background
        batch.draw(backroundImg, 0, 0);
        font.draw(batch, "Last Winning Number ;" + lastWinningNumber, 300, 900);
        
        // DRAWS THE CHIP AT THE MOUSE IF SELECTED
        if(currentSelection != null){
            batch.draw(currentSelection.getPictureTexture(),Gdx.input.getX() - 35 ,850 - Gdx.input.getY(),50,50);
        }
        // CHECKS IF ROTATING AND TIMER 
        // HAdnels what to do when the roulette wheel is done spinning
        if(isRotating){
            rouletteWh.rotateBy(1);
        }
        if(startTime + 3 < time && isRotating == true){
            if((int)degrees == (int)rouletteWh.getRotation()){
                rouletteWh.setRotation(-degrees);
                isRotating = false;
                RouletteLogic.returnWinnings(lastWinningNumber);
                RouletteLogic.clearAllBets();
                System.out.println("Over");
            }
            
        }
        // UNselects the currentSelection
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            currentSelection = null;
        }
        
        font.draw(batch, "Players Money: " + Player.getMoney(), 40, 225);
        font.draw(batch, "Players X: " + Gdx.input.getX(), 70, 250);
        font.draw(batch, "Players Y: " + Gdx.input.getY(), 70, 275);
        font.draw(batch, "Degrees: " + degrees + "Goal:" + rouletteWh.getRotation(), 70, 300);
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
    
    public static void setCurrentSelection(Chip c){
        currentSelection = c;
    }
    
    public static Chip getCurrentSelection(){
            return currentSelection;
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
