package com.example.alexander.shapespainter.model;

public class ShapeDiagram {
    private float mTop;
    private float mRight;
    private float mLeft;
    private float mBottom;
    ShapeDiagram(float top, float left, float right, float bottom){
        mTop = top;
        this.mLeft = left;
        this.mRight = right;
        this.mBottom = bottom;
    }

    public float getTop(){
        return mTop;
    }

    public float getRight(){
        return mRight;
    }

    public float getLeft(){
        return mLeft;
    }

    public float getBottom(){
        return mBottom;
    }

    public void Move(float diffX, float diffY){
        mTop += diffY;
        mBottom += diffY;
        mRight += diffX;
        mLeft += diffX;
    }

    public boolean isEqual(ShapeDiagram shapeArea) {
        return mRight == shapeArea.getRight()
                && mLeft == shapeArea.getLeft()
                && mTop == shapeArea.getTop()
                && mBottom == shapeArea.getBottom();
    }
}
