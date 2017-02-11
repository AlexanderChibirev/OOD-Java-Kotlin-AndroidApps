package com.example.alexander.shapespainter.model;


import java.util.ArrayList;

public class ShapesList {
    private ArrayList<IShape> mShapeList = new ArrayList<>();

    public void addShape(IShape shape) {
        mShapeList.add(shape);
    }

    public int getIndexShape(IShape shape) {
        return mShapeList.indexOf(shape);
    }

    public void insertShape(int index, IShape shape) {
        mShapeList.add(index, shape);
    }

    public ArrayList<IShape> getShapes() {
        return mShapeList;
    }

    public void removeShape(IShape shape) {
        mShapeList.remove(shape);
    }

}
