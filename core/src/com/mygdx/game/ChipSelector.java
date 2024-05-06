/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 *
 * @author conradkadel
 */
public class ChipSelector extends MyButton {
    
    private final int width;
    private final int height;
    private Chip chip;
    private Sound clickSound;
      
    public ChipSelector(Skin skin,int x,int y,Chip chip){
        super(skin,x,y,chip.getPictureString());
        this.width = 125;
        this.height = 75;
        this.setSize(width, height);
        this.setVisible(true);
        this.setColor(Color.RED);
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/pickUpChip.mp3"));
        this.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clickSound.play();
            Player.setCurrentSelection(chip);           
            return true;
        }});
        
    }
    
    
}
