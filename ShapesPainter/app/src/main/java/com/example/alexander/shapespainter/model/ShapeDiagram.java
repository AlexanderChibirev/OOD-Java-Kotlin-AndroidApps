package com.example.alexander.shapespainter.model;

public class ShapeDiagram {
    private float mTop;
    private float mRight;
    private float mLeft;
    private float mBottom;

    public ShapeDiagram(float top, float left, float right, float bottom) {
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
}
