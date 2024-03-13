/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author conradkadel
 */
public class Globals {
    
    private static Globals globals;
    
    private GameStates currentGameState;
    
    private Globals(){
        currentGameState = GameStates.MENU;
    }
    
    public void changeCurrentGameState(GameStates state){
        currentGameState = state;
    }
    
    public GameStates getGameState(){
        return currentGameState;
    }
    
    public static Globals getOrMakeInstance(){
        if(globals == null){
            globals = new Globals();
            return globals;
                   
        }
        else
            return globals;
    }
}
