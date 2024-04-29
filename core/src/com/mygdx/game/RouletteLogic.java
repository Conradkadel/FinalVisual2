/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */
public class RouletteLogic {
    private static ArrayList<ChipHolder> chipHoldersList = new ArrayList<ChipHolder>();
    
    public static void addToHolderList(ChipHolder c){
        chipHoldersList.add(c);
    }
    
    public static void clearAllBets(){
        for(ChipHolder cHolder: chipHoldersList){
            cHolder.clearBet();
        }
    }
    
    public static void returnWinnings(int winningNumber){
        for(ChipHolder holder:chipHoldersList){
            if(holder.checkIfWon(winningNumber)){
                Player.giveMoney(holder.returnWinnings());  
            }
        }
    }
}
