/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author conradkadel
 */
public class BJButton extends TextButton {
    
    private final int row_height = Gdx.graphics.getWidth() / 12;
    private final int col_width = Gdx.graphics.getWidth() / 12;
    private final int width;
    private final int height;

   
    public BJButton(Skin skin,String text,int x,int y){
        super("" + text,skin);
       


        this.width = col_width*2;
        this.height = row_height;
        
        setSize(this.width,this.height);
        setPosition(x,y);
        
    }
}
