package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 19.01.2017.
 */

interface IShapeFactory {
    void createShape(final Vector2f center,
                     final VectorSize2f size,
                     final Color fillColor,
                     final Color outlineColor,
                     final ShapeType shapeType);
}
