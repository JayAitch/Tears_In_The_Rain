package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenData;

import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Jordan Harrison on 06/01/2018.
 */

public class CarSprite extends BonusSprite {

    private Array<TextureAtlas.AtlasRegion> regions;
    private Array<TextureAtlas.AtlasRegion> idleRegion;
    private Array<TextureAtlas.AtlasRegion> deathRegion;
    private float StateTimer;
    private Boolean dead;
    public CarSprite(Texture t) {
        super(t);
        // String atlasLocation = "gfx/MobCar/mob_car.atlas";
        Vector2 position = new Vector2(5, randomY());
        //   Animation.PlayMode okayMode = Animation.PlayMode.LOOP;


        //  TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasLocation));

        // regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        // idleRegion = new Array<TextureAtlas.AtlasRegion>();

//        for (int i = 0; i < 3; i++) {
//            idleRegion.add(regions.pop());
//
//        }

        //  animationInit(idleRegion, okayMode);
        createAnimArrays();
        this.setPosition(position.x, position.y);
        initTweenData();
    }



    public void createAnimArrays(){
        String atlasLocation = "gfx/MobCar/mob_car.atlas";
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasLocation));

        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        idleRegion = new Array<TextureAtlas.AtlasRegion>();
        deathRegion = new Array<TextureAtlas.AtlasRegion>();
        for (int i = 0; i < 3; i++) {
            idleRegion.add(regions.pop());

        }
        for(int i = 0; i < 12; i++){
            deathRegion.add(regions.pop());
        }
        animationInit(idleRegion, Animation.PlayMode.LOOP);


    }


    @Override
    public void update(float deltaTime) {
        StateTimer += Gdx.graphics.getDeltaTime();
        super.update(StateTimer);
        this.setX(tweenData.getXY().x);
        this.setY(tweenData.getXY().y);
        this.setColor(tweenData.getColour());
        this.setScale(tweenData.getScale());
        this.setRotation(tweenData.getRotation());
    }



    public void destroyRoutine() {
        float crashTimer = 300f;
        //seperate crash and movement for game
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, crashTimer)
                .target(300, tweenData.getXY().y).start(tweenManager)

                //faid
                .to(tweenData,TweenDataAccessor.TYPE_COLOUR,0.5f).delay(800f)
                .target(.15f,.15f,.15f,.0f)
                .start(tweenManager)

                //crash rotations
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION,50f).delay(crashTimer)
                .target(120).start().start(tweenManager)

                .to(tweenData, TweenDataAccessor.TYPE_ROTATION,350f).delay(crashTimer+ 170)
                .target(330).start().start(tweenManager)

                //crash scale
                .to(tweenData, TweenDataAccessor.TYPE_SCALE,100f).delay(crashTimer + 30)
                .target(1.5f).start().start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_SCALE,300f).delay(crashTimer + 170)
                .target(7f).start().start(tweenManager)
                //crash movement
                .to(tweenData, TweenDataAccessor.TYPE_POS, 100f).delay(crashTimer)
                .target(200, tweenData.getXY().y+300).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS, 100f).delay(crashTimer + 100f)
                .target(200, tweenData.getXY().y+200).start(tweenManager);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                changeAnimation();
            }
        },crashTimer/100);
    }

//    public void faidRoutine(){
//        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 250f)
//    }



    public void changeAnimation(){
        animationInit(deathRegion, Animation.PlayMode.NORMAL);
        StateTimer =0;
    }

    private float randomY(){
        //return MathUtils.random(Constants.SCENE_HEIGHT, 0);
        return 5;
    }
}
