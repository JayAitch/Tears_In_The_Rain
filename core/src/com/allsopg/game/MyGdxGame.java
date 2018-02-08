package com.allsopg.game;

import com.allsopg.game.actor.AnimatedSprite;
import com.allsopg.game.actor.BonusSprite;
import com.allsopg.game.actor.CarSprite;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private CarSprite bp;
    private float animationTime;

	@Override
	public void create () {
		//populate camera, view and batch references
		camera = new OrthographicCamera();
		view = new FitViewport(800,600,camera);
		batch = new SpriteBatch();
		//assign sizing textures from files
		Texture small = new Texture(Gdx.files.internal("gfx/smallSize.png"));
		Texture medium = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
		Texture carSize = new Texture(Gdx.files.internal("gfx/carSize.png"));
		//create new carsprite and run routine
        bp = new CarSprite(carSize);
        bp.destroyRoutine();

	}
	@Override
	public void render () {
		//setting up clear colour and batch render
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime +=Gdx.graphics.getDeltaTime();
        UniversalResource.getInstance().tweenManager.update(animationTime);
        batch.begin();
	    bp.update(animationTime);
        bp.draw(batch);
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
