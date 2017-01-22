package com.example.alexander.shapespainter;

import android.view.MotionEvent;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;

import javax.vecmath.Vector2f;

public class Designer implements IDesigner {

    @Override
    public PictureDraft createDraft() {
        PictureDraft pictureDraft = new PictureDraft();
        ShapeFactory shapeFactory = new ShapeFactory();
        createBaseIcon(pictureDraft, shapeFactory);
        return pictureDraft;
    }

    private void createBaseIcon(PictureDraft pictureDraft, ShapeFactory shapeFactory) {
        Shape shape = shapeFactory.createShape(new Vector2f(150,45), 26, 26, ShapeType.Ellipse);
        pictureDraft.addShape(shape);
        shape = shapeFactory.createShape(new Vector2f(220, 20), 80, 50, ShapeType.Rectangle);
        pictureDraft.addShape(shape);
        shape = shapeFactory.createShape(new Vector2f(60, 45), 80, 50, ShapeType.Triangle);
        pictureDraft.addShape(shape);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
