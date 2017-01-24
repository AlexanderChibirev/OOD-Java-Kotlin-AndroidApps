package com.example.alexander.shapespainter.model.shapes;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;

import java.util.Vector;

import javax.vecmath.Vector2f;

public class Triangle extends Shape {

    private Vector2f mVertex1;
    private Vector2f mVertex2;
    private Vector2f mVertex3;

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
    public ShapeDiagram getDiagram() {
        return new ShapeDiagram(mVertex3.y, mVertex1.x, mVertex2.x, mVertex2.y);
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
    public Vector<Vector2f> getVertices() {
        Vector<Vector2f> v = new Vector<>();
        v.add(mVertex1);
        v.add(mVertex2);
        v.add(mVertex3);
        return v;
    }

    @Override
    public Vector2f getCenter() {
        return new Vector2f(
                mVertex1.x / 2f + mVertex2.x / 2f,
                mVertex3.y / 2f + mVertex2.y / 2f);
    }

    @Override
    public Vector2f getSize() {
        ShapeDiagram diagram = getDiagram();
        return new Vector2f(
                diagram.getRight() - diagram.getLeft(),
                diagram.getBottom() - diagram.getTop());
    }

}
