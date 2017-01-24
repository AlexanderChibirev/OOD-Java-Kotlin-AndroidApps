package com.example.alexander.shapespainter.controller.commands;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.alexander.shapespainter.ShapesList;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.Tools;

import java.util.Map;

import javax.vecmath.Vector2f;


public class Controller {

    private ShapesList mShapesList = new ShapesList();
    private Tools mTools;
    private Vector2f mMousePos = new Vector2f(0, 0);
    private PointInsideShapeManager mPointInsideShape = new PointInsideShapeManager();


    public Controller(Context context, int screenWidth) {
        mTools = new Tools(context, screenWidth);
    }

    public ShapesList getShapesDraft() {
        return mShapesList;
    }

    public ShapesList getToolsDraft() {
        return mTools.getTools();
    }

    public Map<Bitmap, Vector2f> getBitmaps() {
        return mTools.getBitmaps();
    }

    public void update() {
        int count = 0;
        final int baseShapeQuantity = 2;
        for (Shape shape : mShapesList.getShapes()) {
            if (count < baseShapeQuantity) {
                if (mPointInsideShape.isPointInside(shape, mMousePos)) {

                }
              /*  if (shape.isPointInside(mMousePos)) {
                    shape.setCenter(mMousePos);
                }*/

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
