package com.example.alexander.shapespainter;


import com.example.alexander.shapespainter.model.Shape;

import java.util.ArrayList;


public class PictureDraft {
    private ArrayList<Shape> shapeArrayList = new ArrayList<>();

    public void addShape(Shape shape) {
        shapeArrayList.add(shape);
    }

    public int getShapeCount() {
        return shapeArrayList.size();
    }

    public Shape getShape(int idx) {
        return shapeArrayList.get(idx);
    }


    public ArrayList<Shape> getShapes() {
        return shapeArrayList;
    }

    public void deleteAllShapes() {
        shapeArrayList.clear();
    }

    public void removeShape(Shape shape) {
        shapeArrayList.remove(shape);
    }

}
