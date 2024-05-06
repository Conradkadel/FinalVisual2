/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */
public class BlackJackLogic {
    
    // BlackJack logic that handles everything done in the background
    // static class to be able to reference to it always
    
    
    private static Globals globals = Globals.getOrMakeInstance(); // Singelton
    
    private static BlackJackHand player = new BlackJackHand("player"); // Bj 1
    private static BlackJackHand dealer = new BlackJackHand("dealer"); // BJ 2
    private static Card hiddenCard;    
    private static Deck deck;
    private static String deckID;
    private static boolean dealerHit;
    private static boolean started = false;
    public static BlackJackHand lastWinner;
    private static ChipHolder chipHolder;
    private static float time;
    public static float waitTime;
    private static final Sound cardSound = Gdx.audio.newSound(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/Sounds/click.mp3"));
    public static BlackJackScene myScene;
 
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
    
    public static boolean update(float deltaTime){
        time += deltaTime;
        if(started == true){
            if(dealerHit == false){
                if(getPlayerTotal() >= 21){
                    myScene.activate(false);                   
                }
                if(getPlayerTotal() == 21 && time >= waitTime ){
                    playerWins();
                    return true;    
                }
                if(getPlayerTotal() >= 22 && time >= waitTime ){
                    System.out.println("Time:" + time +" to" + waitTime);
                    dealerWins();
                    return true;  
                }
            }
            if(dealerHit == true && time > waitTime){
                waitTime = time + 3;
                if(getDealerTotal() == getPlayerTotal()){
                    draw();
                    return true;
                }
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
                else if(getDealerTotal() >= 22){
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
        cardSound.play();
        waitTime = time + 3;
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
        cardSound.play();
        if(player.getTotal() > 21){
            return false;
        }
        return true;
    }
    public static boolean giveDealerCard(){
        dealer.giveCard(APIRequest.getCard(deckID));
        cardSound.play();
        if(dealer.getTotal() > 21){
            return false;
        }
        else
            return true;
    }
    
    private static void reset(){
        player = new BlackJackHand("player");
        dealer = new BlackJackHand("dealer");
        chipHolder.clearBet();
        dealerHit = false;
        started = false;
        // Give the Cards out
    }
    
    public static int getPlayerTotal(){
        return player.getTotal();
    }
    public static int getDealerTotal(){
        return dealer.getTotal();
    } 
    public static void addToHolder(ChipHolder c){
        chipHolder = c;
    }
    public static void allowBets(boolean bool){
        chipHolder.acceptsBets(bool);
    }
    public static boolean isBetPlaced(){
        return chipHolder.containsBet();
    }
    public static ArrayList<Card> getPlayer() {
            return player.getCards();
        }
    public static ArrayList<Card> getDealer() {
        return dealer.getCards();
    } 
    public static void playerWins(){
        lastWinner = player;
        myScene.activate(true);
        Player.giveMoney(chipHolder.returnWinnings() * 2);
        reset();      
    }
    public static void dealerWins(){
        lastWinner = dealer;
        myScene.activate(true);
        reset();      
    }
    public static void draw(){
        lastWinner = null;
        myScene.activate(true);
        reset();      
    }
    
   
}
