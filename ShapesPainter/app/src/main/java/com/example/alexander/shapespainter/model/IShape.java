package com.example.alexander.shapespainter.model;

import android.graphics.Color;

import javax.vecmath.Vector2f;


public interface IShape {
    ShapeType getType();
    ShapeDiagram getDiagram();
    Color getFillColor();
    Color getOutlineColor();
    Vector2f getVertices();
    boolean isPointInside(final Vector2f point);
    Vector2f getCenter();
    Vector2f getSize();
}
