package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
        private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("casino.png");
                font = new BitmapFont();
                font.setColor(Color.RED);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
         
                Deck blackJackDeck = APIRequest.getNewDeck();
                Card singleCard = APIRequest.getCard(blackJackDeck.getDeck_id());
		batch.begin();
                batch.draw(img, 0, 0);
		font.draw(batch, singleCard.getSuit(), 200, 200);
		batch.end();
                
                
                
                
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
