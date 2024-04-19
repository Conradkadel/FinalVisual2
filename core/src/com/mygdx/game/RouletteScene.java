/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;




/**
 *
 * @author conradkadel
 */
public class RouletteScene extends Scene {
    private static RouletteScene scene;
    private Stage stage;
    private SpriteBatch batch;
    private Texture backroundImg;
    private Texture rouletteWheel;
    private ArrayList<Chip> chips;
    private static Chip currentSelection;
    
    public RouletteScene(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("RouletteBackroun.png"));
        backroundImg = new Texture(imgSmall);
        
        imgSmall = new Pixmap(Gdx.files.internal("RouletteWheel.png"));
        rouletteWheel = new Texture(imgSmall);

        
        Skin mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        UIButton buttonHome = new UIButton(mySkin,0,750,GameStates.MENU,"Menu.png");
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
            mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
            Chip chip = new Chip(chipsValue.get(i),pictures.get(i));
            ChipSelector selector = new ChipSelector(mySkin,650 + (i *125),220,chip);
            selector.setSize(125, 75);
            chips.add(chip);
            stage.addActor(selector);
        }
        
        ArrayList<ChipHolder> holders = new ArrayList<ChipHolder>();
        
        int wBox = 56;
        int hBox = 90;
        for(int j = 0 ; j < 3 ; j++){
            for(int b = 0 ; b < 12 ; b++)
                stage.addActor(new ChipHolder(665 + (b * wBox),400 - (j*hBox),wBox,hBox));
        }
    }

    @Override
    public void draw(float deltaTime) {
        Gdx.input.setInputProcessor(stage);
        update(deltaTime);
        stage.act(deltaTime);
        batch.begin();
        batch.draw(backroundImg, 0, 0);
        batch.draw(rouletteWheel,100,100);
        
        if(currentSelection != null){
            
            batch.draw(currentSelection.getPictureTexture(),Gdx.input.getX(),Gdx.input.getY() );
        }
        
        stage.draw();
        batch.end(); 
    }

    @Override
    public void update(float deltaTime) {
       
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
