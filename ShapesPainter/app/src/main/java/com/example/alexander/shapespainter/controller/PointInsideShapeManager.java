package com.example.alexander.shapespainter.controller;

import com.example.alexander.shapespainter.model.IShape;
import com.example.alexander.shapespainter.model.SelectShapeDiagram;
import com.example.alexander.shapespainter.model.ShapeDiagram;

import java.util.Vector;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_LEFT_VERTEX_TRIANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX;
import static com.example.alexander.shapespainter.constants.Constant.DEFAULT_RADIUS_DRAG_POINT;
import static com.example.alexander.shapespainter.constants.Constant.SIZE_INVISIBLE_RADIUS_FOR_USABILITY;

class PointInsideShapeManager {

    static boolean isPointInside(IShape shape, Vector2f point) {
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

    private static boolean isPointInsideTriangle(IShape shape, Vector2f point) {
        Vector<Vector2f> vertices = shape.getDataShape();
        Vector2f vertex1 = vertices.get(DATA_SHAPE_LEFT_VERTEX_TRIANGLE_INDEX);
        Vector2f vertex2 = vertices.get(DATA_SHAPE_RIGHT_VERTEX_TRIANGLE_INDEX);
        Vector2f vertex3 = vertices.get(DATA_SHAPE_TOP_VERTEX_TRIANGLE_INDEX);

        boolean b1, b2, b3;
        b1 = sign(point, vertex1, vertex2) < 0;
        b2 = sign(point, vertex2, vertex3) < 0;
        b3 = sign(point, vertex3, vertex1) < 0;

        return ((b1 == b2) && (b2 == b3));
    }

    private static boolean isPointInsideRectangle(IShape shape, Vector2f point) {
        SelectShapeDiagram selectShapeDiagram = new SelectShapeDiagram(shape);
        ShapeDiagram shapeDiagram = selectShapeDiagram.getShapeDiagram();
        return point.x <= shapeDiagram.getRight()
                && point.x >= shapeDiagram.getLeft()
                && point.y >= shapeDiagram.getTop()
                && point.y <= shapeDiagram.getBottom();
    }

    static boolean isPointInsideLeftTopDragPoint(ShapeDiagram shapeDiagram, Vector2f mousePos) {
        return (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2)
                + Math.pow((mousePos.y - shapeDiagram.getTop()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2) <= 1);
    }

    private static boolean isPointInsideEllipse(IShape shape, Vector2f point) {
        Vector2f center = shape.getCenter();
        SelectShapeDiagram selectShapeDiagram = new SelectShapeDiagram(shape);
        ShapeDiagram shapeDiagram = selectShapeDiagram.getShapeDiagram();
        float wRadius = center.y - shapeDiagram.getTop();
        float hRadius = center.x - shapeDiagram.getLeft();

        return Math.pow(point.x - center.x, 2) / Math.pow(wRadius, 2)
                + Math.pow(point.y - center.y, 2) / Math.pow(hRadius, 2) <= 1;
    }

    private static float sign(Vector2f p1, Vector2f p2, Vector2f p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    static boolean isPointInsideRightTopDragPoint(ShapeDiagram shapeDiagram, Vector2f mousePos) {
        return (Math.pow((mousePos.x - shapeDiagram.getRight()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2)
                + Math.pow((mousePos.y - shapeDiagram.getTop()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2) <= 1);
    }

    static boolean isPointInsideLeftBottomDragPoint(ShapeDiagram shapeDiagram, Vector2f mousePos) {
        return (Math.pow((mousePos.x - shapeDiagram.getLeft()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2)
                + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2) <= 1);
    }

    static boolean isPointInsideRightBottomDragPoint(ShapeDiagram shapeDiagram, Vector2f mousePos) {
        return (Math.pow((mousePos.x - shapeDiagram.getRight()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2)
                + Math.pow((mousePos.y - shapeDiagram.getBottom()), 2)
                / Math.pow(DEFAULT_RADIUS_DRAG_POINT + SIZE_INVISIBLE_RADIUS_FOR_USABILITY, 2) <= 1);
    }
}
