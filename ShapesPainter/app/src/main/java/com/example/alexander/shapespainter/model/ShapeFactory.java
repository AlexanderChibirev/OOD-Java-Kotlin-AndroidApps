package com.example.alexander.shapespainter.model;


import com.example.alexander.shapespainter.model.shapes.Ellipse;
import com.example.alexander.shapespainter.model.shapes.Rectangle;
import com.example.alexander.shapespainter.model.shapes.Triangle;

import javax.vecmath.Vector2f;

public class ShapeFactory {

    private final static float SHAPE_DEFAULT_WIDTH = 110f;
    private final static float SHAPE_DEFAULT_HEIGHT = 80f;
    private final static float SHAPE_DEFAULT_SIZE_FOR_CIRCLE = 80f;
    private final static Vector2f SHAPE_DEFAULT_POSITION = new Vector2f(150f, 150f);

    public IShape createDefaultShape(ShapeType shapeType) {
        switch (shapeType) {
            case Ellipse:
                return getEllipse(
                        SHAPE_DEFAULT_POSITION,
                        SHAPE_DEFAULT_SIZE_FOR_CIRCLE,
                        SHAPE_DEFAULT_SIZE_FOR_CIRCLE);
            case Rectangle:
                return getRectangle(
                        SHAPE_DEFAULT_POSITION,
                        SHAPE_DEFAULT_WIDTH,
                        SHAPE_DEFAULT_HEIGHT);
            case Triangle:
                return getTriangle(
                        SHAPE_DEFAULT_POSITION,
                        SHAPE_DEFAULT_WIDTH,
                        SHAPE_DEFAULT_HEIGHT);
        }
        return null;
    }

    public IShape createShape(Vector2f center, float width, float height, ShapeType shapeType) {
        switch (shapeType) {
            case Ellipse:
                return getEllipse(center, width, height);
            case Rectangle:
                return getRectangle(center, width, height);
            case Triangle:
                return getTriangle(center, width, height);
        }
        return null;
    }

    private Rectangle getRectangle(Vector2f center, float width, float height) {
        return new Rectangle(
                new Vector2f(
                        center.x - width / 2f,
                        center.y - height / 2f),
                width,
                height);
    }

    private Ellipse getEllipse(Vector2f center, float width, float height) {
        return new Ellipse(
                center,
                width / 2f,
                height / 2f);
    }

    private Triangle getTriangle(Vector2f center, float width, float height) {
        return new Triangle(
                new Vector2f(center.getX() - width / 2f, center.getY() + height / 2f),
                new Vector2f(center.getX() + width / 2f, center.getY() + height / 2f),
                new Vector2f(center.getX(), center.getY() - height / 2f));
    }
}
