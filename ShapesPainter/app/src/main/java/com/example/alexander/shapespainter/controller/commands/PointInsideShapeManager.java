package com.example.alexander.shapespainter.controller.commands;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;

import java.util.Vector;

import javax.vecmath.Vector2f;

class PointInsideShapeManager {

    boolean isPointInside(Shape shape, Vector2f point) {
        switch (shape.getType()) {
            case Ellipse:
                return isPointInsideEllipse(shape, point);
            case Rectangle:
                return isPointInsideRectangle(shape, point);
            case Triangle:
                return isPointInsideTriangle(shape, point);
        }
        return false;
    }

    boolean isPointInside(RectF rect, Vector2f point, Bitmap bitmap) {
        return (rect.contains(point.x, point.y)) &&
                bitmap.getPixel((int) (point.getX() - rect.left), (int) (point.getY() - rect.top)) != Color.TRANSPARENT;
    }

    private boolean isPointInsideTriangle(Shape shape, Vector2f point) {
        Vector<Vector2f> vertices = shape.getVertices();
        Vector2f vertex1 = vertices.get(0);
        Vector2f vertex2 = vertices.get(1);
        Vector2f vertex3 = vertices.get(2);

        boolean b1, b2, b3;
        b1 = sign(point, vertex1, vertex2) < 0;
        b2 = sign(point, vertex2, vertex3) < 0;
        b3 = sign(point, vertex3, vertex1) < 0;

        return ((b1 == b2) && (b2 == b3));
    }

    private boolean isPointInsideRectangle(Shape shape, Vector2f point) {
        ShapeDiagram shapeDiagram = shape.getDiagram();
        return point.x <= shapeDiagram.getRight()
                && point.x >= shapeDiagram.getLeft()
                && point.y >= shapeDiagram.getTop()
                && point.y <= shapeDiagram.getBottom();
    }

    private boolean isPointInsideEllipse(Shape shape, Vector2f point) {
        Vector2f center = shape.getCenter();
        ShapeDiagram shapeDiagram = shape.getDiagram();
        float wRadius = center.y - shapeDiagram.getTop();
        float hRadius = center.x - shapeDiagram.getLeft();
        return Math.pow(point.x - center.x, 2) / Math.pow(wRadius, 2)
                + Math.pow(point.y - center.y, 2) / Math.pow(hRadius, 2) <= 1;
    }

    private float sign(Vector2f p1, Vector2f p2, Vector2f p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }
}
