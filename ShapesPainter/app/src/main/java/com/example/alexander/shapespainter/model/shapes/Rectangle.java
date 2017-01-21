package com.example.alexander.shapespainter.model.shapes;

import android.graphics.Color;

import com.example.alexander.shapespainter.ICanvas;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.Shape;

import javax.vecmath.Vector2f;


public class Rectangle extends Shape {
    private Color mFillColor;
    private Color mOutlineColor;
    private Vector2f mLeftTop;
    private float mWidth;
    private float mHeight;

    public Rectangle(Vector2f leftTop, float width, float height, Color fillColor, Color outlineColor) {
        mLeftTop = leftTop;
        mWidth = width;
        mHeight = height;
        mFillColor = fillColor;
        mOutlineColor = outlineColor;
    }

    @Override
    protected void draw(ICanvas canvas) {

    }

    @Override
    public ShapeType getType() {
        return null;
    }

    @Override
    public ShapeDiagram getDiagram() {
        return null;
    }

    @Override
    public Color getFillColor() {
        return null;
    }

    @Override
    public Color getOutlineColor() {
        return null;
    }

    @Override
    public Vector2f getVertices() {
        return null;
    }


    @Override
    public boolean isPointInside(Vector2f point) {
        return false;
    }

    @Override
    public Vector2f getCenter() {
        return null;
    }

    @Override
    public Vector2f getSize() {
        return null;
    }
}
