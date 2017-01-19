package com.example.alexander.shapespainter;

import android.graphics.Color;

/**
 * Created by Alexander on 19.01.2017.
 */

public interface ICanvas {
    public void setFillColor(Color color);
    public void setOutlineColor(Color color);
    public void setCenter(final Vector2f pos);
    public void setSize(final Vector2f size);

    public void drawRectangle(Vector2f leftTop,
                              Vector2f size,
                              Color fillColor,
                              Color outlineColor);

    public void drawEllipse(Vector2f center,
                            float hRadius,
                            float vRadius,
                            Color fillColor,
                            Color outlineColor);

    public void drawTriangle(Vector2f leftPoint,
                             Vector2f rightPoint,
                             Vector2f topPoint,
                             Color fillColor,
                             Color outlineColor);
}
