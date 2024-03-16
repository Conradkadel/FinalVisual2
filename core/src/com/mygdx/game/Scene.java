/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author conradkadel
 */
public abstract class Scene {
    
    protected Stage stage;
    protected SpriteBatch batch;
    
    public abstract void draw();
    
 
    
}
