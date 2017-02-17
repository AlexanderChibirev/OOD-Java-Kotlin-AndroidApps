package com.example.alexander.shapespaintermvp.mvp.models.shapes;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;

public class Triangle implements IShape {

    private Vector2f mVertex1 = new Vector2f();
    private Vector2f mVertex2 = new Vector2f();
    private Vector2f mVertex3 = new Vector2f();

    public Triangle(Vector2f vertex1,
                    Vector2f vertex2,
                    Vector2f vertex3) {
        mVertex1 = vertex1;
        mVertex2 = vertex2;
        mVertex3 = vertex3;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.Triangle;
    }

    @Override
    public void setCenter(Vector2f pos) {
        float height = mVertex2.y - mVertex3.y;
        float width = mVertex2.x - mVertex1.x;
        mVertex1 = new Vector2f(pos.x - width / 2f, pos.y + height / 2f);
        mVertex2 = new Vector2f(pos.x + width / 2f, pos.y + height / 2f);
        mVertex3 = new Vector2f(pos.x, pos.y - height / 2f);
    }

    @Override
    public void setSize(float width, float height) {
        Vector2f center = getCenter();
        mVertex3 = new Vector2f(center.x, center.y - height / 2f);
        mVertex2 = new Vector2f(center.x + width / 2f, center.y + height / 2f);
        mVertex1 = new Vector2f(center.x - width / 2f, center.y + height / 2f);
    }

    @Override
    public Vector<Vector2f> getDataShape() {
        Vector<Vector2f> mDataShape = new Vector<>();
        mDataShape.add(mVertex1);
        mDataShape.add(mVertex2);
        mDataShape.add(mVertex3);
        return mDataShape;
    }

    @Override
    public Vector2f getCenter() {
        return new Vector2f(
                mVertex1.x / 2f + mVertex2.x / 2f,
                mVertex3.y / 2f + mVertex2.y / 2f);
    }

    @Override
    public Vector2f getSize() {
        return new Vector2f(
                mVertex2.x - mVertex1.x,
                mVertex2.y - mVertex3.y);
    }
}
