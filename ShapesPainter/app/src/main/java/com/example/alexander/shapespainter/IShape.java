package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 19.01.2017.
 */

public interface IShape {
    public ShapeType getType();
    public ShapeDiagram getDiagram();
    public Color getFillColor();
    public Color getOutlineColor();
    public Vector2f getVertices();
    public boolean isPointInside(final Vector2f point);
    public Vector2f getCenter();
   // public Iterable<IShape> getChild();
    public Vector2f getSize();
}
