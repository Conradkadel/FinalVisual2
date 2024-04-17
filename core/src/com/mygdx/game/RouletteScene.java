/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 *
 * @author conradkadel
 */
public class RouletteScene extends Scene {
    private static RouletteScene scene;
    private Stage stage;
    

    @Override
    public void draw(float deltaTime) {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {

    }
    
    
    public static RouletteScene getOrMakeInstance(){
         if(scene == null){
            scene = new RouletteScene();
            return scene;
        }
        else{
            return scene;
        }
    }
    
}
