package com.example.alexander.shapespainter;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeDiagram;
import com.example.alexander.shapespainter.utils.PainterUtils;

import java.util.Map;
import java.util.Vector;

import javax.vecmath.Vector2f;

class Painter implements ICanvas {
    private Paint mPaint = new Paint();
    ShapeDiagram mDiagram;
    Vector<Vector2f> mVertices;
    RectF mRectangleEllipse = new RectF();

    void drawPicture(ShapesList draft, Canvas canvas) {
        for (Shape shape : draft.getShapes()) {
            mDiagram = shape.getDiagram();
            mVertices = shape.getVertices();
            switch (shape.getType()) {
                case Rectangle:
                    drawRectangle(
                            mVertices.get(0),
                            mVertices.get(2),
                            canvas);
                    break;
                case Ellipse:
                    drawEllipse(
                            shape.getCenter(),
                            ((mDiagram.getRight() - mDiagram.getLeft()) / 2f),
                            ((mDiagram.getBottom() - mDiagram.getTop()) / 2f),
                            canvas);
                    break;
                case Triangle:
                    mVertices = shape.getVertices();
                    drawTriangle(
                            mVertices.get(0),
                            mVertices.get(1),
                            mVertices.get(2),
                            canvas);
                    break;
            }
        }
    }


    @Override
    public void drawRectangle(Vector2f leftTop, Vector2f topBottom, Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(leftTop.x, leftTop.y, topBottom.x, topBottom.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(leftTop.x, leftTop.y, topBottom.x, topBottom.y, mPaint);
    }

    @Override
    public void drawEllipse(Vector2f center, float hRadius, float wRadius, Canvas canvas) {
        mRectangleEllipse.set(center.x - hRadius, center.y - wRadius, center.x + hRadius, center.y + wRadius);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(mRectangleEllipse, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(mRectangleEllipse, mPaint);
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
