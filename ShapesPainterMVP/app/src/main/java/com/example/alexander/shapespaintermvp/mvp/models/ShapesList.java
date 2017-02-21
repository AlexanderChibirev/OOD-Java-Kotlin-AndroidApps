package com.example.alexander.shapespaintermvp.mvp.models;


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
        if (mShapeList.size() != 0) {
            return mShapeList;
        }
        return mShapeList;
    }

    public void removeShape(IShape shape) {
        mShapeList.remove(shape);
    }

}
