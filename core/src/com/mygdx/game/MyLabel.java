/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 *
 * @author conradkadel
 */
public class MyLabel extends Actor {
    
    private Texture picture;
    private int printX;
    private int printY;

    private Label label;
    private BitmapFont font;
    private String displayText;
    private String title;
    
    public MyLabel(Texture pic,int x, int y,String str,String title){
        this.picture = pic;
        this.printX = x;
        this.printY = y;  
        this.displayText = str;
        this.font = new BitmapFont(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/raw/font-title.fnt"));        
        this.title = title;
        
    }
    
    public void setTitle(String str){
        this.title = str;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(picture, printX, printY,200,125);
        font.draw(batch,this.title, printX + 60, printY + 105);
        font.draw(batch,this.displayText , printX + 80, printY + 60);
    }
    
    public void changeText(String text){
       displayText = text;
    }
    
}
