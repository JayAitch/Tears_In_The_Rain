package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenData;
import com.allsopg.game.utility.TweenDataAccessor;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by gja on 13/11/2017.
 * Updated by gja on 19/10/2017
 */

public class BonusSprite extends AnimatedSprite {
    protected TweenData tweenData;
    protected TweenManager tweenManager;
    protected float StateTime;

    public BonusSprite(String atlasString, Texture t, Vector2 pos, Animation.PlayMode loopType) {
        super(atlasString, t, loopType);
        this.setPosition(pos.x, pos.y);
        initTweenData();
    }

    public BonusSprite(Texture t) {
        super(t);
    }

    protected void initTweenData() {
        tweenData = new TweenData();
        tweenData.setXY(this.getX(), this.getY());
        tweenData.setColour(this.getColor());
        tweenData.setScale(this.getScaleX());
        tweenManager = UniversalResource.getInstance().tweenManager; //tweenManager;
    }



    private TweenData getTweenData() {
        return tweenData;
    }


    @Override
    public void update(float stateTime) {
        super.update(stateTime);
        this.setX(tweenData.getXY().x);
        this.setY(tweenData.getXY().y);
        this.setColor(tweenData.getColour());
        this.setScale(tweenData.getScale());
        this.setRotation(tweenData.getRotation());
    }

//    public void destroyRoutine() {
//        System.out.print(Constants.VIEWPORT_WIDTH);
//        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 250f)
//                .target(Constants.SCENE_WIDTH-100, tweenData.getXY().y).start(tweenManager);
//
//
//    }
}
