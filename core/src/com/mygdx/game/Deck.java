/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author conradkadel
 */
public class Deck {
    // This is a Deck class used with the Card class and the API to create a 
    // card game that then can be controlled
    // This Class doesnt need to hold much information expept the id of the cards list
    // and the size of the Deck
    
    private boolean success;
    
    private String deck_id;
    
    private boolean shuffled;
    
    private int remaining;

    public String getDeck_id() {
        return deck_id;
    }

    public int getRemaining() {
        return remaining;
    }
    
    
}
