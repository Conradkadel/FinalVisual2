/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */
public class ChipHolder extends Actor{
    private int printX;
    private int printY;
    private int width;
    private int height;
    private ArrayList<Chip> chipList;
    
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    BitmapFont font = new BitmapFont();
   
    Texture img;
    
    public ChipHolder(int x,int y,int w,int h){
        super();
        this.printX = x;
        this.printY = y;
        this.width = w;
        this.height = h;
        this.chipList = new ArrayList<Chip>();
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("WhiteBox.png"));
        Pixmap imgBig = new Pixmap(w, h, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        this.img = new Texture(imgBig);
        this.addListener(new InputListener(){
        
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            chipList.add(RouletteScene.getCurrentSelection());
            return true;
        };
        });
        
    }
    
    public boolean hovering(){
        if(Gdx.input.getX() > printX 
           && Gdx.input.getX() < (printX + width) 
           && Gdx.input.getY() > printY 
           && Gdx.input.getY() < (printY + height)){
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        font.draw(batch, "Working "+ "X :" + Gdx.input.getX() + " Y: " + Gdx.input.getY(), 300, 600);
        if(hovering()){            
            batch.draw(img,printX, 850 - printY);          
        }
        if(!chipList.isEmpty()){
            for(Chip c: chipList){
                batch.draw(c.getPictureTexture(),printX,900 - printY);
            }
        }
    }
    

    
}
