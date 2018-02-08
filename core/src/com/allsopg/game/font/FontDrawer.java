package com.allsopg.game.font;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * simple class to generate bitmapped fonts for drawing
 * Created by Jordan Harrison on 08/02/2018.
 */
public class FontDrawer {
    private BitmapFont font;
    private String text;
    private SpriteBatch batch;

// constructor will create a string from the passed in parameter
    public FontDrawer(String message){
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/yugothic.fnt"));
        text = message;
    }
    //local draw method that can take the sprites position
    public void drawText(float yLocation, float xLocation){
        batch.begin();
        font.draw(batch, text,xLocation,yLocation);
        batch.end();
    }
    public void dispose(){batch.dispose();}
}
