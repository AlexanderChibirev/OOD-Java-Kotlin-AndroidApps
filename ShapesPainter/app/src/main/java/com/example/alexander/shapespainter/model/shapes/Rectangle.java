package com.example.alexander.shapespainter.model.shapes;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;


public class Rectangle extends Shape {
    private Vector2f mLeftTop;
    private Vector2f mRightBottom;
    private Vector2f mSize = new Vector2f(0, 0);
    private Vector2f mCenter = new Vector2f(0, 0);
    private Vector<Vector2f> mVertices = new Vector<>();

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
    public ShapeDiagram getDiagram() {
        return new ShapeDiagram(mLeftTop.y, mLeftTop.x, mRightBottom.x, mRightBottom.y);
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
    public Vector<Vector2f> getVertices() {
        mVertices.add(0, mLeftTop);
        mVertices.add(1, new Vector2f(mRightBottom.x, mLeftTop.y));
        mVertices.add(2, mRightBottom);
        mVertices.add(3, new Vector2f(mLeftTop.x, mRightBottom.y));
        return mVertices;
    }

    @Override
    public Vector2f getCenter() {
        mCenter.set(mLeftTop.x / 2f + mRightBottom.x / 2f,
                mLeftTop.y / 2f + mRightBottom.y / 2f);
        return mCenter;
    }

    @Override
    public Vector2f getSize() {
        ShapeDiagram diagram = getDiagram();
        mSize.set(
                diagram.getRight() - diagram.getLeft(),
                diagram.getBottom() - diagram.getTop());
        return mSize;
    }

}
