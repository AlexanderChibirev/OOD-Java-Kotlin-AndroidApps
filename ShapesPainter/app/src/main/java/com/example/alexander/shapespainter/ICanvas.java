package com.example.alexander.shapespainter;

import android.graphics.Color;

import javax.vecmath.Vector2f;


public interface ICanvas {
    void setFillColor(Color color);
    void setOutlineColor(Color color);
    void setCenter(final Vector2f pos);
    void setSize(float width, float height);

    void drawRectangle(Vector2f leftTop,
                              float width, float height,
                              Color fillColor,
                              Color outlineColor);

    void drawEllipse(Vector2f center,
                            float hRadius,
                            float vRadius,
                            Color fillColor,
                            Color outlineColor);

    void drawTriangle(Vector2f leftPoint,
                             Vector2f rightPoint,
                             Vector2f topPoint,
                             Color fillColor,
                             Color outlineColor);
}
