package com.example.alexander.shapespainter.model;

import javax.vecmath.Vector2f;


interface IShapeFactory {
    Shape createShape(final Vector2f center,
                      float width,
                      float height,
                      final ShapeType shapeType);
}
