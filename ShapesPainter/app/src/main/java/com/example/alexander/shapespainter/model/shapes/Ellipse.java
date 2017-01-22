package com.example.alexander.shapespainter.model.shapes;

import com.example.alexander.shapespainter.ICanvas;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;

public class Ellipse extends Shape {
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
    public ShapeDiagram getDiagram() {
        return new ShapeDiagram(
                mCenter.y - mHRadius,
                mCenter.x - mWRadius,
                mCenter.x + mWRadius,
                mCenter.y + mHRadius);
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
    public Vector<Vector2f> getVertices() {
        Vector<Vector2f>  v = new Vector<>();
        v.add(mCenter);
        return v;
    }

    @Override
    public boolean isPointInside(Vector2f point) {
        return (point.x	- mCenter.x) * (point.x - mCenter.x) /
                (mWRadius * mWRadius) + (point.y - mCenter.y) *
                (point.y - mCenter.y) / (mHRadius * mHRadius) <= 1;
    }

    @Override
    public Vector2f getCenter() {
        return mCenter;
    }

    @Override
    public Vector2f getSize() {
        ShapeDiagram diagram = getDiagram();
        return new  Vector2f(
                diagram.getRight() - diagram.getLeft(),
                diagram.getBottom() - diagram.getTop());
    }

    @Override
    public void draw(ICanvas canvas) { canvas.drawEllipse(mCenter, mWRadius, mHRadius); }
}
