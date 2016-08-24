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
    DragAndDropActor garbage8;
    DragAndDropActor garbage9;
    DragAndDropActor garbage10;
    DragAndDropActor garbage11;
    DragAndDropActor garbage12;
    DragAndDropActor garbage13;
    DragAndDropActor garbage14;
    DragAndDropActor garbage15;
    DragAndDropActor garbage16;




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
        stage.addActor(garbage6);
        stage.addActor(garbage7);
        stage.addActor(garbage8);
        stage.addActor(garbage9);
        stage.addActor(garbage10);
        stage.addActor(garbage11);
        stage.addActor(garbage12);
        stage.addActor(garbage13);
        stage.addActor(garbage14);
        stage.addActor(garbage15);
        stage.addActor(garbage16);
    }

    private  void initialDragAndDropActorObject()
    {
        garbage1 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(450,0,0), new Rectangle(30,40,20,20), 20, true);// Rectangle(20,30,20,20) для ведра
        garbage2 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(350,0,0),  new Rectangle(30,40,20,20), 20, true);
        garbage3 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(650,70,0),  new Rectangle(30,40,20,20), 20, true);
        garbage4 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(150,50,0), new Rectangle(30,40,20,20), 20, true);
        garbage5 = new DragAndDropActor("lumpOfGarbage1.png", new Vector3(200,70,0),  new Rectangle(30,40,20,20), 20, true);
        garbage6 = new DragAndDropActor("lumpOfGarbage2.png", new Vector3(100,20,0),  new Rectangle(30,40,20,20), 20, true);
        garbage7 = new DragAndDropActor("lumpOfGarbage3.png", new Vector3(300,70,0), new Rectangle(30,40,20,20), 20, true);
        garbage8 = new DragAndDropActor("lumpOfGarbage4.png", new Vector3(350,80,0),  new Rectangle(30,40,20,20), 20, true);
        garbage9 = new DragAndDropActor("lumpOfGarbage4.png", new Vector3(650,50,0),  new Rectangle(30,40,20,20), 20, true);//под стулом
        garbage10 = new DragAndDropActor("lumpOfGarbage4.png", new Vector3(690,30,0),  new Rectangle(30,40,20,20), 20, true);//чуть ниже 9ого
        garbage11 = new DragAndDropActor("lumpOfGarbage4.png", new Vector3(350,20,0),  new Rectangle(30,40,20,20), 20, true);
        garbage12 = new DragAndDropActor("lumpOfGarbage4.png", new Vector3(120,70,0),  new Rectangle(30,40,20,20), 20, true);

        garbage13 = new DragAndDropActor("lumpOfGarbage2.png", new Vector3(520,70,0),  new Rectangle(30,40,20,20), 20, true);
        garbage14 = new DragAndDropActor("lumpOfGarbage3.png", new Vector3(620,10,0),  new Rectangle(30,40,20,20), 20, true);
        garbage15 = new DragAndDropActor("lumpOfGarbage5.png", new Vector3(720,70,0),  new Rectangle(30,40,20,20), 20, true);
        garbage16 = new DragAndDropActor("lumpOfGarbage5.png", new Vector3(620,70,0),  new Rectangle(30,40,20,20), 20, true);


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
