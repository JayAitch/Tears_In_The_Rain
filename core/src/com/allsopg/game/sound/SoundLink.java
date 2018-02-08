package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Jordan Harrison on 05/02/2018.
 */

public class SoundLink {
    private IntMap<Sound> soundKeys;

    public enum SoundEnum{
        EXPLODESND, CRASHSND
    }
    public SoundLink(){
        // create array for sounds and store within an array
        soundKeys = new IntMap<Sound>();

        Sound explodeSnd = Gdx.audio.newSound(Gdx.files.internal("sfx/explode.ogg"));
        Sound crashSnd = Gdx.audio.newSound(Gdx.files.internal("sfx/crash.ogg"));

        soundKeys.put(1,crashSnd);
        soundKeys.put(2,explodeSnd);
    }

    public boolean play(SoundEnum soundEnum){
        //state machine to chose sound to play
        //enum driven for clarity when calling
        Sound sound;
        switch (soundEnum){
            case EXPLODESND:
                sound = soundKeys.get(2);
                break;
            case CRASHSND:
                sound = soundKeys.get(1);
                break;
            default:
                System.out.println("Sound enum not recognised");
                sound = soundKeys.get(1);
                break;
        }
       // pre enum method was driven using index param passed in Sound sound = soundKeys.get(keyCode);
        //check to see if sound has been stored
        if(sound != null){
            sound.play();
            return true;
        }
        return false;
    }
    //dispose sound array
    public void dispose(){
        for (Sound sound: soundKeys.values())
            sound.dispose();
    }
}
