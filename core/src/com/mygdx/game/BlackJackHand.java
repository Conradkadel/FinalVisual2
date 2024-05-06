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
    
    // BlackJack Hand that holds the cards and gets me information like total and handsize
    
    private ArrayList<Card> hand = new ArrayList<Card>();
    
    private String name;
    
    public BlackJackHand(String name){
        this.name = name;
    }
    
    public void giveCard(Card card){
        this.hand.add(card);
    }
    public String getName(){
        return name;
    }
    private int containsAce(){
        int countAce = 0;
        for(Card c:hand){
            if(c.getValue().equals("ACE"))
                countAce++;
        }
        return countAce;
    }
    
    public int getTotal(){
        int total = 0;
        if(hand != null){
            for(Card c:hand){
                if(c.getValue().equals("QUEEN") || c.getValue().equals("KING") || c.getValue().equals("JACK"))
                    total = 10 + total;
                else if(c.getValue().equals("ACE")){
                    total = 11 + total;
                }
                else
                    total = Integer.parseInt(c.getValue()) + total;   
            }
            if(total > 21 && containsAce() != 0){
                total = total - (containsAce()*10);
            }
        }
    return total;        
    }
    
    public ArrayList<Card> getCards(){
        return hand;
    }
}
