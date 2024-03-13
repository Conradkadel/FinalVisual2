/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
/**
 *
 * @author conradkadel
 */
public class UIButton{
    
    private final int row_height = Gdx.graphics.getWidth() / 12;
    private final int col_width = Gdx.graphics.getWidth() / 12;
    public ImageButton button;
    
    public UIButton(Skin skin,int number){
        ImageButton button2 = new ImageButton(skin);
        button2.setSize(col_width*4,row_height * number);
        button2.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
        @Override
        public boolean handle(Event event) {
           Globals g = Globals.getOrMakeInstance();
           g.changeCurrentGameState(GameStates.BLACKJACK);
            return true;
        };
        });
        button = button2;
    }
    
    
}
