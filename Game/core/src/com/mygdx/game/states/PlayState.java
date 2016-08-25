package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Alexander on 24.08.2016.
 */
public class PlayState extends State implements InputProcessor {
    private Texture bg;
    private Texture twentyScore;
    private Texture tenScore;
    private Texture fiftyScore;
    Stage stage;

    private Animation bucketAnimation;

    private Rectangle bucketTargetDropCoordinate = new Rectangle(30,40,20,20) ;

    static int allScore;
    private int oldScore = 0;

    private BitmapFont fontScore;
    private float secondTime = 60f;
    private int minTime = 1;
    private Array<DragAndDropActor> dragAndDropObjects;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        Texture texture = new Texture("bucketAll.png");
        Texture twentyScore = new Texture("twentyScore.png");
        Texture fiftyScore= new Texture("fiftyScore.png");
        Texture tenScore= new Texture("tenScore.png");
        dragAndDropObjects = new Array<DragAndDropActor>();
        bucketAnimation = new Animation(new TextureRegion(texture),4,0.5f);

        bg = new Texture("bgGame.jpg");
        stage = new Stage();
        addDragAndDropActorObjects();
        addActorForStage();

        Gdx.input.setInputProcessor( stage );
        initialFont();
    }

    private  void  addDragAndDropActorObjects()
    {
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage1.png", new Vector3(450,0,0), bucketTargetDropCoordinate, 20,true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage1.png", new Vector3(350,0,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage1.png", new Vector3(650,70,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage1.png", new Vector3(150,1,0), bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage1.png", new Vector3(200,34,0), bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage2.png", new Vector3(100,20,0), bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage3.png", new Vector3(300,70,0),bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage4.png", new Vector3(350,80,0), bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage4.png", new Vector3(650,50,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage4.png", new Vector3(690,30,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage4.png", new Vector3(350,20,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add( new DragAndDropActor("lumpOfGarbage4.png", new Vector3(120,260,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage2.png", new Vector3(520,70,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage3.png", new Vector3(620,10,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage5.png", new Vector3(720,70,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage5.png", new Vector3(160,230,0), bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage6.png", new Vector3(225,70,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage7.png", new Vector3(780,410,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage6.png", new Vector3(375,370,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("lumpOfGarbage7.png", new Vector3(430,200,0),  bucketTargetDropCoordinate, 20, true));
        dragAndDropObjects.add(new DragAndDropActor("alarmClock.png", new Vector3(30,235,0),  new Rectangle(278, 318,10,10), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("clock.png", new Vector3(330,100,0),  new Rectangle(290, 375,80,80), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("stool.png", new Vector3(625,70,0),  new Rectangle(125,70,20,20), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("flower.png", new Vector3(530,205,0),  new Rectangle(750,275,20,20), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("pillow.png", new Vector3(350,70,0),  new Rectangle(460,205,40,40), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("picture.png", new Vector3(260,100,0),  new Rectangle(760,215,10,10), 50, false));
        dragAndDropObjects.add(new DragAndDropActor("clip.png", new Vector3(0,0,0),  new Rectangle(25,183,30,30), 50, false));


    }

    private  void addActorForStage()
    {
        for(int i = 0; i < dragAndDropObjects.size; i = i + 1)
        {
            stage.addActor(dragAndDropObjects.get(i));
        }
    }

    private void initialFont()
    {
        fontScore = new BitmapFont();
        fontScore.setColor(Color.BLACK);
    }

    @Override
    protected void handleInput() {

    }

    public void setAnimation(Vector3 coordinate){

    }

    @Override
    public void update(float dt) {
        bucketAnimation.update(dt);
        if(secondTime < 1){
            secondTime = 59;
            minTime -= 1;
        }
        secondTime = secondTime - 0.1f ;//0.02 для норм, 1.5 для проверки

        if(minTime < 0 ){
            gsm.set(new GameOverState(gsm));// заменяем на сцену повтора
        }

        for(int i = 0; i < dragAndDropObjects.size; i = i + 1)
        {
           if(dragAndDropObjects.get(i).isDrop() == true)
           {

           }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0);
        //sb.draw(bucketAnimation.getFrame(), 0, 0); пустить анимацию
        if(Math.round(secondTime) < 10){
            fontScore.draw(sb, minTime+ ":0" + Math.round(secondTime), 155, 450);
        }
        else {
            fontScore.draw(sb, minTime+ ":" + Math.round(secondTime), 155, 450);
        }

        fontScore.draw(sb, "1", 280, 450);

        if(allScore >= 100) {
            fontScore.draw(sb, "" + allScore,375,450);
        }
        else {
            fontScore.draw(sb, "" + allScore,385,450);
        }
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
