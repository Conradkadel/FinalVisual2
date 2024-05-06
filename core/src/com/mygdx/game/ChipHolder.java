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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author conradkadel
 */

public class ChipHolder extends Actor {
    
    private final int printX;
    private int printY;
    private final int width;
    private final int height;
    private ArrayList<Chip> chipList;
    public int[] winningNumbers;
    private final int value;
    private boolean acceptBets = true;
    private boolean visible = false;
    private Texture img;
    private Sound placeSound;
    
    public ChipHolder(Skin skin,int x,int y,int w,int h, int[] numbers,int value){
        super();
        this.value = value;
        this.winningNumbers = numbers;
        this.printX = x;
        this.printY = y;
        this.width = w;
        this.height = h;
        this.setBounds(x, y, width, height);
        this.placeSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/placeChip.mp3"));
        this.chipList = new ArrayList<Chip>();
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("WhiteBox.png"));
        Pixmap imgBig = new Pixmap(w, h, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        this.img = new Texture(imgBig); 
        
        if(this.value > 1)
            RouletteLogic.addToHolderList(this);
        else
            {
            BlackJackLogic.addToHolder(this);
             this.printY = this.printY - 80;
            }
    }
    
    public void toggleVisible(){
        this.visible = !this.visible;
    }
    
    public void clearBet(){
        this.chipList = new ArrayList<Chip>();
    }
    
    public boolean containsBet(){
        if(!this.chipList.isEmpty())
            return true;
        return false;
    }
    
    public void acceptsBets(boolean bool){
       acceptBets = bool;
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
    
    private boolean hovering(){
        if(Gdx.input.getX() > printX 
           && Gdx.input.getX() < (printX + this.width) 
           && Gdx.input.getY() + 90 > printY 
           && Gdx.input.getY() + 90 < (printY + this.height)){
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        if(hovering() || this.visible == true ){  
            if(winningNumbers != null && winningNumbers[0] == 0 )
                batch.draw(img,printX, 770 - printY); 
            else
                batch.draw(img,printX, 900 - printY);  
            
            if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
                if(!chipList.isEmpty())
                    chipList.remove(chipList.size() - 1);
            }
        }
       
        if(acceptBets == true && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && hovering()){
            if(Player.getCurrentSelection() != null){
              Chip c = Player.getCurrentSelection();
              placeSound.play();
              chipList.add(c);
              Player.takeMoney(c.getValue());
            }    
        }
        
        if(!chipList.isEmpty()){
            int i = 0;
            int chipWidth = 50;
            for(Chip c: chipList){
                if(printY >= 460)
                    batch.draw(c.getPictureTexture(),printX + (this.width / 2 - chipWidth / 2) ,900 - printY - i * 5,50,50);
                else
                    batch.draw(c.getPictureTexture(),printX + (this.width / 2 - chipWidth / 2) ,920 - printY - i * 5,50,50);

                i++;
            }
        }
    }
    

    
}
