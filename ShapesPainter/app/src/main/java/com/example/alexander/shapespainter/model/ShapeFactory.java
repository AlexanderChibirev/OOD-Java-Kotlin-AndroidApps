package com.example.alexander.shapespainter.model;

import android.graphics.Color;

import com.example.alexander.shapespainter.model.shapes.Ellipse;
import com.example.alexander.shapespainter.model.shapes.Rectangle;
import com.example.alexander.shapespainter.model.shapes.Triangle;

import javax.vecmath.Vector2f;

public class ShapeFactory implements IShapeFactory {
    @Override
    public Shape createShape(Vector2f center, float width, float height, Color fillColor, Color outlineColor, ShapeType shapeType) {
       switch (shapeType){
           case Ellipse:
               return getEllipse(center, width, height, fillColor, outlineColor);
           case Rectangle:
               return getRectangle(center, width, height, fillColor, outlineColor);
           case  Triangle:
               return getTriangle(center, width, height, fillColor, outlineColor);
       }
       return null;
    }

    private Rectangle getRectangle(Vector2f center, float width, float height, Color fillColor, Color outlineColor) {
        return new Rectangle(
                center,
                width,
                height,
                fillColor,
                outlineColor);
    }

    private Ellipse getEllipse(Vector2f center, float width, float height, Color fillColor, Color outlineColor) {
        return new Ellipse(
                center,
                width / 2,
                height / 2,
                fillColor,
                outlineColor);
    }

    private Triangle getTriangle(Vector2f center, float width, float height, Color fillColor, Color outlineColor) {
        return new Triangle(
                new Vector2f(center.getX() - width / 2.f, center.getY() + height / 2.f),
                new Vector2f(center.getX() + width / 2.f, center.getY() + height / 2.f),
                new Vector2f(center.getX(), center.getY() - height / 2.f),
                fillColor,
                outlineColor);
    }
}
