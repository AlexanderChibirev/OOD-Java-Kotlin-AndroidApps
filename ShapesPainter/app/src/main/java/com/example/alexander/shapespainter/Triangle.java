package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 20.01.2017.
 */

public class Triangle extends Shape  {

    public Triangle(Vector2f vertex1,
                    Vector2f vertex2,
                    Vector2f vertex3,
                    Color fillColor,
                    Color outlineColor) {

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
