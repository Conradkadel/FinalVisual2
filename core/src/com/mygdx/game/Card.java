/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author conradkadel
 */
public class Card {
    // This is the Card class and is used in combination with mz API Request 
    // Class to load up Cards and Decks...
    // Simple Card with a Suit and a Value
    // Image is provided by API
    
    private String code;
    
    private String image;
    
    private Image images;
    
    private String value;
    
    private String suit;

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
