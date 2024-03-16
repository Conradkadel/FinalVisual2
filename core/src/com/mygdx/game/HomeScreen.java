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
public class HomeScreen extends Scene{
    
    private Texture img;
    private BitmapFont font;
    private Skin mySkin;
    
    public static HomeScreen scene;
    
    public HomeScreen(){
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"));
        UIButton button1 = new UIButton(mySkin,1);
        stage.addActor(button1.button);
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("casino.png"));
        Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        img = new Texture(imgBig);

        font = new BitmapFont();
        font.setColor(Color.RED);
}
    @Override
    public void draw(){
        stage.act();
        batch.begin();
        batch.draw(img, 0, 0);
    
        
        batch.end();
        stage.draw();
    }
    
    public static HomeScreen getOrMakeInstance(){
        if(scene == null){
            scene = new HomeScreen();
            return scene;
        }
        else{
            return scene;
        }
    }
}
