package com.example.alexander.shapespaintermvp.mvp.models;

import java.util.Vector;

import javax.vecmath.Vector2f;

public interface IShape {
    ShapeType getType();

    void setCenter(final Vector2f pos);

    void setSize(float width, float height);

    Vector<Vector2f> getDataShape();

    Vector2f getCenter();

    Vector2f getSize();
}
