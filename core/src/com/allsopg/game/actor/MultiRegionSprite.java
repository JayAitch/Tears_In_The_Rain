package com.allsopg.game.actor;

import com.allsopg.game.font.FontDrawer;
import com.allsopg.game.sound.SoundLink;

import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.HashMap;
import java.util.Map;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

/**
 * most derived version of animated sprite this class was created to allow multiple regions from a single
 * texture atlas.
 * Created by Jordan Harrison on 06/01/2018.
 */

public class MultiRegionSprite extends BonusSprite {
//regions contains all the regions and idle and death regions will be populated via regions
    private Array<TextureAtlas.AtlasRegion> regions;
    private Map<Integer,Array<TextureAtlas.AtlasRegion>> animationRegions;
    //state timer to control animation position
    private float StateTimer;
    private SoundLink soundLink;
    private int[] arraytest;
    //MultiRegionSprite constructor super(t) is called for sizing via textures
    public MultiRegionSprite(Texture t, String atlasString,int[] regionLengths) {
        super(t);
        Vector2 position = new Vector2(5, randomY());
        createAnimArrays(atlasString,regionLengths);
        this.setPosition(position.x, position.y);
        initTweenData();
        //creating new soundlink
        soundLink = new SoundLink();
    }
//private Array<TextureAtlas.AtlasRegion> tempRegion;
//generating two seperate region from the initial region containing the whole atlas
    //in order to have 2 animations to switch between
    public void createAnimArrays(String atlasString, int[]regionLengths){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animationRegions = new HashMap<Integer, Array<TextureAtlas.AtlasRegion>>();

        for(int i = 0; i < regionLengths.length; i++) {
            Array<TextureAtlas.AtlasRegion> tempRegion = new Array<TextureAtlas.AtlasRegion>();
          for(int i2 = 0; i2 < regionLengths[i]; i2++) {
              tempRegion.add(regions.pop());

           }
            animationRegions.put(i,tempRegion);
        }


//        for (int i = 0; i < 3; i++) {
//            idleRegion.add(regions.pop());
//        }
//        for(int i = 0; i < 12; i++){
//            deathRegion.add(regions.pop());
//        }
//        animationRegions.put(0,idleRegion);
//        animationRegions.put(1,deathRegion);
        System.out.println("outside of loop animation regions get:"+ animationRegions.get(0).size);
        animationInit(animationRegions.get(0), Animation.PlayMode.LOOP);
    }

    //overriding the initial update method
    // to reset the animation timer to the start
    //when the animation is switched over
    @Override
    public void update(float deltaTime) {
        StateTimer += Gdx.graphics.getDeltaTime();
        super.update(StateTimer);
    }


//tween routine
    public void destroyRoutine() {
        float crashTimer = 300f;
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, crashTimer)
                .target(300, tweenData.getXY().y).start(tweenManager)

                //faid
                .to(tweenData,TweenDataAccessor.TYPE_COLOUR,0.5f).delay(800f)
                .target(.15f,.15f,.15f,.0f)
                .start(tweenManager)

                //crash rotations
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION,50f)
                .setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                soundLink.play(SoundLink.SoundEnum.CRASHSND);
            }
        })
                .delay(crashTimer)
                .target(120).start().start(tweenManager)

                .to(tweenData, TweenDataAccessor.TYPE_ROTATION,350f).delay(crashTimer+ 170)
                .target(330).start().start(tweenManager)

                //crash scale
                .to(tweenData, TweenDataAccessor.TYPE_SCALE,100f).delay(crashTimer + 30)
                .target(1.5f).start().start(tweenManager)

               //crash movement
                .to(tweenData, TweenDataAccessor.TYPE_POS, 100f).delay(crashTimer)
                .setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                changeAnimation();
                soundLink.play(SoundLink.SoundEnum.EXPLODESND);
                            }
        })
                .target(200, tweenData.getXY().y+300).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS, 100f).delay(crashTimer + 100f)
                .target(200, tweenData.getXY().y+200).start(tweenManager);

    }




//change the animation and set the StateTimer to 0 to play from the beginging
    // i am changing the playmode to prevent the death animation looping
    // i aim to extend this method to accept a region to change to allowing switching back and fourth
    public void changeAnimation(){
        animationInit(animationRegions.get(1), Animation.PlayMode.NORMAL);
        StateTimer =0;
    }
// this method could be used to spawn the cars in different places
    private float randomY(){
        //return MathUtils.random(Constants.SCENE_HEIGHT, Constants.SCENE_HEIGHT/2);
        return 5;
    }
}
