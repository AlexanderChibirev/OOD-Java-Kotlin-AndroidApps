package com.example.alexander.shapespainter.view;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.alexander.shapespainter.model.SelectShapeDiagram;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.model.ShapesList;
import com.example.alexander.shapespainter.utils.PainterUtils;

import java.util.Vector;

import javax.vecmath.Vector2f;

import static com.example.alexander.shapespainter.constants.Constant.DEFAULT_RADIUS_DRAG_POINT;

class Painter implements ICanvas {
    private Paint mPaint = new Paint();

    void drawPicture(ShapesList draft, Canvas canvas) {
        ShapeDiagram diagram;
        Vector<Vector2f> vertices;
        for (Shape shape : draft.getShapes()) {
            diagram = shape.getDiagram();
            vertices = shape.getVertices();
            switch (shape.getType()) {
                case Rectangle:
                    drawRectangle(
                            vertices.get(0),// 0 = левый верхний угол прямоугольника
                            vertices.get(2),//2 = правый нижний угол прямоугольника
                            canvas);
                    break;
                case Ellipse:
                    drawEllipse(
                            shape.getCenter(),
                            ((diagram.getRight() - diagram.getLeft()) / 2f),
                            ((diagram.getBottom() - diagram.getTop()) / 2f),
                            canvas);
                    break;
                case Triangle:
                    vertices = shape.getVertices();
                    drawTriangle(
                            vertices.get(0),// 0 = левая вершина треугольника
                            vertices.get(1),// 1 = правая вершина треугольника
                            vertices.get(2),// 2 = верхняя вершина треугольника
                            canvas);
                    break;
            }
        }
    }


    @Override
    public void drawRectangle(Vector2f leftTop, Vector2f topBottom, Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(leftTop.x, leftTop.y, topBottom.x, topBottom.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(leftTop.x, leftTop.y, topBottom.x, topBottom.y, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float wRadius, Canvas canvas) {
        RectF rectangleEllipse = new RectF(center.x - hRadius, center.y - wRadius, center.x + hRadius, center.y + wRadius);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangleEllipse, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangleEllipse, mPaint);
    }

    @Override
    public void drawTriangle(Vector2f vertex1, Vector2f vertex2, Vector2f vertex3, Canvas canvas) {
        PainterUtils.drawPolygon(canvas, Color.BLACK,
                new Vector2f[]{
                        new Vector2f(vertex1.x, vertex1.y),
                        new Vector2f(vertex2.x, vertex2.y),
                        new Vector2f(vertex3.x, vertex3.y)
                });

        int shiftForOutlineColor = 2;
        PainterUtils.drawPolygon(canvas, Color.MAGENTA,
                new Vector2f[]{
                        new Vector2f(vertex1.x + shiftForOutlineColor, vertex1.y - shiftForOutlineColor),
                        new Vector2f(vertex2.x - shiftForOutlineColor, vertex2.y - shiftForOutlineColor),
                        new Vector2f(vertex3.x, vertex3.y + shiftForOutlineColor)
                });
    }

    void drawSelectDiagramShape(SelectShapeDiagram selectDiagramShape, Canvas canvas) {
        Shape shape = selectDiagramShape.getShape();
        ShapeDiagram shapeDiagram = shape.getDiagram();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(
                shapeDiagram.getLeft(),
                shapeDiagram.getTop(),
                shapeDiagram.getRight(),
                shapeDiagram.getBottom(),
                mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(shapeDiagram.getLeft(), shapeDiagram.getBottom(), DEFAULT_RADIUS_DRAG_POINT, mPaint);
        canvas.drawCircle(shapeDiagram.getRight(), shapeDiagram.getBottom(), DEFAULT_RADIUS_DRAG_POINT, mPaint);
        canvas.drawCircle(shapeDiagram.getRight(), shapeDiagram.getTop(), DEFAULT_RADIUS_DRAG_POINT, mPaint);
        canvas.drawCircle(shapeDiagram.getLeft(), shapeDiagram.getTop(), DEFAULT_RADIUS_DRAG_POINT, mPaint);
    }
}
