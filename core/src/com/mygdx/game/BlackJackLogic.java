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
public class BlackJackLogic {
    
    private static Globals globals = Globals.getOrMakeInstance();
    private static BlackJackHand player = new BlackJackHand("player");
    private static BlackJackHand dealer = new BlackJackHand("dealer");
    private static Card hiddenCard;    
    private static Deck deck;
    private static String deckID;
    private static boolean dealerHit;
    private static boolean started = false;
    public static BlackJackHand lastWinner;
    
    public static void start(){ 
        deck = APIRequest.getNewDeck();
        deckID = deck.getDeck_id();
        started = true;
        // Give the Cards out
        
        player.giveCard(APIRequest.getCard(deckID));
        dealer.giveCard(APIRequest.getCard(deckID));
        player.giveCard(APIRequest.getCard(deckID));
        hiddenCard = APIRequest.getCard(deckID);
        
    }
    
    public static boolean update(){
        if(started == true){
            if(dealerHit == false){
                if(getPlayerTotal() == 21){
                    playerWins();
                    return true;    
                }
            }
            if(dealerHit == true){
                if(getDealerTotal() > 16 && getDealerTotal() < 22){ 
                    if(BlackJackLogic.getDealerTotal() < BlackJackLogic.getPlayerTotal() || BlackJackLogic.getDealerTotal() > 21){
                        playerWins();
                        return true;
                    }
                    else if(BlackJackLogic.getDealerTotal() > BlackJackLogic.getPlayerTotal() && BlackJackLogic.getDealerTotal() > 15 && BlackJackLogic.getDealerTotal() < 22){
                        dealerWins();
                        return true;    
                    }
                }
                else if(getDealerTotal() > 22){
                    playerWins();
                    return true;
                }
                else{
                    giveDealerCard();
                    return false;
                }
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
        player = new BlackJackHand("player");
        dealer = new BlackJackHand("dealer");
        dealerHit = false;
        started = false;
        // Give the Cards out
    }
    
    public static ArrayList<Card> getPlayer() {
            return player.getCards();
        }

    public static ArrayList<Card> getDealer() {
        return dealer.getCards();
    }
    
    public static void playerWins(){
        lastWinner = player;
        reset();
        
    }
    public static void dealerWins(){
        lastWinner = dealer;
        reset();
        
    }
   
}
