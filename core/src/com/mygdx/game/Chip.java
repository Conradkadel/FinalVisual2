/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author conradkadel
 */
public class Chip {
    private int value;
    private String pictureString;
    private Texture pictureTexture;
    
    public Chip(int v, String picture){
        this.value = v;
        this.pictureString = picture;
        
        Pixmap imgSmall = new Pixmap(Gdx.files.internal(picture));
        
        pictureTexture = new Texture(imgSmall);
    }
    
    public int getValue(){
        return value;
    }
    public String getPictureString(){
        return pictureString;
    }
    public Texture getPictureTexture(){
        return pictureTexture;
    }
}
