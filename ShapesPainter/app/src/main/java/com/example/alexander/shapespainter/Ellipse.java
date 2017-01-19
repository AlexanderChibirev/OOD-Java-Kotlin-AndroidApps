package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 20.01.2017.
 */

public class Ellipse extends Shape {
    private Color mFillColor;
    private Color mOutlineColor;
    private Vector2f mCenter;
    private float mWRadius;
    private float mHRadius;


    public Ellipse(Vector2f center,
                   float  wRadius,
                   float hRadius,
                   Color fillColor,
                   Color outlineColor) {
        mCenter = center;
        mHRadius = hRadius;
        mWRadius = wRadius;
        mOutlineColor = outlineColor;
        mFillColor = fillColor;
    }

    @Override
    void draw(ICanvas canvas) {

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
