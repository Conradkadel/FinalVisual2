/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author conradkadel
 */
public class ImageActor extends Actor{
    private Texture img;
    public ImageActor(Pixmap picture){
        Pixmap imgSmall = new Pixmap(Gdx.files.internal("bklackJackback.jpg"));
        Pixmap imgBig = new Pixmap(1980, 1020, imgSmall.getFormat());
        imgBig.drawPixmap(imgSmall,
                0, 0, imgSmall.getWidth(), imgSmall.getHeight(),
                0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
        img = new Texture(imgBig);
    }
}
