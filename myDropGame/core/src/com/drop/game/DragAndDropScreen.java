package com.drop.game;

/**
 * Created by Alexander on 24.08.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DragAndDropScreen implements Screen, InputProcessor{

    SpaceGame game;
    Stage stage;
    OrthographicCamera cam;
    //SpriteBatch batch;

    Texture libgdxImg;
    Actor libgdxActor;

    float touchDown_x;
    float touchDown_y;
    float dx;
    float dy;
    float touchDown_x11;
    float touchDown_y11;

    Texture sImg;
    Actor sActor;

    float touchDown_x1;
    float touchDown_y1;
    float dx1;
    float dy1;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;



    public DragAndDropScreen(SpaceGame game)
    {
        backgroundTexture = new Texture("background.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
       // backgroundSprite.setSize(backgroundSprite.getWidth() + 500, backgroundSprite.getHeight());
        backgroundSprite.setScale(backgroundSprite.getScaleX() + 2222,backgroundSprite.getScaleY()+123123);
        this.game = game;
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

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) // когда берешь
            {
                touchDown_x1 = x;
                touchDown_y1 = h - y;
                //touchDown_x11 = dragGroup1.getX(); для того чтобы остался на месте запоминаем откуда взяли
                //touchDown_y11 =  dragGroup1.getY();

                return  true;
            }

            public  void  touchDragged(InputEvent event, float x, float y, int pointer){// во время перетаскивания
                dx1 = dragGroup1.getX() - touchDown_x1;
                dy1 =dragGroup1.getY() - h + touchDown_y1;
                dragGroup1.setPosition(x + dx1, y + dy1);
            }

            public  void touchUp (InputEvent event, float x, float y, int pointer, int button){//когда отпускаешь
                dx1 = dragGroup1.getX() - touchDown_x1;
                dy1 = dragGroup1.getY() - h + touchDown_y1;
                dragGroup1.setPosition(x + dx1, y + dy1);
                //dragGroup1.setPosition(touchDown_x11,touchDown_y11); возвращаемся на месте откуда взяли
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,1);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        //stage.getRoot().draw(batch,1);
        game.batch.flush();
        game.batch.draw(backgroundSprite,0,0);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        cam = new OrthographicCamera(width,height);
        cam.position.set(cam.viewportWidth / 2.0f , cam.viewportHeight/2.0f,0.0f);
        cam.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
