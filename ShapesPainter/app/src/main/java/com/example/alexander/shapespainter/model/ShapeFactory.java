package com.example.alexander.shapespainter.model;


import com.example.alexander.shapespainter.model.shapes.Ellipse;
import com.example.alexander.shapespainter.model.shapes.Rectangle;
import com.example.alexander.shapespainter.model.shapes.Triangle;

import javax.vecmath.Vector2f;

public class ShapeFactory {

    public Shape createShape(Vector2f center, float width, float height, ShapeType shapeType) {
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
