package com.example.alexander.shapespainter;

import javax.vecmath.Vector2f;


public interface ICanvas {
    void drawRectangle(Vector2f leftTop,
                       Vector2f topBottom);

    void drawEllipse(Vector2f center,
                            float hRadius,
                            float vRadius);

    void drawTriangle(Vector2f vertex1,
                             Vector2f vertex2,
                             Vector2f vertex3);
}
