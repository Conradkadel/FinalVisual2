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
public class DrawCard {
    // This is a needed class for my API to handle the request to draw a card
    // as it doesnt only return the card itself 
    
    private boolean success;
    
    private String deck_id;
    
    private boolean shuffled;
    
    private ArrayList<Card> cards;
    
    private int remaining;
    

    public String getDeck_id() {
        return deck_id;
    }

    public Card getCards() {
        return cards.get(0);
    }
    
}
