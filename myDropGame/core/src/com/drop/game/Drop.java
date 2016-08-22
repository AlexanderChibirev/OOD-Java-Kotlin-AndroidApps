package com.drop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Drop extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture dropImage;
	Texture bucketImage;
	Sound dropSound;
	Music rainMusic;
	Rectangle bucket;


	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		dropImage = new Texture("droplet.png");
		bucketImage = new Texture("bucket.png");

		dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("undertreeinrain.mp3"));

		rainMusic.setLooping(true);
		rainMusic.play();

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
