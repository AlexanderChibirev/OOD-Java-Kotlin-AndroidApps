package com.example.alexander.shapespainter.model.shapes;


import com.example.alexander.shapespainter.model.IShape;
import com.example.alexander.shapespainter.model.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;

public class Ellipse implements IShape {
    private Vector2f mCenter;
    private float mWRadius;
    private float mHRadius;


    public Ellipse(Vector2f center,
                   float wRadius,
                   float hRadius) {
        mCenter = center;
        mHRadius = hRadius;
        mWRadius = wRadius;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.Ellipse;
    }

    @Override
    public void setCenter(Vector2f pos) {
        mCenter = pos;
    }

    @Override
    public void setSize(float width, float height) {
        mWRadius = width / 2f;
        mHRadius = height / 2f;
    }

    @Override
    public Vector<Vector2f> getDataShape() {
        Vector<Vector2f> v = new Vector<>();
        v.add(mCenter);
        v.add(new Vector2f(mWRadius, mHRadius));
        return v;
    }

    @Override
    public Vector2f getCenter() {
        return mCenter;
    }

    @Override
    public Vector2f getSize() {
        return new Vector2f(
                (mCenter.x + mWRadius) - (mCenter.x - mWRadius),
                (mCenter.y + mHRadius) - (mCenter.y - mHRadius));
    }
}
