package com.example.alexander.shapespainter.controller.commands;

import com.example.alexander.shapespainter.PictureDraft;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;

import javax.vecmath.Vector2f;


public class Designer implements IDesigner {

/*  TODO:: перенести в отдельный класс констант, сделать статическими, но не публичными
    private static final Vector2f SHAPE_MAX_SIZE = new Vector2f(200f, 200f);
    private static final Vector2f SHAPE_MIN_SIZE = new Vector2f(25f, 25f);
    private static final float SHAPE_DEFAULT_WIDTH =  100f;
    private static final float SHAPE_DEFAULT_HEIGHT = 25f;
    private static final float SHAPE_DEFAULT_POSITION = 400f;*/


    private PictureDraft mPictureDraft = new PictureDraft();

    private ShapeFactory mShapeFactory = new ShapeFactory();
    private Vector2f mMousePos = new Vector2f(0, 0);

    @Override
    public PictureDraft createDraft() {
        createBaseIcon();
        return mPictureDraft;
    }

    private void createBaseIcon() {
        Shape shape = mShapeFactory.createShape(new Vector2f(150, 45), 26, 26, ShapeType.Ellipse);
        mPictureDraft.addShape(shape);
        shape = mShapeFactory.createShape(new Vector2f(220, 20), 80, 50, ShapeType.Rectangle);
        mPictureDraft.addShape(shape);
        shape = mShapeFactory.createShape(new Vector2f(60, 45), 80, 50, ShapeType.Triangle);
        mPictureDraft.addShape(shape);
    }

    public void update() {
        int count = 0;
        final int baseShapeQuantity = 2;
        for (Shape shape : mPictureDraft.getShapes()) {
            if (count < baseShapeQuantity) {
                if (shape.isPointInside(mMousePos)) {
                    shape.setCenter(mMousePos);
                }

            }
            /*TODO ::реализовать блок, когда shape != основным иконкам
            else {

                //shape.setSize(SHAPE_MAX_SIZE.x,SHAPE_MAX_SIZE.y );
            }
            */
            count++;
        }
    }

    /*TODO :: запилить в класс команд
    private void createNewShape(Shape shape) {
        switch (shape.getType()){
            case Ellipse:
                shape = mShapeFactory.createShape(
                        new Vector2f(SHAPE_DEFAULT_POSITION, SHAPE_DEFAULT_POSITION),
                        SHAPE_DEFAULT_WIDTH,
                        SHAPE_DEFAULT_HEIGHT,
                        ShapeType.Ellipse);
                mPictureDraft.addShape(shape);
                mPainterThread.setPictureDraft(mPictureDraft);
        }
    }*/
    public void setMousePos(float x, float y) {
        mMousePos.set(x, y);
    }
}
