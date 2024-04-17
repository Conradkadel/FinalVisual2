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
    
    private Chip currentSelection;
    
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
       
        
        for(int i = 0; i < 5;i++){
            mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
            ChipSelector selector = new ChipSelector(mySkin,650 + (i *50),220,"Stand.png",new Chip(5),scene);
            stage.addActor(selector);
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
    
    @Override
    public void setCurrentSelection(Chip c){
        currentSelection = c;
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
