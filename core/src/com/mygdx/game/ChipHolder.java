/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */

public class ChipHolder extends Actor {
    
    private int printX;
    private int printY;
    private int width;
    private int height;
    private ArrayList<Chip> chipList;
    private int[] winningNumbers;
    private int value;
    BitmapFont font = new BitmapFont();
   
    Texture img;
    
    public ChipHolder(Skin skin,int x,int y,int w,int h, int[] numbers,int value){
        super();
        this.value = value;
        this.winningNumbers = numbers;
        this.printX = x;
        this.printY = y;
        this.width = w;
        this.height = h;
        this.setSize(w,h);
        this.chipList = new ArrayList<Chip>();
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("WhiteBox.png"));
        Pixmap imgBig = new Pixmap(w, h, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        this.img = new Texture(imgBig); 
        RouletteLogic.addToHolderList(this);
    }
   
    
    public boolean checkIfWon(int number){
        for(int n: winningNumbers){
            if(n == number){
                return true;
            }
        }
        return false;
    }
    
    public int returnWinnings(){
        int pot = 0;
        for(Chip c:chipList){
            pot = pot + c.getValue();
        }
        return pot * value;
    }
    public void clearBet(){
        this.chipList = new ArrayList<Chip>();
    }
   
    
    private boolean hovering(){
        if(Gdx.input.getX() > printX 
           && Gdx.input.getX() < (printX + width) 
           && Gdx.input.getY() + 35 > printY 
           && Gdx.input.getY() + 35 < (printY + height)){
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
        
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && hovering()){
          if( RouletteScene.getCurrentSelection() != null){
            Chip c = RouletteScene.getCurrentSelection();
            chipList.add(c);
            Player.takeMoney(c.getValue());
          }
        }
        
        if(!chipList.isEmpty()){
            int i = 0;
            int chipWidth = 50;
            for(Chip c: chipList){
                if(printY >= 460)
                    batch.draw(c.getPictureTexture(),printX + (this.width / 2 - chipWidth / 2) ,860 - printY - i * 5,50,50);
                else
                    batch.draw(c.getPictureTexture(),printX + (this.width / 2 - chipWidth / 2) ,880 - printY - i * 5,50,50);

                i++;
            }
        }
    }
    

    
}
