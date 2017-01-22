package com.example.alexander.shapespainter;

import javax.vecmath.Vector2f;


public interface ICanvas {
    void drawRectangle(float left,
                       float right,
                       float top,
                       float bottom);

    void drawEllipse(Vector2f center,
                            float hRadius,
                            float vRadius);

    void drawTriangle(Vector2f vertex1,
                             Vector2f vertex2,
                             Vector2f vertex3);
}
