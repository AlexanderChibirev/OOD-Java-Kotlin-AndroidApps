package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 19.01.2017.
 */

public class Rectangle extends Shape {
    private Color mFillColor;
    private Color mOutlineColor;
    private Vector2f mLeftTop;
    private VectorSize2f mSize;

    Rectangle(Vector2f leftTop, VectorSize2f size, Color fillColor, Color outlineColor) {
        mLeftTop = leftTop;
        mSize = size;
        mFillColor = fillColor;
        mOutlineColor = outlineColor;
    }

    @Override
    void draw(ICanvas canvas) {
        canvas.setFillColor(mFillColor);
        canvas.setOutlineColor(mOutlineColor);
        canvas.drawRectangle(mLeftTop, mSize, mFillColor, mOutlineColor);
    }

    @Override
    public ShapeType getType() {
        return null;
    }

    @Override
    public ShapeDiagram getDiagram() {
        return null;
    }

    @Override
    public Color getFillColor() {
        return null;
    }

    @Override
    public Color getOutlineColor() {
        return null;
    }

    @Override
    public Vector2f getVertices() {
        return null;
    }


    @Override
    public boolean isPointInside(Vector2f point) {
        return false;
    }

    @Override
    public Vector2f getCenter() {
        return null;
    }

    @Override
    public Vector2f getSize() {
        return null;
    }
}
