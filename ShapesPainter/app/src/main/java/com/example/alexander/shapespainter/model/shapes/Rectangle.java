package com.example.alexander.shapespainter.model.shapes;

import com.example.alexander.shapespainter.ICanvas;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;


public class Rectangle extends Shape {
    private Vector2f mLeftTop;
    private Vector2f mRightBottom;

    public Rectangle(
            final Vector2f leftTop,
            final float width,
            final float height) {

        mLeftTop = leftTop;
        mRightBottom = new Vector2f(mLeftTop.x + width, mLeftTop.y + height);
    }

    @Override
    public void draw(ICanvas canvas) { canvas.drawRectangle(mLeftTop.x, mLeftTop.y, mRightBottom.x, mRightBottom.y); }

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
        mLeftTop = new Vector2f( pos.x - width / 2f, pos.y - height / 2f);
        mRightBottom =  new Vector2f( pos.x + width / 2f, pos.y + height / 2f);
    }

    @Override
    public void setSize(float width, float height) {
        Vector2f center = getCenter();
        mLeftTop = new Vector2f( center.x - width / 2f, center.y - height / 2f );
        mRightBottom = new Vector2f( center.x + width / 2f, center.y + height / 2f);
    }

    @Override
    public Vector<Vector2f> getVertices() {
        Vector<Vector2f> v = new Vector<>();
        v.add(mLeftTop);
        v.add(new Vector2f(mRightBottom.x, mLeftTop.y));
        v.add(mRightBottom);
        v.add(new Vector2f(mLeftTop.x, mRightBottom.y));
        return v;
    }

    @Override
    public boolean isPointInside(Vector2f point) {
        ShapeDiagram diagram = getDiagram();
        return point.x <= diagram.getRight()
                && point.x >= diagram.getLeft()
                && point.y >= diagram.getTop()
                && point.y <= diagram.getBottom();
    }

    @Override
    public Vector2f getCenter() {
        return new Vector2f(
                mLeftTop.x / 2f + mRightBottom.x /2f,
                mLeftTop.y / 2f + mRightBottom.y /2f);
    }

    @Override
    public Vector2f getSize() {
        ShapeDiagram diagram = getDiagram();
        return new  Vector2f(
                diagram.getRight() - diagram.getLeft(),
                diagram.getBottom() - diagram.getTop());
    }

}
