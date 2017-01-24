package com.example.alexander.shapespainter;

import android.graphics.Canvas;

import javax.vecmath.Vector2f;


public interface ICanvas {
    void drawRectangle(Vector2f leftTop,
                       Vector2f topBottom,
                       Canvas canvas);

    void drawEllipse(Vector2f center,
                     float hRadius,
                     float vRadius,
                     Canvas canvas);

    void drawTriangle(Vector2f vertex1,
                      Vector2f vertex2,
                      Vector2f vertex3,
                      Canvas canvas);
}
