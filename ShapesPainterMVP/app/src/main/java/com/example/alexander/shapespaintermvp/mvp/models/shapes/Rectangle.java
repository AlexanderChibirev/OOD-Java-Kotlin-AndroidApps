package com.example.alexander.shapespaintermvp.mvp.models.shapes;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;


public class Rectangle implements IShape {
    private Vector2f mLeftTop = new Vector2f();
    private Vector2f mRightBottom = new Vector2f();

    public Rectangle(
            final Vector2f leftTop,
            final float width,
            final float height) {

        mLeftTop = leftTop;
        mRightBottom = new Vector2f(mLeftTop.x + width, mLeftTop.y + height);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.Rectangle;
    }

    @Override
    public void setCenter(Vector2f pos) {
        float width = mRightBottom.x - mLeftTop.x;
        float height = mRightBottom.y - mLeftTop.y;
        mLeftTop.set(
                pos.x - width / 2f,
                pos.y - height / 2f);
        mRightBottom.set(
                pos.x + width / 2f,
                pos.y + height / 2f);
    }

    @Override
    public void setSize(float width, float height) {
        Vector2f center = getCenter();
        mLeftTop.set(
                center.x - width / 2f,
                center.y - height / 2f);
        mRightBottom.set(
                center.x + width / 2f,
                center.y + height / 2f);
    }

    @Override
    public Vector<Vector2f> getDataShape() {
        Vector<Vector2f> dataShape = new Vector<>();
        dataShape.add(mLeftTop);
        dataShape.add(mRightBottom);
        return dataShape;
    }

    @Override
    public Vector2f getCenter() {
        return new Vector2f(
                mLeftTop.x / 2f + mRightBottom.x / 2f,
                mLeftTop.y / 2f + mRightBottom.y / 2f);
    }

    @Override
    public Vector2f getSize() {
        return new Vector2f(
                mRightBottom.x - mLeftTop.x,
                mRightBottom.y - mLeftTop.y);
    }
}
