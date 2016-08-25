package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BeginScreen;

/**
 * Created by Alexander on 24.08.2016.
 */
public class PlayState extends State implements InputProcessor {
    Music music;
    Stage stage;
    private Texture bg;
    private Texture twentyScore;
    private Texture tenScore;
    private Texture fiftyScore;
    private Texture bucketUp;
    private Texture bucketAll;
    private int countObjects = 0;//27
    private boolean isScore = false;
    private float timeScore = 10000;
    private float dxSorce = 0;
    private  float dySorce = 0;
    private Animation bucketAnimation;
    private int scoreOb = 0;
    private Rectangle bucketTargetDropCoordinate = new Rectangle(30,40,20,20) ;
    static int allScore;
    private Vector3 coodinateScore = new Vector3();
    private BitmapFont fontScore;
    private float secondTime = 60f;
    private int minTime = 1;
    private Array<DragAndDropActor> dragAndDropObjects;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        music = Gdx.audio.newMusic(Gdx.files.internal("music//GameProcess.mp3"));
        music.setLooping(true);
        //music.setVolume(0.1f);
        music.play();

        bucketUp = new Texture("bucketUp.png");
        bucketAll = new Texture("bucketAll.png");
        twentyScore = new Texture("twentyScore.png");
        fiftyScore= new Texture("fiftyScore.png");
        tenScore= new Texture("tenScore.png");
        bg = new Texture("bgGame.jpg");

        dragAndDropObjects = new Array<DragAndDropActor>();
        bucketAnimation = new Animation(new TextureRegion(bucketAll),10,0.5f);


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
        dragAndDropObjects.add(new DragAndDropActor("stool.png", new Vector3(625,70,0),  new Rectangle(125,70,50,50), 50, false));
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

    private void updateForTimer() {
        float speed = 0.02f;//0.02 для норм, 1.5 для проверки
        if(secondTime < 1){
            secondTime = 59;
            minTime -= 1;
        }
        secondTime = secondTime - speed ;
        if(minTime < 0 ){
            gsm.set(new GameOverState(gsm));// заменяем на сцену повтора
            music.stop();
        }
    }
    public  void updateForAnimScore() {
        if(isScore ){
            dxSorce += 0.1;
            dySorce += 1.5;
            timeScore += 1;
        }
        if(timeScore > 20)
        {
            timeScore = 0;
            isScore = false;
        }
    }
    @Override
    public void update(float dt) {
        bucketAnimation.update(dt);
        updateForAnimScore();
        //////////////////////////////////
        if(countObjects == dragAndDropObjects.size ){
            music.stop();
            gsm.push(new com.mygdx.game.states.WinState(gsm));
        }
        updateForTimer();
    }
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, BeginScreen.WIDTH, BeginScreen.HEIGHT);
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
        if(!isScore || scoreOb == -10 || scoreOb == 50 ){ sb.draw(bucketUp, 0, 0);}
        sb.end();
        stage.act();
        stage.draw();
        sb.begin();
        if(isScore){
            if(scoreOb == 20){
                sb.draw(bucketAnimation.getFrame(), 0, 0);
                sb.draw(twentyScore, dxSorce, dySorce);
            }
            else  if(scoreOb == 50){
                sb.draw(fiftyScore, dxSorce, dySorce);

            }
            else {
                sb.draw(tenScore, dxSorce, dySorce);
            }
        }
        else {
            scoreOb = -10;
        }

        for(int i = 0; i < dragAndDropObjects.size; i = i + 1)
        {
            DragAndDropActor objects = dragAndDropObjects.get(i);
            if(objects.isFailDrop())
            {
                coodinateScore = objects.getInitialPosition();
                setDataForScore(objects);
                objects.setFailDrop(false);

            }
            if(objects.isDrop())
            {
                countObjects++;
                objects.setDrop(false);
                coodinateScore = objects.getDropCoordinate();
                setDataForScore(objects);
                if (objects.getScore() == 20 )
                {
                    scoreOb = 20;
                }
                if (objects.getScore() == 50 )
                {
                    scoreOb = 50;
                }
            }
        }
        sb.end();
    }

    private void setDataForScore(DragAndDropActor objects)
    {
        dxSorce = coodinateScore.x;
        dySorce = coodinateScore.y;
        isScore = true;
    }

    @Override
    public void dispose() {
        stage.dispose();
        music.dispose();
        bg.dispose();
        twentyScore.dispose();
        tenScore.dispose();
        fiftyScore.dispose();
        bucketUp.dispose();
        bucketAll.dispose();
        fontScore.dispose();
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
