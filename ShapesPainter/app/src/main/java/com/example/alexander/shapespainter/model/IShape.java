package com.example.alexander.shapespainter.model;

import java.util.Vector;

import javax.vecmath.Vector2f;

interface IShape {
    ShapeType getType();

    ShapeDiagram getDiagram();

    void setCenter(final Vector2f pos);

    void setSize(float width, float height);

    Vector<Vector2f> getVertices();

    Vector2f getCenter();

    Vector2f getSize();
}
