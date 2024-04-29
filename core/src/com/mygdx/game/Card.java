/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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
       
        if(imageTexture == null){
            BufferedImage bufferedImage = null;
            java.awt.Image scaleVersion;
            try {
            // ONLINE SOURCE AND HELP USED FOR THIS 
            // Load image from URL
            URL url = new URL(image);
            bufferedImage = ImageIO.read(url);
            scaleVersion = bufferedImage.getScaledInstance(75, 125, 1);

            // Create texture from BufferedImage
            // Convert BufferedImage to Pixmap
            Pixmap pixmap = new Pixmap(bufferedImage.getWidth(), bufferedImage.getHeight(), Pixmap.Format.RGBA8888);
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int argb = bufferedImage.getRGB(x, y);
                int rgba8888 = ((argb & 0xff000000) >>> 24) | // Alpha
                               ((argb & 0xff) << 16) |           // Red
                               ((argb & 0xff00)) |               // Green
                               ((argb & 0xff0000) >>> 16);       // Blue
                pixmap.drawPixel(x, y, rgba8888);
                }
            }
            Pixmap scalePixmap = new Pixmap(75, 125, pixmap.getFormat());
            scalePixmap.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, 75, 125);

          
            // Create texture from Pixmap
            imageTexture = new Texture(pixmap);
            scalePixmap.dispose();
            pixmap.dispose();


        } catch (IOException e) {
            e.printStackTrace();
            imageTexture = null;
        }
            
        }
        
        return imageTexture;
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
