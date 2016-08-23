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


		//stage.clear();

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



/*	Stage stage;
	SpriteBatch batch;
	Image sourceImage;
	Image validTargetImage;
	Image invalidTargetImage;
	//Texture sourceImage;// = new Texture(Gdx.files.internal("image.png"));
	private Sprite sourceSprite;

	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		final Skin skin = new Skin();
		skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("badlogic", new Texture("badlogic.jpg"));

		//sourceImage = new Texture(Gdx.files.internal("bucket.png"));
		//sourceSprite = new Sprite(sourceImage);

		sourceImage = new Image(skin, "badlogic");
		sourceImage.setBounds(50, 125, 100, 100);
		stage.addActor(sourceImage);

		validTargetImage = new Image(skin, "badlogic");
		validTargetImage.setBounds(200, 50, 100, 100);
		stage.addActor(validTargetImage);

		invalidTargetImage = new Image(skin, "badlogic");
		invalidTargetImage.setBounds(200, 200, 100, 100);
		stage.addActor(invalidTargetImage);

		DragAndDrop dragAndDrop = new DragAndDrop();

		dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {

			@Override
			public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
				DragAndDrop.Payload payload = new DragAndDrop.Payload();
				payload.setObject(sourceImage);
				payload.setDragActor(sourceImage);
				sourceImage.setSize(120, 120);//увеличиваем размер перемщаемой картинки


				*//*
				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = new Label("Some payload!", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);*//*
				//getActor().setBounds(x,y,100,100);
				//validTargetImage.setBounds(100, 200, 100, 100); //когда перетаскиваем
				return payload;
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
				super.dragStop(event, x, y, pointer, payload, target);
				sourceImage.setBounds(getActor().getX(),getActor().getY(), 100, 100);//увеличиваем размер перемщаемой картинки
				//sourceImage.setOrigin(sourceImage.getOriginX()/2, sourceImage.getOriginY()/ 2);
				stage.addActor(sourceImage);
			}
		});


		dragAndDrop.addTarget(new Target(validTargetImage) {// пришел к второй картинке
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				//getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				//getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				//System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
			}
		});
		dragAndDrop.addTarget(new Target(invalidTargetImage) {// пришел к второй картинке
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				//getActor().setColor(Color.RED);
				return false;
			}

			public void reset (Source source, Payload payload) {
				//getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
			}
		});
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
	}
	*/

}
