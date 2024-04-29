/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author conradkadel
 */
public class Player {
    
    private static int money;
    
    private static int wins;
    
    public static void giveMoney(int amount){
        money = money + amount;
    }
    public static void takeMoney(int amount){
        money = money - amount;
    }
    
    public static int getMoney(){
        return money;
    }
    
}
