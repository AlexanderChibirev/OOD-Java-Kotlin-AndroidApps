package com.example.alexander.shapespainter;


import android.graphics.drawable.shapes.Shape;

import java.util.ArrayList;



public class PictureDraft {
    private ArrayList<Shape> shapeArrayList = new ArrayList<>();

    public void addShape(Shape shape){
        shapeArrayList.add(shape);
    }

    public int getShapeCount(){
        return shapeArrayList.size();
    }

    public Shape getShape(int idx){
        return shapeArrayList.get(idx);
    }

    public void removeShape(Shape shape) {
        shapeArrayList.remove(shape);
    }

}
