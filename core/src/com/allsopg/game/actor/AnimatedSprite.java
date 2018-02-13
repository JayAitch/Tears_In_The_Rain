package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;


/**
 * Created by gja10 on 13/11/2017.
 */

public class AnimatedSprite extends Sprite {
    private Animation animation;
    private TextureAtlas atlas;

//create and sort texture regions for animating
    public AnimatedSprite(String atlasString, Texture t, Animation.PlayMode loopType) {
        super(t);
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        Array<TextureAtlas.AtlasRegion> regions = new
                Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(Constants.FRAME_DURATION, regions, loopType);
    }
//default constructor to allow extention for multiple regions
    public AnimatedSprite(Texture t) {
        super(t);
    }
//moved from constructor to allow animations to be switched from decrived class
    protected void animationInit(Array<TextureAtlas.AtlasRegion> regions, Animation.PlayMode loopType) {
        regions.sort(new RegionComparator());
        animation = new Animation(Constants.FRAME_DURATION, regions, loopType);

    }

//update to animated between textures
    public void update(float deltaTime) {
        this.setRegion((TextureAtlas.AtlasRegion) animation.getKeyFrame(deltaTime));
    }
//region comparator inner class for sorting
    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion> {

        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }
}
