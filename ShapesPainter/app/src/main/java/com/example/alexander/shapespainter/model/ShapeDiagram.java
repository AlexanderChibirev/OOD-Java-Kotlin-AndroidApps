package com.example.alexander.shapespainter.model;

public class ShapeDiagram {
    private float mTop;
    private float mRight;
    private float mLeft;
    private float mBottom;

    ShapeDiagram(float top, float left, float right, float bottom) {
        mTop = top;
        mLeft = left;
        mRight = right;
        mBottom = bottom;
    }

    public float getTop() {
        return mTop;
    }

    public float getRight() {
        return mRight;
    }

    public float getLeft() {
        return mLeft;
    }

    public float getBottom() {
        return mBottom;
    }

    void setTop(float top) {
        mTop = top;
    }

    void setRight(float right) {
        mRight = right;
    }

    void setBottom(float bottom) {
        mBottom = bottom;
    }

    void setLeft(float left) {
        mLeft = left;
    }
}
