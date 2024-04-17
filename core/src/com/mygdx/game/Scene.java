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
    
    
    public abstract void draw(float deltaTime);
    public abstract void update(float deltaTime);
    public abstract Stage getStage();
    public abstract void dispose();
    public abstract Scene returnScene();
    public abstract void setCurrentSelection(Chip c);
 
    
}
