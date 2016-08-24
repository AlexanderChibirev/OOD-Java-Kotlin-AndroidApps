package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    float dx;
    float dy;
    Rectangle rectDrag;
    Texture garbageT;
    Image garbageActor;

    public DragAndDropActor(final String ingName, final Vector3 initialPosition, final Rectangle rect, final int scoreSuccess, boolean isDisappears) {


        garbageT = new Texture(Gdx.files.internal(ingName));
        garbageActor = new Image(garbageT);
        dragActor = garbageActor;
        dragActor.setOrigin( garbageActor.getWidth() / 2, garbageActor.getHeight() / 2 );

        dragActor.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                dragActor.addAction(Actions.parallel(
                        Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.fade),
                        Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.5f), 0.45f, Interpolation.fade) )
                );
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dragActor.addAction(Actions.parallel(
                        Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.fade),
                        Actions.color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0.45f, Interpolation.fade) )
                );
            }
        });


        dragGroup = new Group();
       // dragActor.setWidth( actor.getWidth() );
        //dragActor.setHeight( actor.getHeight() );

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
                // узнаем куда переместились
                dx = dragGroup.getX() - touchDown_x;
                dy = dragGroup.getY() - h + touchDown_y;
                //
                //создаем прямоугольник
                //и если он пересекся с картинкой, то мы удаляем картинку и добавляем очки
                rectDrag.setPosition(x + dx, y + dy);
                if(rect.overlaps(rectDrag)){ //если поставили на нужное место
                    dragGroup.remove();
                    com.mygdx.game.states.PlayState.allScore+= scoreSuccess;
                }
                com.mygdx.game.states.PlayState.allScore+= -10;
                dragGroup.setPosition(initialPosition.x , initialPosition.y);
            }
        });

        this.addActor(dragGroup);
    }

    void dispose() {
        dragGroup.clear();
        dragActor = null;
        dragGroup = null;
        garbageT.dispose();
    }
}
