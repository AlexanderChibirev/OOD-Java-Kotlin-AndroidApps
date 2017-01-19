package com.example.alexander.shapespainter;

import java.util.ArrayList;

/**
 * Created by Alexander on 19.01.2017.
 */

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
