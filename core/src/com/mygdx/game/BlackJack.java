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
    
    private static Globals globals = Globals.getOrMakeInstance();
    private static BlackJackHand player = new BlackJackHand();
    private static BlackJackHand dealer = new BlackJackHand();
    private static Card hiddenCard;    
    private static Deck deck;
    private static String deckID;
    private static boolean dealerHit;
    
    public static void start(){ 
        deck = APIRequest.getNewDeck();
        deckID = deck.getDeck_id();
        // Give the Cards out
        
        player.giveCard(APIRequest.getCard(deckID));
        dealer.giveCard(APIRequest.getCard(deckID));
        player.giveCard(APIRequest.getCard(deckID));
        hiddenCard = APIRequest.getCard(deckID);
        
    }
    
    public static boolean update(){
        
        if(dealerHit == false){
            if(getPlayerTotal() == 21){
                if(BlackJack.getPlayerTotal() == 21){
                    playerWins();
                    return true;
                   }
            }
        }
        if(dealerHit == true){
            if(getDealerTotal() > 16 && getDealerTotal() < 22){ 
                if(BlackJack.getDealerTotal() < BlackJack.getPlayerTotal() || BlackJack.getDealerTotal() > 21){
                    playerWins();
                    return true;
                }
                else if(BlackJack.getDealerTotal() > BlackJack.getPlayerTotal() && BlackJack.getDealerTotal() > 15 && BlackJack.getDealerTotal() < 22){
                    dealerWins();
                    return true;    
                }
            }
            else if(getDealerTotal() > 22){
                playerWins();
                return false;
            }
            else{
                giveDealerCard();
            }
        }
        return false;
    }
    
    public static void giveHiddenCard(){
        dealer.giveCard(hiddenCard);
        dealerHit = true;
        
    }
    
    public static boolean check(){
       
       if(dealer.getTotal() < 16){
           return true;
       }
       else
           return false;
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
    
    private static void reset(){
        player = new BlackJackHand();
        dealer = new BlackJackHand();
        dealerHit = false;
        
        // Give the Cards out
    }
    
    public static void playerWins(){
        reset();
        
    }
    public static void dealerWins(){
        reset();
        
    }
    
    
    
}
