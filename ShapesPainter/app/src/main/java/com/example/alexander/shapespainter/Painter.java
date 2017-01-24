package com.example.alexander.shapespainter;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.utils.PainterUtils;

import java.util.Map;
import java.util.Vector;

import javax.vecmath.Vector2f;

class Painter implements ICanvas {
    private Paint mPaint = new Paint();

    void drawPicture(ShapesList draft, Canvas canvas) {
        for (Shape shape : draft.getShapes()) {
            ShapeDiagram diagram = shape.getDiagram();
            Vector<Vector2f> vertices = shape.getVertices();
            switch (shape.getType()) {
                case Ellipse:
                    drawEllipse(
                            shape.getCenter(),
                            ((diagram.getRight() - diagram.getLeft())),
                            ((diagram.getBottom() - diagram.getTop())),
                            canvas);
                    break;
                case Rectangle:
                    drawRectangle(
                            vertices.get(0),
                            vertices.get(2),
                            canvas);
                    break;
                case Triangle:
                    vertices = shape.getVertices();
                    drawTriangle(
                            vertices.get(0),
                            vertices.get(1),
                            vertices.get(2),
                            canvas);
                    break;
            }
        }
    }


    @Override
    public void drawRectangle(Vector2f leftTop, Vector2f topBottom, Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect((int) leftTop.x, (int) leftTop.y, (int) topBottom.x, (int) topBottom.y);
        canvas.drawRect(rect, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float wRadius, Canvas canvas) {
        RectF rectangle = new RectF(center.x - hRadius, center.y - wRadius, center.x + hRadius, center.y + wRadius);
        System.out.print(rectangle);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangle, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangle, mPaint);
    }

    @Override
    public void drawTriangle(Vector2f vertex1, Vector2f vertex2, Vector2f vertex3, Canvas canvas) {
        PainterUtils.drawPoly(canvas, Color.BLACK,
                new Vector2f[]{
                        new Vector2f(vertex1.x, vertex1.y),
                        new Vector2f(vertex2.x, vertex2.y),
                        new Vector2f(vertex3.x, vertex3.y)
                });

        int shiftForOutlineColor = 1;
        PainterUtils.drawPoly(canvas, Color.BLUE,
                new Vector2f[]{
                        new Vector2f(vertex1.x + shiftForOutlineColor, vertex1.y - shiftForOutlineColor),
                        new Vector2f(vertex2.x, vertex2.y + shiftForOutlineColor),
                        new Vector2f(vertex3.x - shiftForOutlineColor, vertex3.y - shiftForOutlineColor)
                });
    }

    void drawTools(Canvas canvas, ShapesList draft, Map<Bitmap, Vector2f> bitmaps) {
        Bitmap bitmap;
        Vector2f pos;
        for (Map.Entry entry : bitmaps.entrySet()) {
            bitmap = (Bitmap) entry.getKey();
            pos = (Vector2f) entry.getValue();
            canvas.drawBitmap(bitmap, pos.x, pos.y, mPaint);
        }
        drawPicture(draft, canvas);
    }
}
