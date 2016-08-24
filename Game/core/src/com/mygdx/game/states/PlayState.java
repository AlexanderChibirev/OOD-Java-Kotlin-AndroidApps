package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Alexander on 24.08.2016.
 */
public class PlayState extends State implements InputProcessor {
    private Texture bg;
    Stage stage;

    DragAndDropActor garbage1;
    DragAndDropActor garbage2;
    DragAndDropActor garbage3;
    DragAndDropActor garbage4;
    DragAndDropActor garbage5;
    DragAndDropActor garbage6;
    DragAndDropActor garbage7;


    static int allScore;

    BitmapFont fontScore;
    BitmapFont fontTime;
    BitmapFont fontLevel;
    private float secondTime = 60f;
    private int minTime = 1;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bgGame.jpg");
        stage = new Stage();

        initialDragAndDropActorObject();
        addActorForStage();

        Gdx.input.setInputProcessor( stage );
        initialFont();
    }
    private  void addActorForStage()
    {
        stage.addActor(garbage1);//добавление объектов
        stage.addActor(garbage2);
        stage.addActor(garbage3);
        stage.addActor(garbage4);
        stage.addActor(garbage5);
    //    stage.addActor(garbage6);
    //    stage.addActor(garbage7);
        //stage.addActor(garbage8);
    }

    private  void initialDragAndDropActorObject()
    {
        garbage1 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(450,0,0), new Vector3 (50,50,0), new Rectangle(30,40,20,20), 20, true);// Rectangle(20,30,20,20) для ведра
        garbage2 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(350,0,0), new Vector3 (50,50,0), new Rectangle(30,40,20,20), 20, true);
        garbage3 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(650,70,0), new Vector3 (50,50,0), new Rectangle(30,40,20,20), 20, true);
        garbage4 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(150,50,0), new Vector3 (50,50,0), new Rectangle(30,40,20,20), 20, true);
        garbage5 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(200,70,0), new Vector3 (50,50,0), new Rectangle(30,40,20,20), 20, true);
    }

    private void initialFont()
    {
        fontScore = new BitmapFont();
        fontScore.setColor(Color.BLACK);

        fontTime = new BitmapFont();
        fontTime.setColor(Color.BLACK);

        fontLevel = new BitmapFont();
        fontLevel.setColor(Color.BLACK);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if(secondTime < 1){
            secondTime = 60;
            minTime -= 1;
        }
        secondTime = secondTime - 0.02f ;//0.02 для норм, 0.5 для проверки

        if(minTime < 0 ){
            gsm.set(new MenuState(gsm));// заменяем на сцену повтора
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0);
        fontScore.draw(sb, minTime+ ":" + Math.round(secondTime), 155, 450);
        fontLevel.draw(sb, "1", 280, 450);
        fontScore.draw(sb, "" + allScore,370,450);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
