package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 19.01.2017.
 */

public class ShapeFactory implements IShapeFactory {
    @Override
    public Shape createShape(Vector2f center, VectorSize2f size, Color fillColor, Color outlineColor, ShapeType shapeType) {
       switch (shapeType){
           case Ellipse:
               return getEllipse(center, size, fillColor, outlineColor);
           case Rectangle:
               return getRectangle(center, size, fillColor, outlineColor);
           case  Triangle:
               return getEllipse(center, size, fillColor, outlineColor);
       }
       return null;
    }

    private Rectangle getRectangle(Vector2f center, VectorSize2f size, Color fillColor, Color outlineColor) {
        return new Rectangle(center, size, fillColor, outlineColor);
    }

    private Ellipse getEllipse(Vector2f center, VectorSize2f size, Color fillColor, Color outlineColor) {
        return new Ellipse(center, size, fillColor, outlineColor);
    }

    private Triangle getTriangle(Vector2f center, VectorSize2f size, Color fillColor, Color outlineColor) {
        return new Triangle(center, size, fillColor, outlineColor);
    }
}
