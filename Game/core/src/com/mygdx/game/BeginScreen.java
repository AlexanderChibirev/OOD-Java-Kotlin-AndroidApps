package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BeginScreen extends ApplicationAdapter {
//1280 x 720
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "ChildrenGame";

	private com.mygdx.game.states.GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new com.mygdx.game.states.GameStateManager();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new com.mygdx.game.states.MenuState(gsm));               //For androids


	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}

