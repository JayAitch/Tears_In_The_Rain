package com.allsopg.game;


import com.allsopg.game.actor.MultiRegionSprite;
import com.allsopg.game.font.FontDrawer;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private MultiRegionSprite multiRegionSprite;
    private float animationTime;
	private FontDrawer fontDrawer;
	private Boolean isDead = false;
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
		int[] regionLengths= {4,13};
        multiRegionSprite = new MultiRegionSprite(carSize, "gfx/MobCar/mob_car.atlas",regionLengths);
        multiRegionSprite.destroyRoutine();
		fontTimer();

		fontDrawer = new FontDrawer("-1HP");

	}
	@Override
	public void render () {
		//setting up clear colour and batch render
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime +=Gdx.graphics.getDeltaTime();
        UniversalResource.getInstance().tweenManager.update(animationTime);
        batch.begin();
		if(isDead) {
			fontDrawer.drawText(batch, multiRegionSprite.getY() + multiRegionSprite.getHeight(), multiRegionSprite.getX());
		}

	    multiRegionSprite.update(animationTime);
        multiRegionSprite.draw(batch);
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
	public void fontTimer(){
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				isDead = true;
			}
		},3.2f);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				isDead = false;
			}
		},4.2f);
	}
}

