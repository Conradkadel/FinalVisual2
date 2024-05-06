/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


/**
 *
 * @author conradkadel
 */
public class Card {
    // This is the Card class and is used in combination with mz API Request 
    // Class to load up Cards and Decks...
    // Simple Card with a Suit and a Value
    // ImageAPI is provided by API
    
    private String code;
    
    private String image;
    
    private ImageAPI images;
    
    private String value;
    
    private Texture imageTexture;
    
    private String suit;
    
    
    public Texture getImageTexture(){  
        return new Texture("/Users/conradkadel/Desktop/Final Visual 2/assets/img/" + code + ".png");

    }

    public String getImage() {
        return image;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }
    
   
    
}
