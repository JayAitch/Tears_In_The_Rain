package com.allsopg.game.font;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Jordan Harrison on 08/02/2018.
 */
public class FontDrawer {
    private BitmapFont font;
    private String text;
    private SpriteBatch batch;


    public FontDrawer(String message){
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/yugothic.fnt"));
        text = message;
    }
    public void drawText(float yLocation, float xLocation){
        batch.begin();
        font.draw(batch, text,yLocation,xLocation);
        batch.end();
    }

}
