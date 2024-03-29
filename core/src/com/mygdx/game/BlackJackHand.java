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
public class BlackJackHand {
    
    private ArrayList<Card> hand = new ArrayList<Card>();
    
    public BlackJackHand(){
        
    }
    
    public void giveCard(Card card){
        this.hand.add(card);
    }
    
    public int getTotal(){
        int total = 0;
        if(hand != null){
            for(Card c:hand){
                if(c.getValue().equals("QUEEN") || c.getValue().equals("KING") || c.getValue().equals("ACE")||  c.getValue().equals("JACK") )
                    total = 10 + total;
                else
                    total = Integer.parseInt(c.getValue()) + total;
            }
        }
    return total;        
    }
}
