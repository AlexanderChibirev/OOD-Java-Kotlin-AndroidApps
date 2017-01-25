package com.example.alexander.shapespainter.model;


import java.util.ArrayList;

public class ShapesList {
    private ArrayList<Shape> mShapeList = new ArrayList<>();

    public void addShape(Shape shape) {
        mShapeList.add(shape);
    }

    public int getIndexShape(Shape shape) {
        return mShapeList.indexOf(shape);
    }

    public void insertShape(int index, Shape shape) {
        mShapeList.add(index, shape);
    }

    public ArrayList<Shape> getShapes() {
        return mShapeList;
    }

    public void removeShape(Shape shape) {
        mShapeList.remove(shape);
    }

}
