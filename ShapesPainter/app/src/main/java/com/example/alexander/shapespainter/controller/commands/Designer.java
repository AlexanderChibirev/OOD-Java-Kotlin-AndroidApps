package com.example.alexander.shapespainter.controller.commands;

import android.view.MotionEvent;

import com.example.alexander.shapespainter.PainterThread;
import com.example.alexander.shapespainter.PictureDraft;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;

import javax.vecmath.Vector2f;


public class Designer extends Thread implements IDesigner {
    private static final Vector2f SHAPE_MAX_SIZE = new Vector2f(200f, 200f);
    private static final Vector2f SHAPE_MIN_SIZE = new Vector2f(25f, 25f);
    private static final float SHAPE_DEFAULT_WIDTH =  100f;
    private static final float SHAPE_DEFAULT_HEIGHT = 25f;
    private static final float SHAPE_DEFAULT_POSITION = 400f;

    private boolean mIsActive = false;
    private PainterThread mPainterThread;
    private PictureDraft mPictureDraft = new PictureDraft();
    private boolean mWasAddShape = true;
    private ShapeFactory mShapeFactory = new ShapeFactory();
    private Vector2f mMousePos = new Vector2f(0,0);

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

    public void setRunning(boolean run) {
        mIsActive = run;
    }

    @Override
    public  void run() {
        while (mIsActive) {
            try {
                if(mWasAddShape) {
                    Shape shape = mShapeFactory.createShape(
                            new Vector2f(SHAPE_DEFAULT_POSITION, SHAPE_DEFAULT_POSITION),
                            SHAPE_DEFAULT_WIDTH,
                            SHAPE_DEFAULT_HEIGHT,
                            ShapeType.Ellipse);
                    mPictureDraft.addShape(shape);
                    mPainterThread.setPictureDraft(mPictureDraft);
                    mWasAddShape = false;
                }
                for(Shape shape : mPictureDraft.getShapes()){
                    shape.setCenter(SHAPE_MAX_SIZE);
                }
            }
            finally {

            }
        }
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


    public void setPainterThread(PainterThread painterThread) {
        mPainterThread = painterThread;
    }
}
