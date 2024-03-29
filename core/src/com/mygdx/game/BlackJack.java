/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author conradkadel
 */
public class BlackJack {
    
    private static BlackJackHand player = new BlackJackHand();
    private static BlackJackHand dealer = new BlackJackHand();
    private static Card hiddenCard;    
    private static Deck deck;
    private static String deckID;
    
    public static void start(){ 
        deck = APIRequest.getNewDeck();
        deckID = deck.getDeck_id();
        // Give the Cards out
        
        player.giveCard(APIRequest.getCard(deckID));
        dealer.giveCard(APIRequest.getCard(deckID));
        player.giveCard(APIRequest.getCard(deckID));
        hiddenCard = APIRequest.getCard(deckID);
        
    }
    
    public static boolean givePlayerCard(){
        player.giveCard(APIRequest.getCard(deckID));
        if(player.getTotal() > 21){
            return false;
        }
        return true;
    }
    public static boolean giveDealerCard(){
        dealer.giveCard(APIRequest.getCard(deckID));
        if(dealer.getTotal() > 21){
            return false;
        }
        else
            return true;
    }
    public static int getPlayerTotal(){
        return player.getTotal();
    }
    public static int getDealerTotal(){
        return dealer.getTotal();
    }
    
    
    
    
}
