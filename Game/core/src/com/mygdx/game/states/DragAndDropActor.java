package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DragAndDropActor extends Group {

    Actor dragActor;
    Group dragGroup;
    float touchDown_x;
    float touchDown_y;
    Sound badScoreSound;
    Sound successScoreSound;
    private boolean isDrop = false;
    private boolean failDrop = false;

    float dx;
    float dy;
    Rectangle rectDrag;
    Texture garbageTexture;
    Image garbageActor;
    Image targetDragActor;
    Texture targetDragTexture;
    Actor DragActorBegin;

    private int score = 0;
    private Vector3 coordinateDrop = new Vector3();
    private Vector3 m_initialPosition = new Vector3();
    public boolean isDrop(){
        return isDrop;
    }

    public void  setDrop(boolean drop){
        isDrop = drop;
    }

    public boolean isFailDrop(){
        return  failDrop;
    }
    public void setFailDrop(boolean drop){
        failDrop = drop;
    }

    public int getScore()
    {
        return score;
    }

    public Vector3 getDropCoordinate(){return coordinateDrop;}
    public Vector3 getInitialPosition(){return m_initialPosition ;}

    public DragAndDropActor(final String imgName, final Vector3 initialPosition, final Rectangle rect, final int scoreSuccess,final boolean isDisappears) {
        badScoreSound  = Gdx.audio.newSound(Gdx.files.internal("music//badScore.wav"));
        successScoreSound = Gdx.audio.newSound(Gdx.files.internal("music//coin.wav"));
        score = scoreSuccess;
        m_initialPosition = initialPosition;
        targetDragTexture = new Texture(Gdx.files.internal(imgName));
        targetDragActor = new Image(targetDragTexture);
        DragActorBegin = targetDragActor;
        DragActorBegin.setOrigin( targetDragActor.getWidth() / 2, targetDragActor.getHeight() / 2 );

        garbageTexture = new Texture(Gdx.files.internal(imgName));
        garbageActor = new Image(garbageTexture);
        dragActor = garbageActor;
        dragActor.setOrigin( garbageActor.getWidth() / 2, garbageActor.getHeight() / 2 );


        dragActor.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                dragActor.addAction(Actions.parallel(
                        Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.swing)//fade выцветание
                ));
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dragActor.addAction(Actions.parallel(
                        Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.swing)

                ));
            }
        });

        dragGroup = new Group();

        dragGroup.setPosition(initialPosition.x, initialPosition.y);
        dragGroup.setSize(dragActor.getWidth(),dragActor.getHeight());
        dragGroup.addActor( dragActor );
        rectDrag = new Rectangle(-190, -190, dragGroup.getWidth(), dragGroup.getHeight());// это кординаты картинки и ее размеры

        dragGroup.addListener(new InputListener() {

            final float h = dragActor.getHeight() / 2;

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {// когда берешь
                touchDown_x = x;
                touchDown_y = h - y;
                return true;
            }
            public void touchDragged(InputEvent event, float x, float y, int pointer) {// во время перетаскивания
                dx = dragGroup.getX() - touchDown_x;
                dy = dragGroup.getY() - h + touchDown_y;
                dragGroup.setPosition(x + dx, y + dy);
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {//когда отпускаешь
                dx = dragGroup.getX() - touchDown_x;
                dy = dragGroup.getY() - h + touchDown_y;
                coordinateDrop.set(x + dx, y + dy + dragGroup.getHeight(), 0) ;
                rectDrag.setPosition(x + dx, y + dy);
                if(isDisappears == true)// Исчезает если
                {
                    if(rect.overlaps(rectDrag)){ //если поставили на нужное место
                        dragGroup.remove();
                        com.mygdx.game.states.PlayState.allScore += scoreSuccess;// + прибавляем очки
                        isDrop = true;
                        successScoreSound.play();
                    }
                    else {
                        com.mygdx.game.states.PlayState.allScore += -10;
                        dragGroup.setPosition(initialPosition.x, initialPosition.y);
                        failDrop = true;
                        badScoreSound.play();
                    }
                }
                else
                {
                    //System.out.println("Enter a number: "+ dx + "    "+ dy);
                    if(rect.overlaps(rectDrag)) { //если поставили на нужное место
                        com.mygdx.game.states.PlayState.allScore += scoreSuccess;
                        dragGroup.clear();
                        dragGroup.setPosition(rect.x, rect.y);
                        dragGroup.setSize(DragActorBegin.getWidth(),DragActorBegin.getHeight());
                        dragGroup.addActor(DragActorBegin);
                        isDrop = true;
                        successScoreSound.play();
                    }
                    else {
                        dragGroup.setPosition(initialPosition.x , initialPosition.y);
                        com.mygdx.game.states.PlayState.allScore -= 10;
                        failDrop = true;
                        badScoreSound.play();
                       // badScoreSound.play();
                    }
                }
            }
        });

        this.addActor(dragGroup);
    }

    void dispose() {
        dragGroup.clear();
        dragActor = null;
        dragGroup = null;
        garbageTexture.dispose();
    }
}
