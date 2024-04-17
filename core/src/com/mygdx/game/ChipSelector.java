/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
public class ChipSelector extends ImageButton {
    
    private final int width;
    private final int height;
    private Chip chip;
    
    public ChipSelector(Skin skin,int x,int y, String picture,Chip chip,Scene scene){
        super(skin);
        this.width = 50;
        this.height = 50;
        this.chip = chip;
        this.setVisible(true);
        this.setPosition(x, y);
        this.setColor(Color.RED);
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(picture))));
        this.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(picture))));

        
        this.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           
            scene.setCurrentSelection(chip);
           
            return true;
        }});
        
    }
    
    
}
