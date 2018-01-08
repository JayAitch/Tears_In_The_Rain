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
    public void destroyRoutine() {

        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 25f)
                .target(300, tweenData.getXY().y).start(tweenManager)
                .to(tweenData,TweenDataAccessor.TYPE_COLOUR,200f)
                .target(.15f,.15f,.15f,.0f)
                .start(tweenManager);


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                changeAnimation();
            }
        },0.2f);
    }
    public void changeAnimation(){
        animationInit(deathRegion, Animation.PlayMode.NORMAL);
        super.update(0);
    }

    private float randomY(){
      //  return MathUtils.random(Constants.SCENE_HEIGHT, 0);
        return 5;
    }
}
