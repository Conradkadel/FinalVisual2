/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.ArrayList;

/**
 *
 * @author conradkadel
 */
public class AssetsLoader {
    
    // Loads some Assets I need during the project but dont wanna load them right at the spot
    // as its a lot to load
    
    public static ArrayList<ChipSelector> getChipSelectors(int x, int y){
        ArrayList<ChipSelector> chipsSelectors;
            // LISTS FOR CHIPS AND PICTURES
        ArrayList<Integer> chipsValue = new ArrayList<Integer>();
        chipsValue.add(5);
        chipsValue.add(10);
        chipsValue.add(25);
        chipsValue.add(100);
        chipsValue.add(500);
        chipsValue.add(1000);

        ArrayList<String> pictures = new ArrayList<String>();
        pictures.add("5DollarChip.png");
        pictures.add("10DollarChip.png");
        pictures.add("25DollarChip.png");
        pictures.add("100DollarChip.png");
        pictures.add("500DollarChip.png");
        pictures.add("1KChip.png");

        chipsSelectors = new ArrayList<ChipSelector>();

        for(int i = 0; i < 6;i++){
            Chip chip = new Chip(chipsValue.get(i),pictures.get(i));
            ChipSelector selector = new ChipSelector(new Skin(Gdx.files.internal("/Users/conradkadel/Desktop/Final Visual 2/assets/shade/skin/uiskin.json"))
                    ,x + (i *125),y,chip);
            selector.setSize(125, 75);
            chipsSelectors.add(selector);
        }
        
        
        return chipsSelectors;
    }
}
