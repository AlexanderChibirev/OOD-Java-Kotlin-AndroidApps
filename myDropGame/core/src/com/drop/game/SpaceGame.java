package com.drop.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends Game  {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;

	public SpriteBatch batch;
	public BackgroundActor background;


	@Override
	public void create() {
		batch = new SpriteBatch();
		//background = new BackgroundActor();
		//background.setPosition(0, 0);
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void dispose()
	{
		super.dispose();
		batch.dispose();
	}

}
