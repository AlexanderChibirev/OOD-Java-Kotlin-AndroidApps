package com.drop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Drop extends ApplicationAdapter implements InputProcessor {

	Stage stage;
	OrthographicCamera cam;
	SpriteBatch batch;
	OrthographicCamera camera;

	int WIDTH;
	int HEIGHT;

	Texture libgdxImg;
	Actor libgdxActor;

	float touchDown_x;
	float touchDown_y;
	float dx;
	float dy;

	Texture sImg;
	Actor sActor;

	float touchDown_x1;
	float touchDown_y1;
	float dx1;
	float dy1;




	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		stage = new Stage();

		libgdxImg = new Texture("bucket.png");
		libgdxActor = new Image(libgdxImg);
		libgdxActor.setOrigin(libgdxActor.getWidth()/2,libgdxActor.getHeight()/2);

		sImg = new Texture("bucket.png");
		sActor = new Image(sImg);
		sActor.setOrigin(sActor.getWidth()/2,sActor.getHeight()/2);
		sActor.setPosition(100,100);

		sActor.addListener(new InputListener()
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button )
			{
				sActor.addAction(Actions.scaleTo(1.5f,1.5f,0.3f, Interpolation.fade));
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button )
			{
				sActor.addAction(Actions.scaleTo(1.0f,1.0f,0.3f, Interpolation.fade));

			}
		});

		libgdxActor.addListener(new InputListener()
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button )
			{
				libgdxActor.addAction(Actions.scaleTo(1.5f,1.5f,0.3f, Interpolation.fade));
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button )
			{
				libgdxActor.addAction(Actions.scaleTo(1.0f,1.0f,0.3f, Interpolation.fade));

			}
		});


		final Group dragGroup = new Group();
		dragGroup.setWidth(libgdxImg.getWidth());
		dragGroup.setHeight(libgdxImg.getHeight());
		dragGroup.addActor(libgdxActor);

		final Group dragGroup1 = new Group();
		dragGroup1.setWidth(sActor.getWidth());
		dragGroup1.setHeight(sActor.getHeight());
		dragGroup1.addActor(sActor);


		dragGroup1.addListener(new InputListener()
		{

			final  float h = sActor.getHeight() / 2;

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				touchDown_x1 = x;
				touchDown_y1 = h - y;
				return  true;
			}

			public  void  touchDragged(InputEvent event, float x, float y, int pointer){
				dx1 = dragGroup1.getX() - touchDown_x1;
				dy1 =dragGroup1.getY() - h + touchDown_y1;
				dragGroup1.setPosition(x + dx1, y + dy1);
			}

			public  void touchUp (InputEvent event, float x, float y, int pointer, int button){
				dx1 = dragGroup1.getX() - touchDown_x1;
				dy1 = dragGroup1.getY() - h + touchDown_y1;
				dragGroup1.setPosition(x + dx1, y + dy1);
			}
		});

		dragGroup.addListener(new InputListener()
		{

			final  float h = libgdxImg.getHeight() / 2;

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				touchDown_x = x;
				touchDown_y = h - y;
				return  true;
			}

			public  void  touchDragged(InputEvent event, float x, float y, int pointer){
				dx = dragGroup.getX() - touchDown_x;
				dy =dragGroup.getY() - h + touchDown_y;
				dragGroup.setPosition(x + dx, y + dy);
			}

			public  void touchUp (InputEvent event, float x, float y, int pointer, int button){
				dx = dragGroup.getX() - touchDown_x;
				dy = dragGroup.getY() - h + touchDown_y;
				dragGroup.setPosition(x + dx, y + dy);
			}
		});


		stage.clear();
		stage.addActor(dragGroup);
		stage.addActor(dragGroup1);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render() {

		Gdx.gl.glViewport(0,0,WIDTH,HEIGHT);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0,0,0,1);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		//stage.getRoot().draw(batch,1);
		batch.flush();

		batch.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void resize (int width, int height) {
		WIDTH = width;
		HEIGHT = height;

		cam = new OrthographicCamera(width,height);
		cam.position.set(cam.viewportWidth / 2.0f , cam.viewportHeight/2.0f,0.0f);
		cam.update();

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
