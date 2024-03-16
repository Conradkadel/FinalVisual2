/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
/**
 *
 * @author conradkadel
 */
public class UIButton extends ImageButton{
    
    private final int row_height = Gdx.graphics.getWidth() / 12;
    private final int col_width = Gdx.graphics.getWidth() / 12;
    private final int width;
    private final int height;
    public ImageButton button;
    
    public UIButton(Skin skin,int number){
        super(skin);
        button = new ImageButton(skin);
        this.width = col_width*4;
        this.height = row_height * number;
        button.setSize(this.width,this.height);
        button.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        button.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(GameStates.BLACKJACK);
            return true;
        };
        });
       
    }
    
    
}
