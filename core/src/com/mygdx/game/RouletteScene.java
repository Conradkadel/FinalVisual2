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
public class RouletteScene extends Scene implements ChipManager{
    private static RouletteScene scene;
    private Stage stage;
    private SpriteBatch batch;
    private Texture backroundImg;
    private ArrayList<Chip> chips;
    private static Chip currentSelection;
    private int degrees;
    private boolean startable;
    private int lastWinningNumber;
    private Sprite rouletteSprite;
    private BitmapFont font;
    
    public RouletteScene(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        font = new BitmapFont();
        backroundImg = new Texture(new Pixmap(Gdx.files.internal("RouletteBackroun.png")));
         
        UIButton buttonHome = new UIButton(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json")),0,750,GameStates.MENU,"Menu.png");
        buttonHome.setColor(Color.ORANGE);
        stage.addActor(buttonHome);
       
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
                    ,650 + (i *125),220,chip,this);
            selector.setSize(125, 75);
            chips.add(chip);
            stage.addActor(selector);
        }
        
        ArrayList<ChipHolder> holders = new ArrayList<ChipHolder>();
        
        int wBox = 56;
        int hBox = 90;
        for(int j = 0 ; j < 3 ; j++){
            for(int b = 0 ; b < 12 ; b++)
                stage.addActor(new ChipHolder(665 + (b * wBox),400 - (j*hBox),wBox,hBox,this));
        }
        
        Pixmap rouletteImg = new Pixmap(Gdx.files.internal("RouletteWheel.png"));
        
        Texture rouletteWheel = new Texture(rouletteImg);
        rouletteSprite = new Sprite(rouletteWheel);
        rouletteSprite.setPosition(175,100);
        
        Integer[] rouletteNumber = { 0 , 28 , 3 , 35 , 12 , 28 , 7 , 29 , 18 , 22 , 9 ,
             31 , 14 , 20 , 1 , 33 , 18 , 24 , 5 , 10 , 23 , 8 , 30 , 11 , 36 , 13 , 
             27 , 6 , 34 , 17 , 25 , 2 , 21 , 4 , 19 , 15 , 32
        };
               
        stage.addActor(new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
                rouletteSprite.draw(batch);
               
            }
        });
        lastWinningNumber = 0;
        Random random = new Random();
        Skin mySkinTwo = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        MyButton startButton = new MyButton(mySkinTwo,200,600,"Spin.png");
        startButton.setVisible(true);
        startButton.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            int randomNumber = random.nextInt(0, 37);
            int oneNumber = 360 / 37 ;
            degrees = randomNumber * oneNumber;
            rouletteSprite.setRotation(0);
            rouletteSprite.rotate(-degrees);
            lastWinningNumber = rouletteNumber[randomNumber];
            return true;
        }});
        stage.addActor(startButton);
    }

    @Override
    public void draw(float deltaTime) {
        Gdx.input.setInputProcessor(stage);
     
        stage.act(deltaTime);
        batch.begin();
        batch.draw(backroundImg, 0, 0);
        font.draw(batch, "Last Winning Number ;" + lastWinningNumber, 100, 900);
        
        if(currentSelection != null){
            batch.draw(currentSelection.getPictureTexture(),Gdx.input.getX() - 35 ,850 - Gdx.input.getY() );
        }
        
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
    
    @Override
    public void setCurrentSelection(Chip c){
        currentSelection = c;
    }
    
    @Override
    public Chip getCurrentSelection(){
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
