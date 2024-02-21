/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author conradkadel
 */
public class APIRequest {
    // This class is will handle all API requests with static methods 
    // For blackjack the API is https://www.deckofcardsapi.com
    private static Json json = new Json();
    
    public static Deck getNewDeck(){
        HttpResponse<String> response = null;
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException ex){
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Convert the HTML To JSON to Class Deck
        
        String myJsonData = response.body();
        Deck respo = json.fromJson(Deck.class,myJsonData);
        
        return respo;
    }
    
    public static Card getCard(String id){
        HttpResponse<String> response = null;
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/" + id + "/draw/?count=1"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException ex){
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String myJsonData = response.body();
        DrawCard respo = json.fromJson(DrawCard.class,myJsonData);
            return respo.getCards();
    }
        
   
}
