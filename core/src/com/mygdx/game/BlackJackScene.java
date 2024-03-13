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
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    
    private SpriteBatch batch;
    private Texture img;
    private BitmapFont font;
    private Stage stage;
    private Skin mySkin;
    
    
    
    public static BlackJackScene scene;
    
    private BlackJackScene(){
        
        stage = new Stage(new ScreenViewport());
        mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);      
        
    }
    public void draw(){
        batch.begin();
        
        font.draw(batch, "Working Stage 2", 200, 200);
        batch.end();
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
