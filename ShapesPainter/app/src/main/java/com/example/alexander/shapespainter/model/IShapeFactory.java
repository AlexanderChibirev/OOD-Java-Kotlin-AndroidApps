package com.example.alexander.shapespainter.model;

import android.graphics.Color;

import javax.vecmath.Vector2f;


public interface IShapeFactory {
    Shape createShape(final Vector2f center,
                      float width,
                      float height,
                      final Color fillColor,
                      final Color outlineColor,
                      final ShapeType shapeType);
}
