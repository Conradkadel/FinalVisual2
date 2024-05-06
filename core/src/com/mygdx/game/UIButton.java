/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 *
 * @author conradkadel
 */
public class UIButton extends ImageButton{
    
    private final int row_height = Gdx.graphics.getWidth() / 12;
    private final int col_width = Gdx.graphics.getWidth() / 12;
    private final int width;
    private final int height;
    private Sound clickSound;

    
    public UIButton(Skin skin,int x,int y,GameStates State,String pictureUP){
        super(skin);
        
        this.width = 350;
        this.height = 150;
        this.setSize(this.width,this.height);
        this.setPosition(x,y);
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/click.mp3"));
      
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(pictureUP))));
        super.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(pictureUP))));
        
        this.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(State);
           clickSound.play();
            return true;
        }});
        
    }
    
   
    
    
}
